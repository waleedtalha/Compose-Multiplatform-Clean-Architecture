package com.app.cmpproject.presentation.screens.users

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import com.app.cmpproject.core.viewModel
import com.app.cmpproject.data.model.UserData
import com.app.cmpproject.presentation.components.AppScaffold
import com.app.cmpproject.presentation.components.ProgressIndicator
import com.app.cmpproject.presentation.components.UserItem
import kotlinx.coroutines.launch

object AllUsersScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val viewModel: AllUsersViewModel = viewModel()
        val usersState by viewModel.state.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }
        val coroutineScope = rememberCoroutineScope()
        viewModel.showAllUsers()

        AppScaffold(
            snackBarHost = { SnackbarHost(hostState = snackbarHostState) },
            topAppBar = {
                TopAppBar(title = {
                    Text(
                        text = "All User List",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                })
            },
            content = {
                when (usersState) {
                    is AllUsersScreenState.Loading -> {
                        if ((usersState as AllUsersScreenState.Loading).isLoading) {
                            ProgressIndicator()
                        }
                    }

                    is AllUsersScreenState.Success -> {
                        AllUserContent(
                            (usersState as AllUsersScreenState.Success).data.users
                        )
                    }

                    is AllUsersScreenState.Error -> {
                        coroutineScope.launch {
                            val errorMessage =
                                (usersState as AllUsersScreenState.Error).errorMessage.split("\n")
                                    .firstOrNull()
                            snackbarHostState.showSnackbar(
                                message = errorMessage ?: "",
                                duration = SnackbarDuration.Short,
                                actionLabel = "Dismiss"
                            )
                        }
                        println((usersState as AllUsersScreenState.Error).errorMessage)
                    }
                }
            }
        )
    }
}

@Composable
fun AllUserContent(userList: List<UserData>) {
    LazyColumn {
        items(userList) { user ->
            UserItem(user, onUserClick = {
            })
        }
    }
}