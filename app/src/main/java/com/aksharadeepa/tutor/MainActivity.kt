package com.aksharadeepa.tutor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.ViewModelProvider
import com.aksharadeepa.tutor.data.database.AksharaDeepaTutorDatabase
import com.aksharadeepa.tutor.data.repository.TutorRepository
import com.aksharadeepa.tutor.ui.AppNavigation
import com.aksharadeepa.tutor.viewmodel.TutorViewModelFactory
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize database and repository
        val db = AksharaDeepaTutorDatabase.getDatabase(applicationContext)
        val repository = TutorRepository(
            chapterDao = db.chapterDao(),
            quizQuestionDao = db.quizQuestionDao(),
            quizAttemptDao = db.quizAttemptDao(),
            userAnswerDao = db.userAnswerDao(),
            progressDao = db.progressDao()
        )

        // Load mock data
        GlobalScope.launch {
            val mockDataHelper = MockDataHelper(repository)
            mockDataHelper.initializeMockData()
        }

        setContent {
            MaterialTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
