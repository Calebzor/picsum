package hu.tvarga.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import hu.tvarga.list.databinding.PicsumLoadStateFooterViewItemBinding

class PicsumLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PicsumLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PicsumLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PicsumLoadStateViewHolder {
        return PicsumLoadStateViewHolder(
            PicsumLoadStateFooterViewItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), retry
        )
    }
}
