package hu.tvarga.model.dto

data class PicsumItem(
    val id: String,
    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    val downloadUrl: String
)
