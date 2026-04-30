package com.aksharadeepa.tutor.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object SyllabusTracker : Screen("syllabus_tracker", "Syllabus", Icons.Filled.Book)
    object QuizMode : Screen("quiz_mode", "Quiz", Icons.Filled.Quiz)
    object StrengthMap : Screen("strength_map", "Strength Map", Icons.Filled.CheckCircle)
    object DailyGoal : Screen("daily_goal", "Daily Goal", Icons.Filled.Star)
}

val screens = listOf(
    Screen.SyllabusTracker,
    Screen.QuizMode,
    Screen.StrengthMap,
    Screen.DailyGoal
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SyllabusTracker.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.SyllabusTracker.route) {
                SyllabusTrackerScreen(
                    onChapterSelected = { chapter ->
                        // Navigate to quiz
                        navController.navigate(Screen.QuizMode.route)
                    }
                )
            }

            composable(Screen.QuizMode.route) {
                QuizModeScreen(
                    chapterId = -1,
                    onQuizComplete = {
                        navController.navigate(Screen.SyllabusTracker.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

            composable(Screen.StrengthMap.route) {
                StrengthMapScreen()
            }

            composable(Screen.DailyGoal.route) {
                DailyGoalScreen()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title, fontSize = androidx.compose.material3.MaterialTheme.typography.labelSmall.fontSize) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
