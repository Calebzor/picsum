package hu.tvarga.model

import hu.tvarga.model.api.PicsumApiObject
import hu.tvarga.model.dto.PicsumItem
import hu.tvarga.model.entity.PicsumItemEntity

fun PicsumItemEntity.toPicsumItem(): PicsumItem =
    PicsumItem(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        downloadUrl = this.downloadUrl,
    )

fun PicsumApiObject.toPicsumItemEntity(): PicsumItemEntity =
    PicsumItemEntity(
        id = this.id,
        author = this.author,
        width = this.width,
        height = this.height,
        url = this.url,
        downloadUrl = this.downloadUrl,
    )
