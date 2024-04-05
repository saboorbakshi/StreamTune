package com.streamtune.streamtune

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GetTokenResult
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.greeting.GreetingViewModel
import com.streamtune.streamtune.ui.register.RegisterViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.verify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class RegisterViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)
    private val firebaseAuth = mockk<FirebaseAuth>(relaxed = true)
    private val tokenResult = mockk<GetTokenResult>(relaxed = true)

    // System under test
    private lateinit var viewModel: RegisterViewModel

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize mocks
        MockKAnnotations.init(this)

        // Initialize view model
        viewModel = RegisterViewModel(navController)

    }

    @Test
    fun navigationToGreeting() {

        // Act: Call onAuxButtonClick
        viewModel.onAuxButtonClick()

        // Assert: Verify that the navigation occurred
        verify{ navController.navigate("greeting") }

    }

    @Test
    fun mismatchedPassword_cantNavigate() {
        // Arrange
        viewModel.email = "test@example.com"
        viewModel.password = "correctpassword"
        viewModel.confirmPassword = "correctpassword"

        // Act
        viewModel.onRegButtonClick()

        // Assert
        verify(exactly = 0) { navController.navigate("songlist") }
    }

//    @Test
//    fun matchedPassword_canNavigate() = runTest {
//        // Arrange
//        viewModel.email = "test@example.com"
//        viewModel.password = "correctpassword"
//        viewModel.confirmPassword = "correctpassword"
//
//        every {
//            firebaseAuth.createUserWithEmailAndPassword(any(), any())
//        } returns mockk()
//
//        coEvery { firebaseAuth.currentUser?.getIdToken(true)?.await() } returns mockk {
//            every { token } returns "fakeToken"
//        }
//
//        // Act
//        viewModel.onRegButtonClick()
//
//        // Assert
//        verify { navController.navigate("songlist") }
//    }
}