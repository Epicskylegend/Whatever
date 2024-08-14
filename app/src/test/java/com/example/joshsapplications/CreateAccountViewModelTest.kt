import com.example.joshsapplications.ApiService
import com.example.joshsapplications.CreateAccountState
import com.example.joshsapplications.CreateAccountViewModel
import com.example.joshsapplications.RegisterRequest
import com.example.joshsapplications.UserResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CreateAccountViewModelTest {

    private lateinit var viewModel: CreateAccountViewModel
    private val mockApiService = mockk<ApiService>()

    @Before
    fun setUp() {
        viewModel = CreateAccountViewModel(mockApiService)
    }

    @Test
    fun `createAccount should succeed when data is valid`() = runBlocking {
        val request = RegisterRequest("Josh", "josh@example.com", "password123")
        coEvery { mockApiService.registerUser(request) } returns UserResponse("token", "id")

        viewModel.createAccount("Josh", "josh@example.com", "password123")

        assertEquals(CreateAccountState.Success(UserResponse("token", "id")), viewModel.createAccountState.first())
        coVerify { mockApiService.registerUser(request) }
    }

    @Test
    fun `createAccount should fail when data is invalid`() = runBlocking {
        val request = RegisterRequest("", "", "")
        coEvery { mockApiService.registerUser(request) } throws Exception("Invalid data")

        viewModel.createAccount("", "", "")

        assertEquals(CreateAccountState.Error("Invalid data"), viewModel.createAccountState.first())
        coVerify { mockApiService.registerUser(request) }
    }
}
