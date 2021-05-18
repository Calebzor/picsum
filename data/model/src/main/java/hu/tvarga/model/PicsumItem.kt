package hu.tvarga.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//{
//    "id": "0",
//    "author": "Alejandro Escamilla",
//    "width": 5616,
//    "height": 3744,
//    "url": "https://unsplash.com/...",
//    "download_url": "https://picsum.photos/..."
//}
@Entity
@JsonClass(generateAdapter = true)
data class PicsumItemEntity(
    @PrimaryKey
    val id: String,

    val author: String,
    val width: Long,
    val height: Long,
    val url: String,
    @field:Json(name = "download_url")
    val downloadUrl: String
)

