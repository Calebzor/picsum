package hu.tvarga.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import hu.tvarga.core.base.BaseFragment
import hu.tvarga.core.resource.Resource
import hu.tvarga.list.databinding.ListFragmentBinding
import hu.tvarga.list.view.PicsumAdapter
import hu.tvarga.model.PicsumItem
import timber.log.Timber

class ListFragment : BaseFragment(R.layout.list_fragment) {
    private var _binding: ListFragmentBinding? = null
    private val binding get() = _binding!!
    private val picsumListViewModel: PicsumListViewModel by viewModels()

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
        val picsumListAdapter = PicsumAdapter(getViewModel())
        binding.picsumListRv.apply {
            adapter = picsumListAdapter
        }
        binding.picsumListRefresh.setOnRefreshListener { getViewModel().getpicsums(true) }
        picsumListViewModel.picsums.observe(viewLifecycleOwner) {
            Timber.d("picsum $it")
            handleLoadingIndicator(it)
            handleNonLoading(it)
            if (it.status == Resource.Status.SUCCESS && it.data?.isNotEmpty() == true) {
                picsumListAdapter.updateData(it.data!!)
            }
        }
        picsumListViewModel.getpicsums(false)
    }

    private fun handleNonLoading(it: Resource<List<PicsumItem>>) {
        if (it.status == Resource.Status.ERROR ||
            (it.status == Resource.Status.SUCCESS && it.data?.isEmpty() == true)
        ) {
            binding.picsumListEmptyText.visibility = View.VISIBLE
        } else {
            binding.picsumListEmptyText.visibility = View.GONE
        }
    }

    private fun handleLoadingIndicator(it: Resource<List<PicsumItem>>) {
        when (it.status) {
            Resource.Status.LOADING -> binding.picsumListRefresh.isRefreshing = true
            else -> binding.picsumListRefresh.isRefreshing = false
        }
    }

    override fun getViewModel() = picsumListViewModel

}
