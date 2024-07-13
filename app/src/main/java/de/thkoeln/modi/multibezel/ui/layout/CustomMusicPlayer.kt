package de.thkoeln.modi.multibezel.ui.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import de.thkoeln.modi.multibezel.ui.compose.MusicPlayerControls
import de.thkoeln.modi.multibezel.viewmodel.CustomMusicPlayerViewModel

@Composable
fun CustomMusicPlayerScreen(customMusicPlayerViewModel: CustomMusicPlayerViewModel = viewModel()) {
    customMusicPlayerViewModel.initMediaPlayer(LocalContext.current)
    var isPlaying by remember { mutableStateOf(false) }

    CustomMusicPlayerScreen(
        songTitle = "Africa",
        artist = "Toto",
        onPlayPauseClick = {
            customMusicPlayerViewModel.playPause()
            isPlaying = !isPlaying
        },
        isPlaying = isPlaying,
        progress = 0.5f,
        onPreviousClick = { customMusicPlayerViewModel.onPreviousSong() },
        onNextClick = { customMusicPlayerViewModel.onNextSong() }
    )
}

@Composable
fun CustomMusicPlayerScreen(
    isPlaying: Boolean,
    progress: Float,
    songTitle: String,
    artist: String,
    onPlayPauseClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = songTitle,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(1.dp))
            Text(
                text = artist,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        }

        MusicPlayerControls(
            modifier = Modifier
                .align(Alignment.Center),
            isPlaying = isPlaying,
            progress = progress,
            onPlayPauseClick = onPlayPauseClick,
            onPreviousClick = onPreviousClick,
            onNextClick = onNextClick
        )
    }
}

@Preview(device = "id:wearos_small_round", showSystemUi = true, showBackground = true)
@Composable
fun CustomMusicPlayerPreview() {
    var isPlaying by remember { mutableStateOf(false) }
    CustomMusicPlayerScreen(
        songTitle = "Africa",
        artist = "Toto",
        isPlaying = isPlaying,
        onPlayPauseClick = { isPlaying = !isPlaying },
        progress = 0.4f,
        onPreviousClick = {},
        onNextClick = {}
    )
}