package hu.tvarga.list.view

import androidx.recyclerview.widget.DiffUtil
import hu.tvarga.model.PicsumItem

class PicsumItemDiffCallback(
    private val oldList: List<PicsumItem>,
    private val newList: List<PicsumItem>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].author == newList[newItemPosition].author
                && oldList[oldItemPosition].width == newList[newItemPosition].width
                && oldList[oldItemPosition].height == newList[newItemPosition].height
                && oldList[oldItemPosition].url == newList[newItemPosition].url
                && oldList[oldItemPosition].downloadUrl == newList[newItemPosition].downloadUrl
    }
}
