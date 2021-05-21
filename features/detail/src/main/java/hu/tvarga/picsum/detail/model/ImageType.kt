package hu.tvarga.picsum.detail.model

import androidx.annotation.IdRes
import hu.tvarga.detail.R

enum class ImageType(@IdRes val id: Int) {
    NORMAL(R.id.normal),
    BLUR(R.id.blur),
    GREY(R.id.grey)
}
