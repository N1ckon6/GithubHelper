@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.githubhelper.presentation.ui.user_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.githubhelper.domain.model.User

@Composable
fun UserItem(
    modifier: Modifier,
    user: User,
    onItemClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colors.onPrimary),
        onClick = onItemClick,
        border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = rememberAsyncImagePainter(user.avatar_url),
                contentDescription = ""
            )
            user.login?.let {
                Text(
                    text = it,
                    fontSize = 24.sp,
                    fontWeight = FontWeight(600),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun UserItemPreview() {
    UserItem(
        modifier = Modifier.fillMaxWidth(),
        user = User(avatar_url = "", login = "Login", id = 1),
        onItemClick = {}
    )
}