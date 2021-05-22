package hu.tvarga.picsum.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import hu.tvarga.core.base.BaseFragment
import hu.tvarga.core.base.BaseViewModel
import hu.tvarga.detail.R
import hu.tvarga.detail.databinding.DetailFragmentBinding
import hu.tvarga.picsum.detail.model.ImageType
import kotlinx.coroutines.flow.collectLatest

class DetailFragment : BaseFragment(R.layout.detail_fragment) {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!
    private val picsumDetailViewModel: PicsumDetailViewModel by viewModels()
    override fun getViewModel(): BaseViewModel = picsumDetailViewModel

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.blur.setOnClickListener { picsumDetailViewModel.setImageType(ImageType.BLUR) }
        binding.grey.setOnClickListener { picsumDetailViewModel.setImageType(ImageType.GREY) }
        binding.normal.setOnClickListener { picsumDetailViewModel.setImageType(ImageType.NORMAL) }
        picsumDetailViewModel.setImageType(ImageType.NORMAL)
        lifecycleScope.launchWhenCreated {
            picsumDetailViewModel.picsums(args.id).collectLatest {
                binding.author.text = getString(R.string.author, it.author)
                binding.id.text = getString(R.string.id, it.id)
                binding.size.text = getString(R.string.size, it.size)
                binding.url.text = getString(R.string.url, it.url)
                binding.downloadUrl.text = getString(R.string.downloadUrl, it.downloadUrl)
                Glide.with(binding.image).load(it.downloadUrl).placeholder(android.R.drawable.ic_menu_report_image)
                    .into(binding.image)
                binding.imageButtons.check(it.selected)
            }
        }
    }
}
