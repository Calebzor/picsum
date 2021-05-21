package hu.tvarga.picsum.detail.model

import androidx.annotation.IdRes

data class DetailUiModel(
    val id: String,
    val author: String,
    val size: String,
    val url: String,
    val downloadUrl: String,
    @IdRes val selected: Int
)
