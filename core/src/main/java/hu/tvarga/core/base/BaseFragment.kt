package hu.tvarga.core.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import hu.tvarga.core.navigation.NavigationCommand
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
abstract class BaseFragment(layout: Int) : Fragment(layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeNavigation(getViewModel())
    }

    abstract fun getViewModel(): BaseViewModel

    private fun observeNavigation(viewModel: BaseViewModel) {
        lifecycleScope.launchWhenCreated {
            viewModel.navigation.collectLatest {
                when (it) {
                    is NavigationCommand.To -> findNavController().navigate(it.directions, getExtras())
                    is NavigationCommand.Back -> findNavController().navigateUp()
                }
            }
        }
    }

    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()
}
