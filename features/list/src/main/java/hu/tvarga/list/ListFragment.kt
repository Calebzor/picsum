package hu.tvarga.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import hu.tvarga.core.base.BaseFragment
import hu.tvarga.list.databinding.ListFragmentBinding

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


    override fun getViewModel() = picsumListViewModel

}
