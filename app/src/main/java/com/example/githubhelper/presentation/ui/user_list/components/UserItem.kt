package com.example.githubhelper.presentation.ui.user_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.githubhelper.R
import com.example.githubhelper.domain.model.User

@Composable
fun UserItem(
    user: User,
    onItemClick: (User) -> Unit
) {
    Card(
        Modifier.fillMaxWidth().clickable { onItemClick(user) },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colors.onPrimary)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = rememberAsyncImagePainter(user.avatar_url),
                contentDescription = ""
            )
            Text(
                text = user.login,
                fontSize = 24.sp,
                fontWeight = FontWeight(600),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
@Preview
fun UserItemPreview() {
    UserItem(user = User(avatar_url = "", login = "Login", ownerId = 1), onItemClick = {})
}