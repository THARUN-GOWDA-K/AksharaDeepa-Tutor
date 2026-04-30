package com.aksharadeepa.tutor.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "chapters")
data class Chapter(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subject: String, // Science, Math, Social Studies
    val chapterName: String,
    val chapterNumber: Int,
    val isCompleted: Boolean = false,
    val completionPercentage: Int = 0, // 0-100
    val lastAccessedDate: String = "",
    val totalTopics: Int = 0
)

@Entity(tableName = "quiz_questions")
data class QuizQuestion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val subject: String,
    val chapterId: Int,
    val questionText: String,
    val optionA: String,
    val optionB: String,
    val optionC: String,
    val optionD: String,
    val correctAnswer: String, // A, B, C, D
    val explanation: String = "",
    val difficulty: String = "Medium" // Easy, Medium, Hard
)

@Entity(tableName = "quiz_attempts")
data class QuizAttempt(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val chapterId: Int,
    val subject: String,
    val questionsAttempted: Int,
    val correctAnswers: Int,
    val score: Double = 0.0, // percentage
    val attemptDate: String = "",
    val timeSpentSeconds: Int = 0
)

@Entity(tableName = "user_answers")
data class UserAnswer(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val quizAttemptId: Int,
    val questionId: Int,
    val userAnswer: String, // A, B, C, D
    val isCorrect: Boolean = false
)

@Entity(tableName = "progress")
data class Progress(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val chapterId: Int,
    val subject: String,
    val completionPercentage: Int = 0,
    val lastModified: String = ""
)

data class SubjectStrength(
    val subject: String,
    val score: Double, // percentage
    val chaptersCompleted: Int,
    val totalChapters: Int
)

data class DailyGoal(
    val goalDate: String,
    val targetTopicsCount: Int = 1,
    val completedTopicsCount: Int = 0,
    val isCompleted: Boolean = false
)
