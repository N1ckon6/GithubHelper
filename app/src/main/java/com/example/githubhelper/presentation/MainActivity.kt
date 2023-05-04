package com.example.githubhelper.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.githubhelper.common.Routes
import com.example.githubhelper.presentation.theme.GithubHelperTheme
import com.example.githubhelper.presentation.ui.user_list.UserListScreen
import com.example.githubhelper.presentation.ui.user_repositories.UserRepositoryListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubHelperTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                            navController = navController,
                            startDestination = Routes.UserListRoutes.route
                    ) {
                        composable(route = Routes.UserListRoutes.route) {
                            UserListScreen(
                                    modifier = Modifier
                                            .fillMaxSize()
                                            .background(Color.Cyan, RoundedCornerShape(36.dp)),
                                    navController = navController
                            )
                        }
                        composable(
                                route = "${Routes.UserRepositoriesRoutes.route}/{login}/{ownerId}",
                                arguments = listOf(
                                        navArgument("login") { type = NavType.StringType },
                                        navArgument("ownerId") { type = NavType.IntType }
                                )
                        ) {
                            val login = it.arguments?.getString("login")
                            val ownerId = it.arguments?.getInt("ownerId")
                            if (login != null && ownerId != null) {
                                UserRepositoryListScreen(
                                    modifier = Modifier.fillMaxSize(),
                                    login = login,
                                    ownerId = ownerId
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}