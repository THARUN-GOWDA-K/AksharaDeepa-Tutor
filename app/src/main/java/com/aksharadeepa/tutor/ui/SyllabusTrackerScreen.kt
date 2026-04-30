package com.aksharadeepa.tutor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aksharadeepa.tutor.data.model.Chapter
import com.aksharadeepa.tutor.viewmodel.SyllabusTrackerViewModel

@Composable
fun SyllabusTrackerScreen(
    viewModel: SyllabusTrackerViewModel = viewModel(),
    onChapterSelected: (Chapter) -> Unit = {}
) {
    val chapters by viewModel.chapters.collectAsState()
    val selectedSubject by viewModel.selectedSubject.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val subjects = viewModel.getSubjects()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(
            "Syllabus Tracker",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Subject tabs
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            subjects.forEach { subject ->
                SubjectTab(
                    subject = subject,
                    isSelected = subject == selectedSubject,
                    onClick = { viewModel.selectSubject(subject) }
                )
            }
        }

        // Chapters list
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
                items(chapters) { chapter ->
                    ChapterCard(
                        chapter = chapter,
                        onChapterClick = {
                            onChapterSelected(chapter)
                            viewModel.markChapterComplete(chapter.id)
                        },
                        onProgressUpdate = { percentage ->
                            viewModel.updateChapterProgress(chapter.id, percentage)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SubjectTab(subject: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) Color(0xFF6200EA) else Color(0xFFE0E0E0),
            contentColor = if (isSelected) Color.White else Color.Black
        ),
        modifier = Modifier
            .height(40.dp)
            .wrapContentWidth()
    ) {
        Text(subject, fontSize = 12.sp)
    }
}

@Composable
fun ChapterCard(
    chapter: Chapter,
    onChapterClick: () -> Unit,
    onProgressUpdate: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onChapterClick() },
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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Chapter ${chapter.chapterNumber}",
                        fontSize = 14.sp,
                        color = Color(0xFF6200EA),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = chapter.chapterName,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Icon(
                    imageVector = if (chapter.isCompleted) Icons.Filled.CheckCircle else Icons.Filled.RadioButtonUnchecked,
                    contentDescription = "Status",
                    tint = if (chapter.isCompleted) Color(0xFF4CAF50) else Color(0xFFB0B0B0),
                    modifier = Modifier.size(32.dp)
                )
            }

            // Progress bar
            LinearProgressIndicator(
                progress = chapter.completionPercentage / 100f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .height(6.dp),
                color = Color(0xFF6200EA),
                trackColor = Color(0xFFE0E0E0)
            )

            Text(
                text = "${chapter.completionPercentage}% Complete",
                fontSize = 12.sp,
                color = Color(0xFF999999),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
