package com.aksharadeepa.tutor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.aksharadeepa.tutor.viewmodel.QuizViewModel

@Composable
fun QuizModeScreen(
    viewModel: QuizViewModel = viewModel(),
    chapterId: Int = -1,
    onQuizComplete: () -> Unit = {}
) {
    val quizStarted by viewModel.quizStarted.collectAsState()
    val quizCompleted by viewModel.quizCompleted.collectAsState()
    val questions by viewModel.questions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val loading by viewModel.loading.collectAsState()

    LaunchedEffect(chapterId) {
        if (!quizStarted && chapterId != -1) {
            viewModel.startQuiz(chapterId)
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
        else -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                Text("No quiz available")
            }
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
            .background(Color(0xFFF5F5F5))
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
                color = Color(0xFF6200EA)
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
                    containerColor = Color(0xFF6200EA)
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
                        containerColor = Color(0xFF6200EA)
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
            containerColor = if (isSelected) Color(0xFF6200EA) else Color(0xFFE0E0E0),
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
                    color = Color(0xFF6200EA),
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
