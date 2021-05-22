package hu.tvarga.list.view

import androidx.recyclerview.widget.RecyclerView
import hu.tvarga.list.PicsumListViewModel
import hu.tvarga.list.databinding.ItemPicsumBinding
import hu.tvarga.model.dto.PicsumItem

class PicsumListViewHolder(private val binding: ItemPicsumBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindTo(picsum: PicsumItem?, viewModel: PicsumListViewModel?) {
        picsum?.let {
            viewModel?.getPicsumImage(binding.image, picsum)
            binding.image.setOnClickListener { viewModel?.picsumListItemClick(picsum.id) }
        }
    }
}
