package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.songlist.SongCardViewModel
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class SongCardViewModelTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)
    // System under test
    private lateinit var viewModel: SongCardViewModel
    private lateinit var song: Song

    private val testDispatcher = StandardTestDispatcher()
    private val playlistName = "Example Playlist"

    @Before
    fun setUp() {
        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)
        // Initialize mocks
        MockKAnnotations.init(this)
        mockkObject(ApiCalls)

        // Initialize view model
        song = Song("1", "Song 1", "Artist 1", "https://exampleSong.com", "Fake Cover", 120)
        viewModel = SongCardViewModel(navController, playlistName, song)
    }

    @Test
    fun deleteSongFromPlaylist() = runTest {
        val songID = "1" // make sure it's same as song.id
        // Assign
        coEvery { ApiCalls.deleteFromPlaylist(any(), any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.deleteSongClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        // Assert
        coVerify { ApiCalls.deleteFromPlaylist(playlistName, songID) }
        coVerify { ApiCalls.getPlaylists() }
        verify { navController.navigate("songlist") }
    }

}