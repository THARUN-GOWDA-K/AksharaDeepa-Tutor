package com.aksharadeepa.tutor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aksharadeepa.tutor.data.model.SubjectStrength
import com.aksharadeepa.tutor.viewmodel.LocalRepository
import com.aksharadeepa.tutor.viewmodel.StrengthMapViewModel
import com.aksharadeepa.tutor.viewmodel.TutorViewModelFactory

@Composable
fun StrengthMapScreen(
    viewModel: StrengthMapViewModel = viewModel(factory = TutorViewModelFactory(LocalRepository.current ?: throw Exception("Repository not provided")))
) {
    val subjectStrengths by viewModel.subjectStrengths.collectAsState()
    val loading by viewModel.loading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFACC8A2))
            .padding(16.dp)
    ) {
        Text(
            "Strength Map",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            "Track your progress across subjects",
            fontSize = 14.sp,
            color = Color(0xFF666666),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(subjectStrengths) { subject ->
                    StrengthMapCard(subject)
                }

                item {
                    Button(
                        onClick = { viewModel.refreshStrengthMap() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1A2517)
                        )
                    ) {
                        Text("Refresh", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun StrengthMapCard(strength: SubjectStrength) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = strength.subject,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${strength.score.toInt()}%",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = getScoreColor(strength.score)
                )
            }

            // Score bar
            LinearProgressIndicator(
                progress = (strength.score / 100.0).toFloat(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = getScoreColor(strength.score),
                trackColor = Color(0xFFE8F0E8)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Chapters: ${strength.chaptersCompleted}/${strength.totalChapters}",
                    fontSize = 12.sp,
                    color = Color(0xFF666666)
                )
                Text(
                    text = "${(strength.chaptersCompleted.toDouble() / strength.totalChapters) * 100}% Done",
                    fontSize = 12.sp,
                    color = Color(0xFF666666)
                )
            }
        }
    }
}

fun getScoreColor(score: Double): Color {
    return when {
        score >= 80 -> Color(0xFF4CAF50) // Green
        score >= 60 -> Color(0xFFFF9800) // Orange
        else -> Color(0xFFF44336)       // Red
    }
}
