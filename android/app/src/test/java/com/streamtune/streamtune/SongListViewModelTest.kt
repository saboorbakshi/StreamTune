package com.streamtune.streamtune

import androidx.navigation.NavController
import com.streamtune.streamtune.ui.songlist.SongListViewModel
import io.mockk.MockKAnnotations
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
class SongListViewModelTest {

    // Mock dependencies
    private val navController = mockk<NavController>(relaxed = true)

    // System under test
    private lateinit var viewModel: SongListViewModel

    @Before
    fun setUp() {
        // Initialize mocks
        MockKAnnotations.init(this)

        // Initialize view model
        viewModel = SongListViewModel(navController)
    }

    @Test
    fun navigationToAddSong() {
        // Call onAddSongButtonClick
        viewModel.onAddSongButtonClick()

        // Verify that the navigation occurred
        verify { navController.navigate("addsong") }
    }

}