package com.app.cmpproject.presentation.screens.users

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.app.cmpproject.core.BackHandler
import com.app.cmpproject.core.viewModel
import com.app.cmpproject.data.model.UserData
import com.app.cmpproject.presentation.components.AppScaffold
import com.app.cmpproject.presentation.components.ErrorScreen
import com.app.cmpproject.presentation.components.ProgressIndicator
import com.app.cmpproject.presentation.components.UserItem
import kotlinx.coroutines.launch

object AllUsersScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: AllUsersViewModel = viewModel()
        MainContent(viewModel)
    }

    @Composable
    fun MainContent(viewModel: AllUsersViewModel) {
        val usersState by viewModel.state.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            viewModel.showAllUsers()
        }

        Column {
            when (usersState) {
                is AllUsersScreenState.Loading -> {
                    ProgressIndicator()
                }

                is AllUsersScreenState.Idle -> {}

                is AllUsersScreenState.Success -> {
                    AllUserList(
                        (usersState as AllUsersScreenState.Success).data.users
                    )
                }

                is AllUsersScreenState.Error -> {
                    ErrorScreen((usersState as AllUsersScreenState.Error).errorMessage)
                }
            }
        }


        BackHandler(true, onBack = {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = "sds",
                    duration = SnackbarDuration.Short,
                    actionLabel = "Dismiss"
                )
            }
            navigator?.pop()
        })

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun AllUserList(userList: List<UserData>) {
        AppScaffold(
            topAppBar = {
                TopAppBar(title = {
                    Text(
                        text = "All User List",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                })
            },
            content = { contentPadding ->
                LazyColumn(
                    contentPadding = contentPadding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(userList) { user ->
                        UserItem(user, onUserClick = {
                        })
                    }
                }
            }
        )

    }
}