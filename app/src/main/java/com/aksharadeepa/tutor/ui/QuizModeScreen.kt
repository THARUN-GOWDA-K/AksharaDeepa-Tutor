package com.aksharadeepa.tutor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aksharadeepa.tutor.viewmodel.LocalRepository
import com.aksharadeepa.tutor.viewmodel.QuizViewModel
import com.aksharadeepa.tutor.viewmodel.TutorViewModelFactory

@Composable
fun QuizModeScreen(
    viewModel: QuizViewModel = viewModel(factory = TutorViewModelFactory(LocalRepository.current ?: throw Exception("Repository not provided"))),
    chapterId: Int = -1,
    onQuizComplete: () -> Unit = {}
) {
    val quizStarted by viewModel.quizStarted.collectAsState()
    val quizCompleted by viewModel.quizCompleted.collectAsState()
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val loading by viewModel.loading.collectAsState()

    if (chapterId != -1) {
        LaunchedEffect(chapterId) {
            if (!quizStarted && questions.isEmpty()) {
                viewModel.startQuiz(chapterId)
            }
        }
    }

    when {
        loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        quizCompleted -> {
            QuizResultScreen(
                viewModel = viewModel,
                onComplete = onQuizComplete
            )
        }
        quizStarted && questions.isNotEmpty() -> {
            QuizQuestionScreen(
                viewModel = viewModel,
                currentIndex = currentIndex,
                totalQuestions = questions.size
            )
        }
        chapterId != -1 && !quizStarted -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFACC8A2)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        else -> {
            QuizSelectionScreen(
                onQuizSelected = { selectedChapterId ->
                    viewModel.startQuiz(selectedChapterId)
                }
            )
        }
    }
}

@Composable
fun QuizQuestionScreen(
    viewModel: QuizViewModel,
    currentIndex: Int,
    totalQuestions: Int
) {
    val question = viewModel.getCurrentQuestion()
    val userAnswers by viewModel.userAnswers.collectAsState()
    val selectedAnswer = userAnswers[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFACC8A2))
            .padding(16.dp)
    ) {
        // Progress indicator
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Question ${currentIndex + 1} of $totalQuestions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            LinearProgressIndicator(
                progress = (currentIndex + 1) / totalQuestions.toFloat(),
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .height(6.dp),
                color = Color(0xFF1A2517)
            )
        }

        if (question != null) {
            // Question card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = question.questionText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    // Options
                    listOf(
                        "A" to question.optionA,
                        "B" to question.optionB,
                        "C" to question.optionC,
                        "D" to question.optionD
                    ).forEach { (option, text) ->
                        QuizOptionButton(
                            option = option,
                            text = text,
                            isSelected = selectedAnswer == option,
                            onClick = {
                                viewModel.selectAnswer(currentIndex, option)
                            }
                        )
                    }
                }
            }
        }

        // Navigation buttons
        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.previousQuestion() },
                enabled = currentIndex > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1A2517)
                ),
                modifier = Modifier.weight(1f)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Previous")
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }

            Spacer(modifier = Modifier.width(16.dp))

            if (currentIndex == totalQuestions - 1) {
                Button(
                    onClick = { viewModel.submitQuiz() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF4CAF50)
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Submit")
                }
            } else {
                Button(
                    onClick = { viewModel.nextQuestion() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1A2517)
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Next")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Filled.ArrowForward, contentDescription = "Next")
                }
            }
        }
    }
}

@Composable
fun QuizOptionButton(
    option: String,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF1A2517) else Color(0xFFE8F0E8),
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = "$option. $text",
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.fillMaxWidth(),
            textAlign = androidx.compose.ui.text.style.TextAlign.Start
        )
    }
}

@Composable
fun QuizResultScreen(
    viewModel: QuizViewModel,
    onComplete: () -> Unit
) {
    val questions by viewModel.questions.collectAsState()
    val userAnswers by viewModel.userAnswers.collectAsState()

    val correctAnswers = userAnswers.count { (index, answer) ->
        index < questions.size && answer == questions[index].correctAnswer
    }
    val score = (correctAnswers.toDouble() / questions.size) * 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Quiz Complete!",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                Text(
                    text = "${score.toInt()}%",
                    fontSize = 64.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1A2517),
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "$correctAnswers out of ${questions.size} correct",
                    fontSize = 18.sp,
                    color = Color(0xFF666666),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Button(
                    onClick = onComplete,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EA)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text("Back to Menu", fontSize = 16.sp)
                }
            }
        }
    }
}

@Composable
fun QuizSelectionScreen(onQuizSelected: (Int) -> Unit) {
    val subjects = listOf("Science", "Math", "Social Studies")
    val chaptersPerSubject = mapOf(
        "Science" to (1..20),
        "Math" to (21..30),
        "Social Studies" to (31..45)
    )

    var selectedSubject by remember { mutableStateOf("Science") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFACC8A2))
            .padding(16.dp)
    ) {
        Text(
            "Select Chapter for Quiz",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color(0xFF1A2517)
        )

        // Subject selection tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            subjects.forEach { subject ->
                Button(
                    onClick = { selectedSubject = subject },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (subject == selectedSubject) Color(0xFF1A2517) else Color(0xFFE8F0E8)
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        subject,
                        fontSize = 12.sp,
                        color = if (subject == selectedSubject) Color.White else Color.Black
                    )
                }
            }
        }

        // Chapter list
        val chapters = chaptersPerSubject[selectedSubject] ?: (1..20)
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(chapters.toList()) { chapterId ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onQuizSelected(chapterId) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Chapter $chapterId",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A2517)
                        )
                        Text(
                            "Take Quiz →",
                            fontSize = 12.sp,
                            color = Color(0xFF1A2517)
                        )
                    }
                }
            }
        }
    }
}
