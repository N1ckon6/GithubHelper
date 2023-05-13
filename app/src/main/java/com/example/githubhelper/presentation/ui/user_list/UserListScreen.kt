package com.example.githubhelper.presentation.ui.user_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubhelper.common.Routes
import com.example.githubhelper.domain.model.User
import com.example.githubhelper.presentation.ui.user_list.components.UserItem

@SuppressLint("SuspiciousIndentation")
@Composable
fun UserListScreen(
    modifier: Modifier,
    navController: NavController
) {
    val viewModel: UserListViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.getUsersFromDb()
        viewModel.getUsersFromServ()
    }
    val state = viewModel.state.value
    UserListContent(
        modifier = modifier,
        state = state,
        onUserClick = {
            navController.navigate(Routes.UserRepositoriesRoutes.route + "/${it.login}" + "/${it.id}")
        }
    )
}

@Composable
fun UserListContent(
    modifier: Modifier,
    state: UserListState,
    onUserClick: (user: User) -> Unit
) {
    Box(
        modifier = modifier.background(Color.Gray)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            items(state.users) { user ->
                UserItem(
                    modifier = Modifier.fillMaxWidth(),
                    user = user,
                    onItemClick = { onUserClick(user) })
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
        if (state.error != null && state.users.isEmpty()) {
            Text(
                text = state.error.message ?: "",
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading && state.users.isEmpty()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Preview
@Composable
fun UserListScreenPreview() {
    UserListContent(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray, RoundedCornerShape(36.dp)),
        state = UserListState(),
        onUserClick = {}
    )

}