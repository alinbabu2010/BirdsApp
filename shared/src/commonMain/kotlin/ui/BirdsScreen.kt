package ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import data.model.BirdInfo
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import util.BASE_URL

@Composable
fun BirdsScreen(viewModel: BirdsViewModel) {

    val uiState by viewModel.uiState.collectAsState()

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 5.dp, start = 5.dp, end = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            uiState.categories.forEach { category ->
                Button(
                    modifier = Modifier.fillMaxHeight(0.1F).weight(1F),
                    onClick = {
                        viewModel.selectCategory(category)
                    },
                ) {
                    Text(category)
                }
            }
        }
        AnimatedVisibility(uiState.selectedImages.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(top = 5.dp),
                contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 5.dp)
            ) {
                items(uiState.selectedImages) {
                    BirdImageCell(it)
                }
            }
        }
    }


}

@Composable
fun BirdImageCell(birdInfo: BirdInfo) {
    KamelImage(
        asyncPainterResource("$BASE_URL${birdInfo.path}"),
        "${birdInfo.category} by ${birdInfo.author}",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize().aspectRatio(1F)
    )
}
