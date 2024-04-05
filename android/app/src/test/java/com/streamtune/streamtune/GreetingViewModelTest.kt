package com.streamtune.streamtune

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.streamtune.streamtune.ui.greeting.GreetingViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.verify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GreetingViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)
    private val firebaseAuth = mockk<FirebaseAuth>(relaxed = true)
    private val tokenResult = mockk<GetTokenResult>(relaxed = true)

    // System under test
    private lateinit var viewModel: GreetingViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize mocks
        MockKAnnotations.init(this)

        // Initialize view model
        viewModel = GreetingViewModel(navController)

    }

    @Test
    fun navigationToRegister() {

        // Act: Call onAuxButtonClick
        viewModel.onAuxButtonClick()

        // Assert: Verify that the navigation occurred
        verify{ navController.navigate("register") }

    }

    @Test
    fun testUnsuccessfulLogin() {

        // Arrange
        viewModel.email = "test@example.com"
        viewModel.password = "wrongpassword"

        // Mock Firebase behavior
        every {
            firebaseAuth.signInWithEmailAndPassword(viewModel.email, viewModel.password)
        } throws RuntimeException("Simulated login failure")

        // Act
        viewModel.onLogButtonClick()

        // Assert
        verify(exactly = 0) { navController.navigate("songlist") }

    }
}