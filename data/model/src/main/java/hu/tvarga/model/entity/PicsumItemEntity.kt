package hu.tvarga.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picsums")
data class PicsumItemEntity(
    @PrimaryKey
    val id: String,

    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    val downloadUrl: String
)

