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

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.aksharadeepa.tutor.data.model.Chapter

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object SyllabusTracker : Screen("syllabus_tracker", "Syllabus", Icons.Filled.Book)
    object ChapterDetails : Screen("chapter_details/{chapterId}/{chapterName}/{chapterNumber}/{subject}", "Chapter Details", Icons.Filled.Book) {
        fun createRoute(chapterId: Int, chapterName: String, chapterNumber: Int, subject: String): String {
            return "chapter_details/$chapterId/$chapterName/$chapterNumber/$subject"
        }
    }
    object QuizMode : Screen("quiz_mode/{chapterId}", "Quiz", Icons.Filled.Quiz) {
        fun createRoute(chapterId: Int): String = "quiz_mode/$chapterId"
    }
    object StrengthMap : Screen("strength_map", "Strength Map", Icons.Filled.CheckCircle)
    object DailyGoal : Screen("daily_goal", "Daily Goal", Icons.Filled.Star)
}

val screens = listOf(
    Screen.SyllabusTracker,
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
                        // Navigate to chapter details instead of quiz
                        navController.navigate(
                            Screen.ChapterDetails.createRoute(
                                chapter.id,
                                chapter.chapterName,
                                chapter.chapterNumber,
                                chapter.subject
                            )
                        )
                    }
                )
            }

            composable(
                Screen.ChapterDetails.route,
                arguments = listOf(
                    navArgument("chapterId") { type = NavType.IntType },
                    navArgument("chapterName") { type = NavType.StringType },
                    navArgument("chapterNumber") { type = NavType.IntType },
                    navArgument("subject") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: 0
                val chapterName = backStackEntry.arguments?.getString("chapterName") ?: ""
                val chapterNumber = backStackEntry.arguments?.getInt("chapterNumber") ?: 0
                val subject = backStackEntry.arguments?.getString("subject") ?: ""

                val chapter = Chapter(
                    id = chapterId,
                    subject = subject,
                    chapterName = chapterName,
                    chapterNumber = chapterNumber,
                    isCompleted = false,
                    completionPercentage = 0,
                    totalTopics = 5
                )

                ChapterDetailsScreen(
                    chapter = chapter,
                    onBackClick = { navController.popBackStack() },
                    onStartQuiz = { selectedChapter ->
                        navController.navigate(Screen.QuizMode.createRoute(selectedChapter.id))
                    }
                )
            }

            composable(
                Screen.QuizMode.route,
                arguments = listOf(
                    navArgument("chapterId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: -1
                QuizModeScreen(
                    chapterId = chapterId,
                    onQuizComplete = {
                        navController.navigate(Screen.SyllabusTracker.route) {
                            popUpTo(Screen.SyllabusTracker.route) { inclusive = true }
                            launchSingleTop = true
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
