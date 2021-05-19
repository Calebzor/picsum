package hu.tvarga.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PicsumItemEntity(
    @PrimaryKey
    val id: String,

    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    val downloadUrl: String
)

