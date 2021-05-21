package hu.tvarga.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import hu.tvarga.core.navigation.NavigationCommand
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _navigation = Channel<NavigationCommand>()
    val navigation: Flow<NavigationCommand> = _navigation.receiveAsFlow()

    /**
     * Convenient method to handle navigation from a [ViewModel]
     */
    fun navigate(directions: NavDirections) {
        viewModelScope.launch {
            _navigation.send(NavigationCommand.To(directions))
        }
    }
}
