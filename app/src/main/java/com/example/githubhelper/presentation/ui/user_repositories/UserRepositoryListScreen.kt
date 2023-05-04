package com.example.githubhelper.presentation.ui.user_repositories

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubhelper.presentation.ui.user_repositories.components.UserRepositoryItem

@Composable
fun UserRepositoryListScreen(
    modifier: Modifier,
    login: String,
    ownerId: Int
) {
    val viewModel: UserRepositoriesViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getReposFromServer(login, ownerId)
        viewModel.getReposFromDb(ownerId)
    }
    val state = viewModel.state.value
    val uriHandle = LocalUriHandler.current
    UserRepositoryContent(
        modifier = modifier,
        state = state,
        onClick = { uriHandle.openUri(it) }
    )
}

@Composable
fun UserRepositoryContent(
    modifier: Modifier,
    state: UserRepositoriesState,
    onClick: (url: String) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.userRepositories) { repository ->
                UserRepositoryItem(
                    userRepository = repository,
                    onRepositoryClick = {
                        onClick(it.url)
                    }
                )
            }
        }
        if (state.error != null) {
            Text(
                text = state.error.toString(),
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
@Preview
fun UserReposScreenPreview() {
    UserRepositoryContent(
        modifier = Modifier.fillMaxSize(),
        state = UserRepositoriesState(),
        onClick = {}
    )
}