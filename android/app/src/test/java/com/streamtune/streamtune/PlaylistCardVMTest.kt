package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.playlists.PlaylistCardVM
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

class PlaylistCardVMTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)
    // System under test
    private lateinit var viewModel: PlaylistCardVM
    private val playlistName = "Example Playlist"
    private val songsInPlaylist: MutableList<Song> = mutableListOf(
        Song("69", "夜に駆ける", "YOASOBI", "https://franktao.com/files/yorunikakeru.mp3", "helloCover", 120),
        Song("70", "アイドル", "YOASOBI", "https://franktao.com/files/idol.mp3", "blah", 120)
    )

    private val testDispatcher = StandardTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)
        // Initialize mocks
        MockKAnnotations.init(this)
        mockkObject(ApiCalls)

        viewModel = PlaylistCardVM(navController, Playlist(playlistName, songsInPlaylist))
    }

    @Test
    fun navigateToSonglist() {
        // Act
        viewModel.onClick()
        // Assert
        verify { navController.navigate("songlist") }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun deletePlaylist() = runTest {
        // Assign
        coEvery { ApiCalls.deletePlaylist(any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.deletePlaylistClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        // Assert
        coVerify { ApiCalls.deletePlaylist(playlistName) }
        coVerify { ApiCalls.getPlaylists() }
        verify { navController.navigate("playlistlist") }

    }
}