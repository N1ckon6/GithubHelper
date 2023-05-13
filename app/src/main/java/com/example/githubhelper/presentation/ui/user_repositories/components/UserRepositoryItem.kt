@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.githubhelper.presentation.ui.user_repositories.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubhelper.domain.model.UserRepository

@Composable
fun UserRepositoryItem(
    modifier: Modifier,
    userRepository: UserRepository?,
    onRepositoryClick: () -> Unit
) {
    OutlinedCard(
        modifier = modifier,
        onClick = onRepositoryClick,
        border = BorderStroke(0.dp, Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = userRepository?.updated_at.toString(),
                modifier = Modifier.align(Alignment.End)
            )
            Text(
                text = userRepository?.name.toString()
            )
            Row {
                Icon(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_decline),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = userRepository?.language.toString()
                )
            }
            Row {
                Icon(
                    painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = userRepository?.stargazers_count.toString()
                )
            }
        }
    }
}

@Composable
@Preview
fun UserRepositoryItemPreview() {
    UserRepositoryItem(
        modifier = Modifier.fillMaxWidth(),
        userRepository = UserRepository(
            name = "android app",
            language = "Kotlin",
            stargazers_count = 10
        ),
        onRepositoryClick = {}
    )
}