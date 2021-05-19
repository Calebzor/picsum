package hu.tvarga.model

data class PicsumItem(
    val id: String,
    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    val downloadUrl: String
)
