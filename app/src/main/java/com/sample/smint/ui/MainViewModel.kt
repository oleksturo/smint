package com.sample.smint.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.sample.smint.EVENT_LOG_IN
import com.sample.smint.EVENT_LOG_OUT
import com.sample.smint.EVENT_TRIPS_VIEWED
import com.sample.smint.SMINT_USER_ID
import com.sample.smint.SmintApp
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val state = MutableStateFlow(State(loggedIn = false))
    val stateFlow = state.asStateFlow()

    private val actions = MutableSharedFlow<Action>()
    val actionsFlow = actions.asSharedFlow()

    private val analytics
        get() = getApplication<SmintApp>().smintAnalytics

    fun handleInput(input: Input) {
        when (input) {
            is Input.Login -> login()
            is Input.Logout -> logout()
            is Input.ViewExperiment -> viewTrips()
        }
    }

    private fun login() {
        analytics.identify(SMINT_USER_ID)
        analytics.track(EVENT_LOG_IN)
        state.update {
            it.copy(loggedIn = true)
        }
        viewModelScope.launch {
            actions.emit(Action.ShowToast("Logged in successfully"))
        }
    }

    private fun logout() {
        analytics.anonymize()
        analytics.track(EVENT_LOG_OUT)
        state.update {
            it.copy(loggedIn = false)
        }
        viewModelScope.launch {
            actions.emit(Action.ShowToast("Logged out successfully"))
        }
    }

    private fun viewTrips() {
        analytics.track(EVENT_TRIPS_VIEWED)
        viewModelScope.launch {
            actions.emit(Action.ShowToast("Trips viewed. Event should be filtered"))
        }
    }

    data class State(
        val loggedIn: Boolean
    )

    sealed interface Action {
        data class ShowToast(val message: String) : Action
    }

    sealed interface Input {
        data object Login : Input
        data object Logout : Input
        data object ViewExperiment : Input
    }
}