package ui

import data.model.BirdInfo

data class BirdsUiState(
    val images: List<BirdInfo> = emptyList(),
    val selectedCategory : String? = null
){
    val categories = images.map { it.category }.toSet()
    val selectedImages = images.filter { it.category == selectedCategory  }
}

