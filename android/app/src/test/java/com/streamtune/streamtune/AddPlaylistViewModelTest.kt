package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.model.Playlist
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.addplaylist.AddPlaylistViewModel
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
class AddPlaylistViewModelTest {
    private val navController = mockk<NavController>(relaxed = true)

    // System under test
    private lateinit var viewModel: AddPlaylistViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {

        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)

        // Initialize mocks
        MockKAnnotations.init(this)
        mockkObject(ApiCalls)

        // Initialize view model
        viewModel = AddPlaylistViewModel(navController)

    }

    @Test
    fun successfulAddPlaylist() = runTest {
        //Assign
        val playlistName = "New Playlist"
        coEvery { ApiCalls.createPlaylist(any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.name = playlistName
        viewModel.onAddButtonClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        //Assert
        coVerify { ApiCalls.createPlaylist(viewModel.name) }
        coVerify { ApiCalls.getPlaylists() }
        verify { navController.navigate("playlistlist") }
    }

    @Test
    fun unsuccessfulAddPlaylist() = runTest {
        //Assign
        val playlistName = "Existing Playlist"
        val songsInPlaylist: MutableList<Song> = mutableListOf(
            Song("69", "夜に駆ける", "YOASOBI", "https://franktao.com/files/yorunikakeru.mp3", "helloCover", 120),
            Song("70", "アイドル", "YOASOBI", "https://franktao.com/files/idol.mp3", "blah", 120)
        )
        coEvery { ApiCalls.createPlaylist(any()) } just Runs
        coEvery { ApiCalls.getPlaylists() } just Runs

        // Act
        viewModel.name = playlistName
        StreamTune.allPlaylists.add(Playlist(viewModel.name, songsInPlaylist))
        viewModel.onAddButtonClick()

        // Ensure that the coroutine is executed to completion
        advanceUntilIdle()

        //Assert
        coVerify (exactly = 0) { ApiCalls.createPlaylist(viewModel.name) }
        coVerify (exactly = 0) { ApiCalls.getPlaylists() }

        // Verify that no navigation action was triggered"
        verify(exactly = 0) { navController.navigate(any<String>()) }
    }
}