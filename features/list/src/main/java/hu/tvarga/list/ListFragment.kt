package hu.tvarga.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import hu.tvarga.core.base.BaseFragment
import hu.tvarga.list.databinding.ListFragmentBinding
import hu.tvarga.list.view.PicsumAdapter
import hu.tvarga.list.view.PicsumLoadStateAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

class ListFragment : BaseFragment(R.layout.list_fragment) {
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    private val picsumListViewModel: PicsumListViewModel by viewModels()
    private val picsumListAdapter: PicsumAdapter = PicsumAdapter()

    private var loadJob: Job? = null

    private fun load() {
        loadJob?.cancel()
        loadJob = lifecycleScope.launch {
            getViewModel().getPicsums().collectLatest {
                picsumListAdapter.submitData(it)
            }
        }
    }

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

        load()
        scrollToTopOnRefresh()
    }

    private fun scrollToTopOnRefresh() {
        lifecycleScope.launch {
            picsumListAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.list.scrollToPosition(0) }
        }
    }

    private fun initAdapter() {
        picsumListAdapter.viewModelPicsum = getViewModel()
        binding.list.apply {
            adapter = picsumListAdapter
        }
        binding.listRefresh.setOnRefreshListener { picsumListAdapter.refresh() }
        binding.retryButton.setOnClickListener { picsumListAdapter.retry() }

        binding.list.adapter = picsumListAdapter.withLoadStateHeaderAndFooter(
            header = PicsumLoadStateAdapter { picsumListAdapter.retry() },
            footer = PicsumLoadStateAdapter { picsumListAdapter.retry() }
        )

        picsumListAdapter.addLoadStateListener { loadState ->
            val isListEmpty = loadState.refresh is LoadState.NotLoading && picsumListAdapter.itemCount == 0
            showEmptyList(isListEmpty)

            binding.listRefresh.isRefreshing = loadState.mediator?.refresh is LoadState.Loading
            binding.list.isVisible = loadState.mediator?.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.mediator?.refresh is LoadState.Loading
            binding.retryButton.isVisible = loadState.mediator?.refresh is LoadState.Error

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let { Toast.makeText(context, R.string.an_error_happened, Toast.LENGTH_LONG).show() }
        }
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
