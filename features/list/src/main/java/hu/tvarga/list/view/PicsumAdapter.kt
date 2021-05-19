package hu.tvarga.list.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hu.tvarga.list.PicsumListViewModel
import hu.tvarga.list.databinding.ItemPicsumBinding
import hu.tvarga.model.PicsumItem

class PicsumAdapter(private val viewModelPicsum: PicsumListViewModel) : RecyclerView.Adapter<PicsumListViewHolder>() {

    private val picsums: MutableList<PicsumItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PicsumListViewHolder(ItemPicsumBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int = picsums.size

    override fun onBindViewHolder(holder: PicsumListViewHolder, position: Int) =
        holder.bindTo(picsums[position], viewModelPicsum)

    fun updateData(items: List<PicsumItem>) {
        val diffCallback = PicsumItemDiffCallback(picsums, items)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        picsums.clear()
        picsums.addAll(items)
        diffResult.dispatchUpdatesTo(this)
    }


}
