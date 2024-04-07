package com.streamtune.streamtune

import androidx.navigation.NavController
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import com.streamtune.streamtune.ui.greeting.GreetingViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.verify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GreetingViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)
    private val firebaseAuth = mockk<FirebaseAuth>(relaxed = true)
    private val firebaseUser = mockk<FirebaseUser>(relaxed = true)

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
    fun unsuccessfulLogin() {

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

//    @Test
//    fun successfulLogin() {
//
//        // Arrange
//        // Mock Firebase behavior
//        val idToken = "exampleToken"
//        every { firebaseAuth.signInWithEmailAndPassword(any(), any()) } returns mockk()
//        every { firebaseAuth.currentUser } returns firebaseUser
//        val getTokenTask = mockk<Task<GetTokenResult>>()
//        val getTokenResult = mockk<GetTokenResult> {
//            every { token } returns idToken
//        }
//        every { firebaseUser.getIdToken(false) } returns getTokenTask
//        coEvery { getTokenTask.await() } returns getTokenResult
//
//
//        // Act
//        viewModel.email = "test@example.com"
//        viewModel.password = "wrongpassword"
//        viewModel.onLogButtonClick()
//
//        // Assert
//        verify { firebaseAuth.signInWithEmailAndPassword(viewModel.email, viewModel.password) }
//        verify { firebaseUser.getIdToken(false) }
//
//        verify { navController.navigate("playlistlist")}
//    }
}