package hu.tvarga.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import hu.tvarga.core.base.BaseFragment
import hu.tvarga.list.databinding.ListFragmentBinding
import hu.tvarga.list.view.PicsumAdapter
import hu.tvarga.list.view.PicsumLoadStateAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter


class ListFragment : BaseFragment(R.layout.list_fragment) {
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    private val picsumListViewModel: PicsumListViewModel by viewModels()
    private lateinit var adapter: PicsumAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
    }

    private fun initAdapter() {
        adapter = PicsumAdapter()
        adapter.viewModelPicsum = getViewModel()
        binding.list.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PicsumLoadStateAdapter { adapter.retry() },
            footer = PicsumLoadStateAdapter { adapter.retry() }
        )
        val numberOfColumns = 2
        binding.list.layoutManager = GridLayoutManager(context, numberOfColumns)

        setAdapterListeners()

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collectLatest { loadState ->
                val itemCount = adapter.itemCount
                val isListEmpty = loadState.refresh is LoadState.NotLoading && itemCount == 0
                showEmptyList(isListEmpty)

                binding.listRefresh.isRefreshing = loadState.refresh is LoadState.Loading
                binding.list.isVisible = loadState.refresh is LoadState.NotLoading
                binding.progressBar.isVisible = loadState.refresh is LoadState.Loading
                binding.retryButton.isVisible = loadState.refresh is LoadState.Error

                handleErrorState(loadState)
            }
        }

        lifecycleScope.launchWhenCreated {
            picsumListViewModel.picsums.collectLatest {
                adapter.submitData(it)
            }
        }

        lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.list.scrollToPosition(0) }
        }
    }

    private fun handleErrorState(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
        errorState?.let { Toast.makeText(context, R.string.an_error_happened, Toast.LENGTH_LONG).show() }
    }

    private fun setAdapterListeners() {
        binding.listRefresh.setOnRefreshListener { adapter.refresh() }
        binding.retryButton.setOnClickListener { adapter.retry() }
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            binding.emptyList.visibility = View.VISIBLE
            binding.list.visibility = View.GONE
        } else {
            binding.emptyList.visibility = View.GONE
            binding.list.visibility = View.VISIBLE
        }
    }

    override fun getViewModel() = picsumListViewModel

}
