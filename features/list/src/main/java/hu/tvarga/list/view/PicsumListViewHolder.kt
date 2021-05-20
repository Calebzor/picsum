package hu.tvarga.list.view

import androidx.recyclerview.widget.RecyclerView
import hu.tvarga.list.PicsumListViewModel
import hu.tvarga.list.databinding.ItemPicsumBinding
import hu.tvarga.model.PicsumItem

class PicsumListViewHolder(val binding: ItemPicsumBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(picsum: PicsumItem?, viewModel: PicsumListViewModel?) {
        picsum?.let {
            viewModel?.getPicsumImage(binding.root, picsum)
            binding.root.setOnClickListener { viewModel?.picsumListItemClick(picsum.id) }
        }
    }
}
