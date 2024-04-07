package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.model.Song
import com.streamtune.streamtune.network.ApiCalls
import com.streamtune.streamtune.ui.playlists.PlaylistListVM
import com.streamtune.streamtune.ui.songlist.SongCardViewModel
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class PlaylistlistVMTest {
    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)
    // System under test
    private lateinit var viewModel: PlaylistListVM

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        // Set Main dispatcher for testing
        Dispatchers.setMain(testDispatcher)
        // Initialize mocks
        MockKAnnotations.init(this)

        viewModel = PlaylistListVM(navController)
    }

    @Test
    fun navigateToAddPlaylist() {

        // Act
        viewModel.onAddPlaylistButtonClick()

        // Assert
        verify { navController.navigate("addplaylist") }
    }
}