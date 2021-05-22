package hu.tvarga.list.view

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import hu.tvarga.list.databinding.PicsumLoadStateFooterViewItemBinding

class PicsumLoadStateViewHolder(
    private val binding: PicsumLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        binding.progressBar.isVisible = loadState is Loading
        binding.retryButton.isVisible = loadState is Error
        binding.errorMsg.isVisible = !(loadState as? Error)?.error?.message.isNullOrBlank()
        binding.errorMsg.text = (loadState as? Error)?.error?.message
    }
}
