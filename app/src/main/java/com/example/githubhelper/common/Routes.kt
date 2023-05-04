package com.example.githubhelper.common

sealed class Routes(val route: String) {
    object UserListRoutes: Routes("user_list")
    object UserRepositoriesRoutes: Routes("repository_list")
}