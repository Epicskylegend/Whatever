package com.example.joshsapplications

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private val mockApiService = mockk<ApiService>()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = LoginViewModel(mockApiService)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login should succeed when credentials are valid`() = runBlocking {
        val email = "test@example.com"
        val password = "password123"
        val request = LoginRequest(email, password)
        val expectedResponse = UserResponse("token", "userId")

        coEvery { mockApiService.loginUser(request) } returns expectedResponse

        viewModel.login(email, password)

        assertEquals(LoginState.Success(expectedResponse), viewModel.loginState.first())
        coVerify { mockApiService.loginUser(request) }
    }

    @Test
    fun `login should fail when credentials are invalid`() = runBlocking {
        // Given
        val email = "invalid@example.com"
        val password = "wrongpassword"
        val request = LoginRequest(email, password)
        val errorMessage = "Invalid credentials"

        coEvery { mockApiService.loginUser(request) } throws Exception(errorMessage)

        viewModel.login(email, password)

        assertEquals(LoginState.Error(errorMessage), viewModel.loginState.first())
        coVerify { mockApiService.loginUser(request) }
    }

    @Test
    fun `login should handle exceptions properly`() = runBlocking {
        val email = "test@example.com"
        val password = "password123"
        val request = LoginRequest(email, password)

        coEvery { mockApiService.loginUser(request) } throws Exception("Network error")

        viewModel.login(email, password)


        assertEquals(LoginState.Error("Network error"), viewModel.loginState.first())
        coVerify { mockApiService.loginUser(request) }
    }
}
