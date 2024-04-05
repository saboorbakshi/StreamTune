package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.addsong.AddSongViewModel
import com.streamtune.streamtune.ui.greeting.GreetingViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.mockk.mockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class AddSongViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)

    // System under test
    private lateinit var viewModel: AddSongViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize mocks
        MockKAnnotations.init(this)
        mockkObject(ApiCalls)
        mockkObject(ApiConfig)

        // Initialize view model
        viewModel = AddSongViewModel(navController)

    }

    @Test
    fun successfulAddSong() {
        // Mocking ApiConfig to return true every time
        every { ApiConfig.songAdded } returns true

        // Assign
        viewModel.link = "https://example.com"

        // Act
        viewModel.onAddButtonClick()

        // Assert
        verify { ApiCalls.addSong(youtubeURL = "https://example.com") }
        verify { ApiCalls.getPlaylists() }
        verify { navController.navigate("songlist") }
    }

    @Test
    fun unsuccessfulAddSong() {
        // Mocking ApiConfig to return true every time
        every { ApiConfig.songAdded } returns false

        // Assign
        viewModel.link = "https://example.com"

        // Act
        viewModel.onAddButtonClick()

        // Assert
        verify { ApiCalls.addSong(youtubeURL = "https://example.com") }
        verify (exactly = 0) { ApiCalls.getPlaylists() }
        verify (exactly = 0) { navController.navigate("songlist") }
    }
}