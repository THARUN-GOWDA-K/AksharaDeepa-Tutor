package com.aksharadeepa.tutor.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.aksharadeepa.tutor.data.model.*

@Dao
interface ChapterDao {
    @Insert
    suspend fun insertChapter(chapter: Chapter): Long

    @Update
    suspend fun updateChapter(chapter: Chapter)

    @Delete
    suspend fun deleteChapter(chapter: Chapter)

    @Query("SELECT * FROM chapters WHERE subject = :subject ORDER BY chapterNumber ASC")
    suspend fun getChaptersBySubject(subject: String): List<Chapter>

    @Query("SELECT * FROM chapters WHERE id = :chapterId")
    suspend fun getChapterById(chapterId: Int): Chapter?

    @Query("SELECT * FROM chapters")
    suspend fun getAllChapters(): List<Chapter>

    @Query("UPDATE chapters SET completionPercentage = :percentage, isCompleted = :completed WHERE id = :chapterId")
    suspend fun updateChapterProgress(chapterId: Int, percentage: Int, completed: Boolean)
}

@Dao
interface QuizQuestionDao {
    @Insert
    suspend fun insertQuestion(question: QuizQuestion): Long

    @Insert
    suspend fun insertQuestions(questions: List<QuizQuestion>)

    @Update
    suspend fun updateQuestion(question: QuizQuestion)

    @Query("SELECT * FROM quiz_questions WHERE chapterId = :chapterId ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestions(chapterId: Int, limit: Int = 5): List<QuizQuestion>

    @Query("SELECT * FROM quiz_questions WHERE subject = :subject ORDER BY RANDOM() LIMIT :limit")
    suspend fun getRandomQuestionsBySubject(subject: String, limit: Int = 5): List<QuizQuestion>

    @Query("SELECT * FROM quiz_questions WHERE id = :questionId")
    suspend fun getQuestionById(questionId: Int): QuizQuestion?

    @Query("SELECT COUNT(*) FROM quiz_questions WHERE chapterId = :chapterId")
    suspend fun getQuestionCountByChapter(chapterId: Int): Int
}

@Dao
interface QuizAttemptDao {
    @Insert
    suspend fun insertAttempt(attempt: QuizAttempt): Long

    @Update
    suspend fun updateAttempt(attempt: QuizAttempt)

    @Query("SELECT * FROM quiz_attempts ORDER BY attemptDate DESC")
    suspend fun getAllAttempts(): List<QuizAttempt>

    @Query("SELECT * FROM quiz_attempts WHERE chapterId = :chapterId ORDER BY attemptDate DESC")
    suspend fun getAttemptsByChapter(chapterId: Int): List<QuizAttempt>

    @Query("SELECT AVG(score) FROM quiz_attempts WHERE chapterId = :chapterId")
    suspend fun getAverageScoreByChapter(chapterId: Int): Double?

    @Query("SELECT * FROM quiz_attempts WHERE attemptDate = :date")
    suspend fun getAttemptsForDate(date: String): List<QuizAttempt>
}

@Dao
interface UserAnswerDao {
    @Insert
    suspend fun insertAnswer(answer: UserAnswer): Long

    @Insert
    suspend fun insertAnswers(answers: List<UserAnswer>)

    @Query("SELECT * FROM user_answers WHERE quizAttemptId = :attemptId")
    suspend fun getAnswersByAttempt(attemptId: Int): List<UserAnswer>

    @Query("SELECT * FROM user_answers WHERE questionId = :questionId AND quizAttemptId = :attemptId")
    suspend fun getUserAnswerByQuestion(attemptId: Int, questionId: Int): UserAnswer?
}

@Dao
interface ProgressDao {
    @Insert
    suspend fun insertProgress(progress: Progress): Long

    @Update
    suspend fun updateProgress(progress: Progress)

    @Query("SELECT * FROM progress WHERE chapterId = :chapterId")
    suspend fun getProgressByChapter(chapterId: Int): Progress?

    @Query("SELECT * FROM progress WHERE subject = :subject")
    suspend fun getProgressBySubject(subject: String): List<Progress>

    @Query("SELECT SUM(completionPercentage) / COUNT(*) FROM progress WHERE subject = :subject")
    suspend fun getAverageProgressBySubject(subject: String): Double?
}
