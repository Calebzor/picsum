package hu.tvarga.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import hu.tvarga.list.PicsumListViewModel
import hu.tvarga.list.databinding.ItemPicsumBinding
import hu.tvarga.model.dto.PicsumItem

class PicsumAdapter :
    PagingDataAdapter<PicsumItem, PicsumListViewHolder>(COMPARATOR) {

    var viewModelPicsum: PicsumListViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsumListViewHolder {
        return PicsumListViewHolder(ItemPicsumBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PicsumListViewHolder, position: Int) {
        holder.bindTo(getItem(position), viewModelPicsum)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<PicsumItem>() {
            override fun areItemsTheSame(oldItem: PicsumItem, newItem: PicsumItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PicsumItem, newItem: PicsumItem): Boolean =
                oldItem == newItem
        }
    }

}
