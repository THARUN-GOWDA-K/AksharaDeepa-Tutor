package com.aksharadeepa.tutor.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aksharadeepa.tutor.data.model.*

@Database(
    entities = [
        Chapter::class,
        QuizQuestion::class,
        QuizAttempt::class,
        UserAnswer::class,
        Progress::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AksharaDeepaTutorDatabase : RoomDatabase() {
    abstract fun chapterDao(): ChapterDao
    abstract fun quizQuestionDao(): QuizQuestionDao
    abstract fun quizAttemptDao(): QuizAttemptDao
    abstract fun userAnswerDao(): UserAnswerDao
    abstract fun progressDao(): ProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AksharaDeepaTutorDatabase? = null

        fun getDatabase(context: Context): AksharaDeepaTutorDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AksharaDeepaTutorDatabase::class.java,
                    "aksharadeepa_tutor.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
