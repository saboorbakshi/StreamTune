package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.network.ApiConfig
import com.streamtune.streamtune.ui.addsong.AddSongViewModel
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class AddSongViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)

    // System under test
    private lateinit var viewModel: AddSongViewModel

    private val testDispatcher = StandardTestDispatcher()
    private val playlistName = "Example Playlist"

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize mocks
        MockKAnnotations.init(this)
        mockkObject(ApiCalls)

        // Initialize view model
        viewModel = AddSongViewModel(navController, playlistName)

    }

    @After
    fun tearDown() {
        unmockkObject(ApiCalls)
        unmockkObject(ApiConfig)
        clearAllMocks()
    }

    @Test
    fun successfulAddSong() = runTest {
        // Assign
        val songID = "123"
        coEvery { ApiCalls.addSong(any()) } returns songID
        coEvery { ApiCalls.addToPlaylist(any(), any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.link = "https://youtubeExample.com"
        viewModel.onAddButtonClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        // Assert
        // Verify that the correct API calls were made
        coVerify { ApiCalls.addSong( viewModel.link ) }
        coVerify { ApiCalls.addToPlaylist(playlistName, songID) }
        coVerify { ApiCalls.getPlaylists() }

        // Verify that navigation occurred
        coVerify { navController.navigate("songlist") }
    }

    @Test
    fun unsuccessfulAddSong_songIDIsNull() = runTest {
        // Assign
        val songID = "123"
        coEvery { ApiCalls.addSong(any()) } returns null
        coEvery { ApiCalls.addToPlaylist(any(), any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.link = "https://youtubeExample.com"
        viewModel.onAddButtonClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        // Assert
        // Verify that the correct API calls were made
        coVerify { ApiCalls.addSong( viewModel.link ) }
        coVerify (exactly = 0) { ApiCalls.addToPlaylist(playlistName, songID) }
        coVerify { ApiCalls.getPlaylists() }

        // Verify that navigation occurred
        coVerify { navController.navigate("songlist") }
    }

    @Test
    fun unsuccessfulAddSong_playlistNameIsAddedSongs() = runTest {
        // Assign
        val songID = "123"
        coEvery { ApiCalls.addSong(any()) } returns songID
        coEvery { ApiCalls.addToPlaylist(any(), any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.link = "https://youtubeExample.com"
        viewModel.playlistName = "Added Songs"
        viewModel.onAddButtonClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        // Assert
        // Verify that the correct API calls were made
        coVerify { ApiCalls.addSong( viewModel.link ) }
        coVerify (exactly = 0) { ApiCalls.addToPlaylist(viewModel.playlistName, songID) }
        coVerify { ApiCalls.getPlaylists() }

        // Verify that navigation occurred
        coVerify { navController.navigate("songlist") }
    }


}