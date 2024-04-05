package com.streamtune.streamtune

import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.addsong.AddSongViewModel
import com.streamtune.streamtune.ui.splash.SplashViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class SplashViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)

    // System under test
    private lateinit var viewModel: SplashViewModel

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize mocks
        MockKAnnotations.init(this)
        // Mock Firebase Auth
        mockkStatic(FirebaseAuth::class)
        every { FirebaseAuth.getInstance() } returns mockk()

        // Initialize view model
        viewModel = SplashViewModel(navController)


    }

    @Test
    fun alreadyLoggedIn() {
        every { FirebaseAuth.getInstance().currentUser } returns mockk()

        // Act
        viewModel.onAppStart()

        // Assert
        verify { navController.navigate("songlist") }
    }

    @Test
    fun notYetLoggedIn() {
        every { FirebaseAuth.getInstance().currentUser } returns null

        // Act
        viewModel.onAppStart()

        // Assert
        verify { navController.navigate("greeting") }
    }
}