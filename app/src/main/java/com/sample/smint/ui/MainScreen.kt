@file:OptIn(ExperimentalMaterial3Api::class)

package com.sample.smint.ui

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sample.smint.ui.MainViewModel.Input

@Composable
fun MainScreen(vm: MainViewModel = viewModel()) {

    val state by vm.stateFlow.collectAsState()
    MainScreenContent(
        state = state,
        handleInput = vm::handleInput
    )

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        vm.actionsFlow.collect { action: MainViewModel.Action ->
            when (action) {
                is MainViewModel.Action.ShowToast -> {
                    Toast.makeText(context, action.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
private fun MainScreenContent(
    state: MainViewModel.State,
    handleInput: (Input) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Segment MoEngage integration")
                }
            )
        }

    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Button(
                    onClick = { handleInput(if (state.loggedIn) Input.Logout else Input.Login) }
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = if (state.loggedIn) "Log out" else "Log in "
                    )
                }
                Button(
                    onClick = { handleInput(Input.ViewExperiment) }
                ) {
                    Text(
                        style = MaterialTheme.typography.titleLarge,
                        text = "View Trips"
                    )
                }
            }
        }
    }
}