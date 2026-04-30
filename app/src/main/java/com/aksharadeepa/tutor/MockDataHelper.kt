package com.aksharadeepa.tutor

import com.aksharadeepa.tutor.data.model.Chapter
import com.aksharadeepa.tutor.data.model.QuizQuestion
import com.aksharadeepa.tutor.data.repository.TutorRepository

class MockDataHelper(private val repository: TutorRepository) {

    suspend fun initializeMockData() {
        // Check if data already exists
        val chapters = repository.getAllChapters()
        if (chapters.isNotEmpty()) return

        // Initialize chapters
        initializeChapters()
        // Initialize quiz questions
        initializeQuizQuestions()
    }

    private suspend fun initializeChapters() {
        val chapters = listOf(
            // Science chapters
            Chapter(subject = "Science", chapterName = "Cell - Structure and Function", chapterNumber = 1),
            Chapter(subject = "Science", chapterName = "Plant Tissues", chapterNumber = 2),
            Chapter(subject = "Science", chapterName = "Animal Tissues", chapterNumber = 3),
            Chapter(subject = "Science", chapterName = "Atom and Molecules", chapterNumber = 4),
            Chapter(subject = "Science", chapterName = "Motion", chapterNumber = 5),
            
            // Math chapters
            Chapter(subject = "Math", chapterName = "Number System", chapterNumber = 1),
            Chapter(subject = "Math", chapterName = "Polynomials", chapterNumber = 2),
            Chapter(subject = "Math", chapterName = "Pair of Linear Equations", chapterNumber = 3),
            Chapter(subject = "Math", chapterName = "Triangles", chapterNumber = 4),
            Chapter(subject = "Math", chapterName = "Circles", chapterNumber = 5),
            
            // Social Studies chapters
            Chapter(subject = "Social Studies", chapterName = "The French Revolution", chapterNumber = 1),
            Chapter(subject = "Social Studies", chapterName = "Socialism in Europe", chapterNumber = 2),
            Chapter(subject = "Social Studies", chapterName = "Nationalism in India", chapterNumber = 3),
            Chapter(subject = "Social Studies", chapterName = "The Making of Indian Constitution", chapterNumber = 4),
            Chapter(subject = "Social Studies", chapterName = "Electoral Politics", chapterNumber = 5)
        )

        chapters.forEach { repository.insertChapter(it) }
    }

    private suspend fun initializeQuizQuestions() {
        val questions = listOf(
            // Science - Cell questions
            QuizQuestion(
                subject = "Science",
                chapterId = 1,
                questionText = "What is the basic unit of life?",
                optionA = "Atom",
                optionB = "Cell",
                optionC = "Molecule",
                optionD = "Electron",
                correctAnswer = "B",
                explanation = "Cell is the basic unit of life. All living organisms are made up of cells.",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Science",
                chapterId = 1,
                questionText = "Which organelle is known as the powerhouse of the cell?",
                optionA = "Nucleus",
                optionB = "Golgi apparatus",
                optionC = "Mitochondria",
                optionD = "Ribosome",
                correctAnswer = "C",
                explanation = "Mitochondria is known as the powerhouse of the cell because it produces ATP (energy).",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Science",
                chapterId = 1,
                questionText = "What is the function of the nucleus?",
                optionA = "Produce energy",
                optionB = "Control cell activities and store genetic information",
                optionC = "Synthesize proteins",
                optionD = "Transport molecules",
                correctAnswer = "B",
                explanation = "The nucleus controls all cell activities and stores genetic information in the form of DNA.",
                difficulty = "Medium"
            ),
            QuizQuestion(
                subject = "Science",
                chapterId = 1,
                questionText = "Which type of cell does not have a nucleus?",
                optionA = "Animal cell",
                optionB = "Plant cell",
                optionC = "Prokaryotic cell",
                optionD = "Eukaryotic cell",
                correctAnswer = "C",
                explanation = "Prokaryotic cells like bacteria do not have a nucleus.",
                difficulty = "Medium"
            ),
            QuizQuestion(
                subject = "Science",
                chapterId = 1,
                questionText = "Which of these is found only in plant cells?",
                optionA = "Mitochondria",
                optionB = "Chloroplast",
                optionC = "Ribosome",
                optionD = "Golgi apparatus",
                correctAnswer = "B",
                explanation = "Chloroplasts are found only in plant cells and are responsible for photosynthesis.",
                difficulty = "Easy"
            ),

            // Science - Motion questions
            QuizQuestion(
                subject = "Science",
                chapterId = 5,
                questionText = "What is velocity?",
                optionA = "Rate of change of distance",
                optionB = "Rate of change of displacement",
                optionC = "Speed of an object",
                optionD = "Distance traveled in a direction",
                correctAnswer = "B",
                explanation = "Velocity is the rate of change of displacement in a specific direction.",
                difficulty = "Medium"
            ),
            QuizQuestion(
                subject = "Science",
                chapterId = 5,
                questionText = "An object moving in a circle at constant speed has acceleration because:",
                optionA = "Its speed is changing",
                optionB = "Its direction is changing",
                optionC = "Both speed and direction are changing",
                optionD = "It has no acceleration",
                correctAnswer = "B",
                explanation = "Even at constant speed, circular motion involves continuous change in direction, hence acceleration.",
                difficulty = "Hard"
            ),

            // Math - Number System
            QuizQuestion(
                subject = "Math",
                chapterId = 6,
                questionText = "What is the square root of 144?",
                optionA = "10",
                optionB = "12",
                optionC = "14",
                optionD = "11",
                correctAnswer = "B",
                explanation = "12 × 12 = 144, so √144 = 12",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Math",
                chapterId = 6,
                questionText = "What is 7/9 + 2/9?",
                optionA = "9/18",
                optionB = "9/9",
                optionC = "14/81",
                optionD = "1/3",
                correctAnswer = "B",
                explanation = "7/9 + 2/9 = (7+2)/9 = 9/9 = 1",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Math",
                chapterId = 6,
                questionText = "Which of the following is a prime number?",
                optionA = "15",
                optionB = "17",
                optionC = "21",
                optionD = "25",
                correctAnswer = "B",
                explanation = "17 is prime (only divisible by 1 and itself). 15=3×5, 21=3×7, 25=5×5",
                difficulty = "Medium"
            ),

            // Math - Triangles
            QuizQuestion(
                subject = "Math",
                chapterId = 9,
                questionText = "What is the sum of angles in a triangle?",
                optionA = "90°",
                optionB = "180°",
                optionC = "270°",
                optionD = "360°",
                correctAnswer = "B",
                explanation = "The sum of all three angles in any triangle is always 180°.",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Math",
                chapterId = 9,
                questionText = "In a right triangle, if one angle is 90°, what is the sum of the other two angles?",
                optionA = "90°",
                optionB = "180°",
                optionC = "270°",
                optionD = "360°",
                correctAnswer = "A",
                explanation = "Since the total is 180° and one angle is 90°, the other two must sum to 90°.",
                difficulty = "Medium"
            ),

            // Social Studies - French Revolution
            QuizQuestion(
                subject = "Social Studies",
                chapterId = 11,
                questionText = "In which year did the French Revolution begin?",
                optionA = "1776",
                optionB = "1789",
                optionC = "1801",
                optionD = "1750",
                correctAnswer = "B",
                explanation = "The French Revolution began in 1789 with the storming of the Bastille on July 14.",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Social Studies",
                chapterId = 11,
                questionText = "What was the main cause of the French Revolution?",
                optionA = "Foreign invasion",
                optionB = "Economic crisis, social inequality, and Enlightenment ideas",
                optionC = "Religious persecution",
                optionD = "Decline of agriculture",
                correctAnswer = "B",
                explanation = "The Revolution was caused by severe economic crisis, social inequality between estates, and Enlightenment ideas about liberty and equality.",
                difficulty = "Medium"
            ),
            QuizQuestion(
                subject = "Social Studies",
                chapterId = 11,
                questionText = "Who was the French king during the Revolution?",
                optionA = "Louis XIII",
                optionB = "Louis XIV",
                optionC = "Louis XVI",
                optionD = "Louis XVII",
                correctAnswer = "C",
                explanation = "Louis XVI was the King of France during the French Revolution. He was executed in 1793.",
                difficulty = "Easy"
            ),

            // Social Studies - Nationalism in India
            QuizQuestion(
                subject = "Social Studies",
                chapterId = 13,
                questionText = "Who was the first President of independent India?",
                optionA = "Pandit Jawaharlal Nehru",
                optionB = "Dr. Rajendra Prasad",
                optionC = "Sardar Vallabhbhai Patel",
                optionD = "Maulana Abul Kalam Azad",
                correctAnswer = "B",
                explanation = "Dr. Rajendra Prasad was the first President of the Indian Republic.",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Social Studies",
                chapterId = 13,
                questionText = "When did India gain independence?",
                optionA = "1945",
                optionB = "1947",
                optionC = "1950",
                optionD = "1942",
                correctAnswer = "B",
                explanation = "India gained independence on August 15, 1947, ending British colonial rule.",
                difficulty = "Easy"
            ),
            QuizQuestion(
                subject = "Social Studies",
                chapterId = 13,
                questionText = "Who gave the slogan 'Swaraj'?",
                optionA = "Mahatma Gandhi",
                optionB = "Bal Gangadhar Tilak",
                optionC = "Jawaharlal Nehru",
                optionD = "Subhas Chandra Bose",
                correctAnswer = "B",
                explanation = "Bal Gangadhar Tilak, an Indian freedom fighter, gave the slogan 'Swaraj' meaning self-rule.",
                difficulty = "Medium"
            )
        )

        repository.insertQuestions(questions)
    }
}
