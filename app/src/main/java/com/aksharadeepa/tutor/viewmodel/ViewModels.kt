package com.aksharadeepa.tutor.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.aksharadeepa.tutor.data.model.*
import com.aksharadeepa.tutor.data.repository.TutorRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class SyllabusTrackerViewModel(private val repository: TutorRepository) : ViewModel() {

    private val _chapters = MutableStateFlow<List<Chapter>>(emptyList())
    val chapters: StateFlow<List<Chapter>> = _chapters.asStateFlow()

    private val _selectedSubject = MutableStateFlow("Science")
    val selectedSubject: StateFlow<String> = _selectedSubject.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        loadChapters()
    }

    private fun loadChapters() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val chaptersList = repository.getChaptersBySubject(_selectedSubject.value)
                _chapters.value = chaptersList
            } finally {
                _loading.value = false
            }
        }
    }

    fun selectSubject(subject: String) {
        _selectedSubject.value = subject
        loadChapters()
    }

    fun updateChapterProgress(chapterId: Int, percentage: Int) {
        viewModelScope.launch {
            val chapter = repository.getChapterById(chapterId)
            if (chapter != null) {
                repository.updateChapterProgress(chapterId, percentage, percentage == 100)
                loadChapters()
            }
        }
    }

    fun markChapterComplete(chapterId: Int) {
        viewModelScope.launch {
            repository.updateChapterProgress(chapterId, 100, true)
            loadChapters()
        }
    }

    fun getSubjects(): List<String> = listOf("Science", "Math", "Social Studies")
}

class QuizViewModel(private val repository: TutorRepository) : ViewModel() {

    private val _questions = MutableStateFlow<List<QuizQuestion>>(emptyList())
    val questions: StateFlow<List<QuizQuestion>> = _questions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _userAnswers = MutableStateFlow<MutableMap<Int, String>>(mutableMapOf())
    val userAnswers: StateFlow<Map<Int, String>> = _userAnswers.asStateFlow()

    private val _quizStarted = MutableStateFlow(false)
    val quizStarted: StateFlow<Boolean> = _quizStarted.asStateFlow()

    private val _quizCompleted = MutableStateFlow(false)
    val quizCompleted: StateFlow<Boolean> = _quizCompleted.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    private var currentAttemptId: Long = -1

    fun startQuiz(chapterId: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val quizQuestions = repository.getRandomQuestions(chapterId, 5)
                _questions.value = quizQuestions
                _currentQuestionIndex.value = 0
                _quizStarted.value = true
                _quizCompleted.value = false
                _userAnswers.value.clear()

                // Create quiz attempt record
                val attempt = QuizAttempt(
                    chapterId = chapterId,
                    subject = "Unknown",
                    questionsAttempted = quizQuestions.size,
                    correctAnswers = 0,
                    attemptDate = LocalDate.now().toString()
                )
                currentAttemptId = repository.insertAttempt(attempt)
            } finally {
                _loading.value = false
            }
        }
    }

    fun startDailyQuiz(subject: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val quizQuestions = repository.getRandomQuestionsBySubject(subject, 5)
                _questions.value = quizQuestions
                _currentQuestionIndex.value = 0
                _quizStarted.value = true
                _quizCompleted.value = false
                _userAnswers.value.clear()

                // Create quiz attempt record
                val attempt = QuizAttempt(
                    chapterId = -1,
                    subject = subject,
                    questionsAttempted = quizQuestions.size,
                    correctAnswers = 0,
                    attemptDate = LocalDate.now().toString()
                )
                currentAttemptId = repository.insertAttempt(attempt)
            } finally {
                _loading.value = false
            }
        }
    }

    fun selectAnswer(questionIndex: Int, answer: String) {
        val newAnswers = _userAnswers.value.toMutableMap()
        newAnswers[questionIndex] = answer
        _userAnswers.value = newAnswers
    }

    fun nextQuestion() {
        if (_currentQuestionIndex.value < _questions.value.size - 1) {
            _currentQuestionIndex.value += 1
        }
    }

    fun previousQuestion() {
        if (_currentQuestionIndex.value > 0) {
            _currentQuestionIndex.value -= 1
        }
    }

    fun submitQuiz(): QuizResult {
        viewModelScope.launch {
            val correctCount = calculateCorrectAnswers()
            val score = (correctCount.toDouble() / _questions.value.size) * 100

            // Save quiz attempt
            if (currentAttemptId != -1L) {
                val attempt = QuizAttempt(
                    id = currentAttemptId.toInt(),
                    chapterId = -1,
                    subject = "Unknown",
                    questionsAttempted = _questions.value.size,
                    correctAnswers = correctCount,
                    score = score,
                    attemptDate = LocalDate.now().toString()
                )
                repository.updateAttempt(attempt)

                // Save user answers
                val userAnswersList = mutableListOf<UserAnswer>()
                for ((index, answer) in _userAnswers.value) {
                    userAnswersList.add(
                        UserAnswer(
                            quizAttemptId = currentAttemptId.toInt(),
                            questionId = _questions.value[index].id,
                            userAnswer = answer,
                            isCorrect = answer == _questions.value[index].correctAnswer
                        )
                    )
                }
                repository.insertAnswers(userAnswersList)
            }

            _quizCompleted.value = true
        }

        return QuizResult(
            totalQuestions = _questions.value.size,
            correctAnswers = calculateCorrectAnswers(),
            score = (calculateCorrectAnswers().toDouble() / _questions.value.size) * 100
        )
    }

    private fun calculateCorrectAnswers(): Int {
        return _userAnswers.value.count { (index, answer) ->
            index < _questions.value.size && answer == _questions.value[index].correctAnswer
        }
    }

    fun getCurrentQuestion(): QuizQuestion? {
        return if (_currentQuestionIndex.value < _questions.value.size) {
            _questions.value[_currentQuestionIndex.value]
        } else null
    }

    fun resetQuiz() {
        _quizStarted.value = false
        _quizCompleted.value = false
        _currentQuestionIndex.value = 0
        _userAnswers.value.clear()
        _questions.value = emptyList()
    }
}

data class QuizResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val score: Double
)

class StrengthMapViewModel(private val repository: TutorRepository) : ViewModel() {

    private val _subjectStrengths = MutableStateFlow<List<SubjectStrength>>(emptyList())
    val subjectStrengths: StateFlow<List<SubjectStrength>> = _subjectStrengths.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        loadStrengthMap()
    }

    private fun loadStrengthMap() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val strengths = repository.calculateSubjectStrengths()
                _subjectStrengths.value = strengths
            } finally {
                _loading.value = false
            }
        }
    }

    fun refreshStrengthMap() {
        loadStrengthMap()
    }
}

class DailyGoalViewModel(private val repository: TutorRepository) : ViewModel() {

    private val _dailyGoal = MutableStateFlow<DailyGoal?>(null)
    val dailyGoal: StateFlow<DailyGoal?> = _dailyGoal.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        loadDailyGoal()
    }

    private fun loadDailyGoal() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val today = LocalDate.now().toString()
                val goal = DailyGoal(
                    goalDate = today,
                    targetTopicsCount = 1,
                    completedTopicsCount = 0,
                    isCompleted = false
                )
                _dailyGoal.value = goal
            } finally {
                _loading.value = false
            }
        }
    }

    fun completeGoal() {
        val current = _dailyGoal.value
        if (current != null) {
            _dailyGoal.value = current.copy(
                completedTopicsCount = current.completedTopicsCount + 1,
                isCompleted = current.completedTopicsCount + 1 >= current.targetTopicsCount
            )
        }
    }

    fun resetGoal() {
        loadDailyGoal()
    }
}

class TutorViewModelFactory(private val repository: TutorRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SyllabusTrackerViewModel::class.java -> SyllabusTrackerViewModel(repository) as T
            QuizViewModel::class.java -> QuizViewModel(repository) as T
            StrengthMapViewModel::class.java -> StrengthMapViewModel(repository) as T
            DailyGoalViewModel::class.java -> DailyGoalViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
