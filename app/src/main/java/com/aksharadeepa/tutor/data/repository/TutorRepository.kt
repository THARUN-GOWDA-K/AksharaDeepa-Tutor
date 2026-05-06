package com.aksharadeepa.tutor.data.repository

import com.aksharadeepa.tutor.data.database.*
import com.aksharadeepa.tutor.data.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TutorRepository(
    private val chapterDao: ChapterDao,
    private val quizQuestionDao: QuizQuestionDao,
    private val quizAttemptDao: QuizAttemptDao,
    private val userAnswerDao: UserAnswerDao,
    private val progressDao: ProgressDao
) {
    // Chapter operations
    suspend fun insertChapter(chapter: Chapter) = chapterDao.insertChapter(chapter)

    suspend fun updateChapter(chapter: Chapter) = chapterDao.updateChapter(chapter)

    suspend fun getChaptersBySubject(subject: String): List<Chapter> {
        return chapterDao.getChaptersBySubject(subject)
    }

    suspend fun getChapterById(chapterId: Int): Chapter? {
        return chapterDao.getChapterById(chapterId)
    }

    suspend fun getAllChapters(): List<Chapter> {
        return chapterDao.getAllChapters()
    }

    suspend fun updateChapterProgress(chapterId: Int, percentage: Int, completed: Boolean) {
        chapterDao.updateChapterProgress(chapterId, percentage, completed)
    }

    // Quiz Question operations
    suspend fun insertQuestions(questions: List<QuizQuestion>) {
        quizQuestionDao.insertQuestions(questions)
    }

    suspend fun getRandomQuestions(chapterId: Int, limit: Int = 5): List<QuizQuestion> {
        return quizQuestionDao.getRandomQuestions(chapterId, limit)
    }

    suspend fun getRandomQuestionsBySubject(subject: String, limit: Int = 5): List<QuizQuestion> {
        return quizQuestionDao.getRandomQuestionsBySubject(subject, limit)
    }

    suspend fun getQuestionById(questionId: Int): QuizQuestion? {
        return quizQuestionDao.getQuestionById(questionId)
    }

    // Quiz Attempt operations
    suspend fun insertAttempt(attempt: QuizAttempt): Long {
        return quizAttemptDao.insertAttempt(attempt)
    }

    suspend fun updateAttempt(attempt: QuizAttempt) {
        quizAttemptDao.updateAttempt(attempt)
    }

    suspend fun getAllAttempts(): List<QuizAttempt> {
        return quizAttemptDao.getAllAttempts()
    }

    suspend fun getAttemptsByChapter(chapterId: Int): List<QuizAttempt> {
        return quizAttemptDao.getAttemptsByChapter(chapterId)
    }

    suspend fun getAverageScoreByChapter(chapterId: Int): Double? {
        return quizAttemptDao.getAverageScoreByChapter(chapterId)
    }

    suspend fun getAttemptsForDate(date: String): List<QuizAttempt> {
        return quizAttemptDao.getAttemptsForDate(date)
    }

    // User Answer operations
    suspend fun insertAnswers(answers: List<UserAnswer>) {
        userAnswerDao.insertAnswers(answers)
    }

    suspend fun getAnswersByAttempt(attemptId: Int): List<UserAnswer> {
        return userAnswerDao.getAnswersByAttempt(attemptId)
    }

    suspend fun getUserAnswerByQuestion(attemptId: Int, questionId: Int): UserAnswer? {
        return userAnswerDao.getUserAnswerByQuestion(attemptId, questionId)
    }

    // Progress operations
    suspend fun updateProgress(progress: Progress) {
        progressDao.updateProgress(progress)
    }

    suspend fun getProgressByChapter(chapterId: Int): Progress? {
        return progressDao.getProgressByChapter(chapterId)
    }

    suspend fun getProgressBySubject(subject: String): List<Progress> {
        return progressDao.getProgressBySubject(subject)
    }

    suspend fun getAverageProgressBySubject(subject: String): Double? {
        return progressDao.getAverageProgressBySubject(subject)
    }

    // Combined operations
    suspend fun calculateSubjectStrengths(): List<SubjectStrength> {
        val subjects = listOf("Science", "Math", "Social Studies")
        return subjects.map { subject ->
            val chapters = chapterDao.getChaptersBySubject(subject)
            val completedChapters = chapters.count { it.isCompleted }
            
            // Calculate average score from all quiz attempts in this subject's chapters
            var totalScore = 0.0
            var attemptCount = 0
            
            chapters.forEach { chapter ->
                val averageForChapter = quizAttemptDao.getAverageScoreByChapter(chapter.id)
                if (averageForChapter != null) {
                    totalScore += averageForChapter
                    attemptCount++
                }
            }
            
            val averageScore = if (attemptCount > 0) (totalScore / attemptCount) else 0.0
            
            SubjectStrength(
                subject = subject,
                score = averageScore,
                chaptersCompleted = completedChapters,
                totalChapters = chapters.size
            )
        }
    }
}
