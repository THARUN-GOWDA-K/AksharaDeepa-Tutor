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
            // Science chapters - Biology
            Chapter(subject = "Science", chapterName = "Cell - Structure and Function", chapterNumber = 1, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Plant Tissues", chapterNumber = 2, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Animal Tissues", chapterNumber = 3, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Photosynthesis", chapterNumber = 4, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Respiration and Energy Flow", chapterNumber = 5, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Nervous System", chapterNumber = 6, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Endocrine System", chapterNumber = 7, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Reproduction in Plants", chapterNumber = 8, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Reproduction in Animals", chapterNumber = 9, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Heredity and Evolution", chapterNumber = 10, totalTopics = 5),
            
            // Science chapters - Physics
            Chapter(subject = "Science", chapterName = "Atoms and Molecules", chapterNumber = 11, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Motion", chapterNumber = 12, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Forces and Newton's Laws", chapterNumber = 13, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Work, Energy and Power", chapterNumber = 14, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Sound", chapterNumber = 15, totalTopics = 4),
            
            // Science chapters - Chemistry
            Chapter(subject = "Science", chapterName = "Chemical Reactions", chapterNumber = 16, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Acids, Bases and Salts", chapterNumber = 17, totalTopics = 5),
            Chapter(subject = "Science", chapterName = "Metals and Non-metals", chapterNumber = 18, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Carbon and Its Compounds", chapterNumber = 19, totalTopics = 4),
            Chapter(subject = "Science", chapterName = "Periodic Table", chapterNumber = 20, totalTopics = 5),
            
            // Math chapters
            Chapter(subject = "Math", chapterName = "Number System", chapterNumber = 1, totalTopics = 5),
            Chapter(subject = "Math", chapterName = "Polynomials", chapterNumber = 2, totalTopics = 4),
            Chapter(subject = "Math", chapterName = "Pair of Linear Equations", chapterNumber = 3, totalTopics = 4),
            Chapter(subject = "Math", chapterName = "Quadratic Equations", chapterNumber = 4, totalTopics = 4),
            Chapter(subject = "Math", chapterName = "Arithmetic Progressions", chapterNumber = 5, totalTopics = 5),
            Chapter(subject = "Math", chapterName = "Triangles", chapterNumber = 6, totalTopics = 5),
            Chapter(subject = "Math", chapterName = "Coordinate Geometry", chapterNumber = 7, totalTopics = 4),
            Chapter(subject = "Math", chapterName = "Circles", chapterNumber = 8, totalTopics = 4),
            Chapter(subject = "Math", chapterName = "Trigonometry", chapterNumber = 9, totalTopics = 5),
            Chapter(subject = "Math", chapterName = "Probability and Statistics", chapterNumber = 10, totalTopics = 4),
            
            // Social Studies - History chapters
            Chapter(subject = "Social Studies", chapterName = "The Rise of Nationalism in Europe", chapterNumber = 1, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "The French Revolution", chapterNumber = 2, totalTopics = 5),
            Chapter(subject = "Social Studies", chapterName = "Socialism in Europe and Russia", chapterNumber = 3, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Print Culture and Modern World", chapterNumber = 4, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Imperialism and India", chapterNumber = 5, totalTopics = 5),
            
            // Social Studies - Geography chapters
            Chapter(subject = "Social Studies", chapterName = "Resources and Development", chapterNumber = 6, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Water Resources", chapterNumber = 7, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Agriculture", chapterNumber = 8, totalTopics = 5),
            Chapter(subject = "Social Studies", chapterName = "Minerals and Energy Resources", chapterNumber = 9, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Manufacturing Industries", chapterNumber = 10, totalTopics = 4),
            
            // Social Studies - Civics chapters
            Chapter(subject = "Social Studies", chapterName = "Power Sharing", chapterNumber = 11, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Federalism", chapterNumber = 12, totalTopics = 5),
            Chapter(subject = "Social Studies", chapterName = "Democracy and Diversity", chapterNumber = 13, totalTopics = 4),
            Chapter(subject = "Social Studies", chapterName = "Gender, Religion and Caste", chapterNumber = 14, totalTopics = 5),
            Chapter(subject = "Social Studies", chapterName = "Political Parties and Electoral Politics", chapterNumber = 15, totalTopics = 4)
        )

        chapters.forEach { repository.insertChapter(it) }
    }


    private suspend fun initializeQuizQuestions() {
        val questions = listOf(
            // ========== SCIENCE - BIOLOGY (Chapters 1-10) ==========
            // Chapter 1: Cell - Structure and Function
            QuizQuestion(subject = "Science", chapterId = 1, questionText = "What is the basic unit of life?", optionA = "Atom", optionB = "Cell", optionC = "Molecule", optionD = "Organelle", correctAnswer = "B", explanation = "Cell is the basic structural and functional unit of life.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 1, questionText = "Which organelle is known as the powerhouse of the cell?", optionA = "Nucleus", optionB = "Mitochondria", optionC = "Golgi apparatus", optionD = "Ribosome", correctAnswer = "B", explanation = "Mitochondria produces ATP (energy) for the cell.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 1, questionText = "Which organelle stores genetic information?", optionA = "Mitochondria", optionB = "Chloroplast", optionC = "Nucleus", optionD = "Vacuole", correctAnswer = "C", explanation = "The nucleus contains DNA which stores genetic information.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 1, questionText = "Plant cells have but animal cells do not have?", optionA = "Mitochondria", optionB = "Cell wall", optionC = "Ribosomes", optionD = "Nucleus", correctAnswer = "B", explanation = "Plant cells have a cell wall outside the cell membrane.", difficulty = "Medium"),
            
            // Chapter 2: Plant Tissues
            QuizQuestion(subject = "Science", chapterId = 2, questionText = "Which tissue is responsible for growth in plants?", optionA = "Dermal tissue", optionB = "Meristematic tissue", optionC = "Ground tissue", optionD = "Vascular tissue", correctAnswer = "B", explanation = "Meristematic tissue contains cells that actively divide for plant growth.", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 2, questionText = "Phloem transports?", optionA = "Water and minerals", optionB = "Sugars and organic compounds", optionC = "Gases only", optionD = "Waste products only", correctAnswer = "B", explanation = "Phloem transports sugars (products of photosynthesis) from leaves to other parts.", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 2, questionText = "Xylem transports?", optionA = "Sugars and proteins", optionB = "Water and minerals", optionC = "Fats and amino acids", optionD = "Vitamins only", correctAnswer = "B", explanation = "Xylem transports water and minerals from roots upward.", difficulty = "Easy"),
            
            // Chapter 3: Animal Tissues
            QuizQuestion(subject = "Science", chapterId = 3, questionText = "Which type of tissue covers the body?", optionA = "Muscle tissue", optionB = "Epithelial tissue", optionC = "Nerve tissue", optionD = "Connective tissue", correctAnswer = "B", explanation = "Epithelial tissue forms the outer covering and lines internal organs.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 3, questionText = "Blood is a type of?", optionA = "Epithelial tissue", optionB = "Muscle tissue", optionC = "Connective tissue", optionD = "Nerve tissue", correctAnswer = "C", explanation = "Blood is a liquid connective tissue.", difficulty = "Medium"),
            
            // Chapter 4: Photosynthesis
            QuizQuestion(subject = "Science", chapterId = 4, questionText = "The equation for photosynthesis is?", optionA = "CO2 + H2O → Glucose + O2", optionB = "Glucose + O2 → CO2 + H2O", optionC = "6CO2 + 6H2O → C6H12O6 + 6O2 (light required)", optionD = "C6H12O6 → 2C2H5OH + 2CO2", correctAnswer = "C", explanation = "This is the complete photosynthesis equation (requires light).", difficulty = "Hard"),
            QuizQuestion(subject = "Science", chapterId = 4, questionText = "Photosynthesis occurs in which organelle?", optionA = "Mitochondria", optionB = "Chloroplast", optionC = "Nucleus", optionD = "Golgi apparatus", correctAnswer = "B", explanation = "Chloroplasts contain chlorophyll and are the site of photosynthesis.", difficulty = "Easy"),
            
            // Chapter 5: Respiration
            QuizQuestion(subject = "Science", chapterId = 5, questionText = "Cellular respiration produces?", optionA = "CO2 and water only", optionB = "ATP and CO2", optionC = "Glucose", optionD = "Oxygen", correctAnswer = "B", explanation = "Cellular respiration produces ATP (energy) and releases CO2.", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 5, questionText = "Anaerobic respiration occurs?", optionA = "In presence of oxygen", optionB = "In absence of oxygen", optionC = "Only in animals", optionD = "Only in plants", correctAnswer = "B", explanation = "Anaerobic respiration occurs without oxygen (fermentation).", difficulty = "Easy"),
            
            // Chapter 6: Nervous System
            QuizQuestion(subject = "Science", chapterId = 6, questionText = "The brain and spinal cord together form the?", optionA = "Peripheral nervous system", optionB = "Central nervous system", optionC = "Autonomic nervous system", optionD = "Somatic nervous system", correctAnswer = "B", explanation = "Brain and spinal cord form the central nervous system.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 6, questionText = "The functional unit of the nervous system is?", optionA = "Brain cell", optionB = "Neuron", optionC = "Ganglion", optionD = "Nucleus", correctAnswer = "B", explanation = "Neurons are the basic functional units that transmit signals.", difficulty = "Medium"),
            
            // Chapter 7: Endocrine System
            QuizQuestion(subject = "Science", chapterId = 7, questionText = "The master gland is?", optionA = "Thyroid", optionB = "Pancreas", optionC = "Pituitary gland", optionD = "Adrenal gland", correctAnswer = "C", explanation = "Pituitary gland controls other endocrine glands.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 7, questionText = "Insulin is produced by?", optionA = "Pancreas", optionB = "Thyroid", optionC = "Liver", optionD = "Kidney", correctAnswer = "A", explanation = "Pancreas produces insulin to regulate blood glucose.", difficulty = "Easy"),
            
            // Chapter 8: Plant Reproduction
            QuizQuestion(subject = "Science", chapterId = 8, questionText = "The male part of a flower is?", optionA = "Stigma", optionB = "Stamen", optionC = "Carpel", optionD = "Sepal", correctAnswer = "B", explanation = "Stamen is the male reproductive organ containing pollen.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 8, questionText = "Pollination is the transfer of?", optionA = "Seeds", optionB = "Pollen grains", optionC = "Nectar", optionD = "Fruits", correctAnswer = "B", explanation = "Pollination is the transfer of pollen from stamen to stigma.", difficulty = "Easy"),
            
            // Chapter 9: Animal Reproduction
            QuizQuestion(subject = "Science", chapterId = 9, questionText = "The process of cell division that produces gametes is?", optionA = "Mitosis", optionB = "Meiosis", optionC = "Binary fission", optionD = "Budding", correctAnswer = "B", explanation = "Meiosis produces gametes (sperm and egg) with half the chromosomes.", difficulty = "Hard"),
            QuizQuestion(subject = "Science", chapterId = 9, questionText = "During fertilization, a sperm and egg fuse to form?", optionA = "Gamete", optionB = "Zygote", optionC = "Embryo", optionD = "Fetus", correctAnswer = "B", explanation = "Zygote is formed when sperm and egg fuse.", difficulty = "Easy"),
            
            // Chapter 10: Heredity and Evolution
            QuizQuestion(subject = "Science", chapterId = 10, questionText = "The study of heredity is called?", optionA = "Evolution", optionB = "Genetics", optionC = "Paleontology", optionD = "Morphology", correctAnswer = "B", explanation = "Genetics is the study of heredity and inheritance.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 10, questionText = "Darwin's theory of evolution is based on?", optionA = "Inheritance only", optionB = "Adaptation only", optionC = "Natural selection and survival of the fittest", optionD = "Mutation only", correctAnswer = "C", explanation = "Evolution occurs through natural selection - survival of organisms best adapted.", difficulty = "Hard"),
            
            // ========== SCIENCE - PHYSICS (Chapters 11-15) ==========
            // Chapter 11: Atoms and Molecules
            QuizQuestion(subject = "Science", chapterId = 11, questionText = "The atomic mass unit is based on?", optionA = "Hydrogen", optionB = "Carbon-12", optionC = "Oxygen", optionD = "Nitrogen", correctAnswer = "B", explanation = "Atomic mass unit is 1/12th of the mass of Carbon-12 atom.", difficulty = "Hard"),
            QuizQuestion(subject = "Science", chapterId = 11, questionText = "A molecule is?", optionA = "A single atom", optionB = "Two or more atoms bonded together", optionC = "A subatomic particle", optionD = "Dissolved in water", correctAnswer = "B", explanation = "A molecule is formed when two or more atoms bond together.", difficulty = "Easy"),
            
            // Chapter 12: Motion
            QuizQuestion(subject = "Science", chapterId = 12, questionText = "Distance is?", optionA = "Vector quantity", optionB = "Always equal to displacement", optionC = "Scalar quantity", optionD = "Measured in degrees", correctAnswer = "C", explanation = "Distance is a scalar quantity (magnitude only).", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 12, questionText = "Velocity is the rate of change of?", optionA = "Distance", optionB = "Displacement", optionC = "Speed", optionD = "Acceleration", correctAnswer = "B", explanation = "Velocity is the rate of change of displacement in a direction.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 12, questionText = "Acceleration is?", optionA = "Change in distance", optionB = "Rate of change of velocity", optionC = "Same as speed", optionD = "Always positive", correctAnswer = "B", explanation = "Acceleration is the rate of change of velocity.", difficulty = "Medium"),
            
            // Chapter 13: Forces and Newton's Laws
            QuizQuestion(subject = "Science", chapterId = 13, questionText = "Newton's first law states?", optionA = "F = ma", optionB = "Action and reaction are equal", optionC = "An object continues in motion unless acted upon by force", optionD = "Gravity attracts all objects", correctAnswer = "C", explanation = "First law: Law of inertia - object in motion stays in motion unless external force acts.", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 13, questionText = "Newton's second law is?", optionA = "F = ma", optionB = "Energy = Force × Distance", optionC = "Velocity = Acceleration × Time", optionD = "Power = Energy / Time", correctAnswer = "A", explanation = "F = ma (Force = mass × acceleration)", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 13, questionText = "For every action there is?", optionA = "A greater reaction", optionB = "An equal and opposite reaction", optionC = "No reaction", optionD = "A reaction after some time", correctAnswer = "B", explanation = "Newton's third law: Action-reaction pairs are equal and opposite.", difficulty = "Easy"),
            
            // Chapter 14: Work, Energy, Power
            QuizQuestion(subject = "Science", chapterId = 14, questionText = "Work is defined as?", optionA = "Force only", optionB = "Force × Displacement × cos(θ)", optionC = "Energy only", optionD = "Power × Time", correctAnswer = "B", explanation = "Work = Force × Displacement × cos(angle between them)", difficulty = "Hard"),
            QuizQuestion(subject = "Science", chapterId = 14, questionText = "Kinetic energy is?", optionA = "PE = mgh", optionB = "KE = 1/2 mv²", optionC = "Energy of position", optionD = "Energy of heat", correctAnswer = "B", explanation = "Kinetic energy = 1/2 × mass × velocity²", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 14, questionText = "The SI unit of power is?", optionA = "Joule", optionB = "Watt", optionC = "Newton", optionD = "Pascal", correctAnswer = "B", explanation = "Power is measured in Watts (J/s).", difficulty = "Easy"),
            
            // Chapter 15: Sound
            QuizQuestion(subject = "Science", chapterId = 15, questionText = "Sound travels fastest in?", optionA = "Air", optionB = "Water", optionC = "Solids", optionD = "Vacuum", correctAnswer = "C", explanation = "Sound travels fastest through solids due to closer molecular arrangement.", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 15, questionText = "The frequency of sound is measured in?", optionA = "Decibels", optionB = "Hertz", optionC = "Meters", optionD = "Seconds", correctAnswer = "B", explanation = "Frequency is measured in Hertz (cycles per second).", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 15, questionText = "Ultrasonic sound has frequency?", optionA = "Less than 20 Hz", optionB = "Between 20-20000 Hz", optionC = "Greater than 20000 Hz", optionD = "Equal to 20000 Hz", correctAnswer = "C", explanation = "Ultrasonic sound is beyond the hearing range of humans (>20000 Hz).", difficulty = "Medium"),
            
            // ========== SCIENCE - CHEMISTRY (Chapters 16-20) ==========
            // Chapter 16: Chemical Reactions
            QuizQuestion(subject = "Science", chapterId = 16, questionText = "A chemical reaction where heat is released is?", optionA = "Endothermic", optionB = "Exothermic", optionC = "Reversible", optionD = "Irreversible", correctAnswer = "B", explanation = "Exothermic reactions release heat energy.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 16, questionText = "Combustion is a?", optionA = "Physical reaction", optionB = "Chemical reaction", optionC = "Nuclear reaction", optionD = "Enzymatic reaction", correctAnswer = "B", explanation = "Combustion is a chemical reaction involving burning of fuel in oxygen.", difficulty = "Easy"),
            
            // Chapter 17: Acids, Bases, Salts
            QuizQuestion(subject = "Science", chapterId = 17, questionText = "The pH of a neutral solution is?", optionA = "0", optionB = "7", optionC = "14", optionD = "10", correctAnswer = "B", explanation = "Neutral solutions have pH = 7 (neither acidic nor basic).", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 17, questionText = "Acids have pH?", optionA = "Less than 7", optionB = "Equal to 7", optionC = "Greater than 7", optionD = "Between 7-14", correctAnswer = "A", explanation = "Acidic solutions have pH < 7.", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 17, questionText = "Bases have pH?", optionA = "Less than 7", optionB = "Equal to 7", optionC = "Greater than 7", optionD = "Less than 0", correctAnswer = "C", explanation = "Basic (alkaline) solutions have pH > 7.", difficulty = "Easy"),
            
            // Chapter 18: Metals and Non-metals
            QuizQuestion(subject = "Science", chapterId = 18, questionText = "Metals are generally?", optionA = "Dull and brittle", optionB = "Shiny and malleable", optionC = "Gases at room temperature", optionD = "Soluble in water", correctAnswer = "B", explanation = "Metals are lustrous (shiny) and malleable (can be hammered).", difficulty = "Easy"),
            QuizQuestion(subject = "Science", chapterId = 18, questionText = "The most reactive metal is?", optionA = "Sodium", optionB = "Potassium", optionC = "Calcium", optionD = "Iron", correctAnswer = "B", explanation = "Potassium is one of the most reactive metals.", difficulty = "Hard"),
            
            // Chapter 19: Carbon and Compounds
            QuizQuestion(subject = "Science", chapterId = 19, questionText = "Methane is?", optionA = "CH2", optionB = "CH4", optionC = "C2H6", optionD = "C3H8", correctAnswer = "B", explanation = "Methane has the molecular formula CH4.", difficulty = "Medium"),
            QuizQuestion(subject = "Science", chapterId = 19, questionText = "Ethane has how many bonds between carbon atoms?", optionA = "Double bond", optionB = "Triple bond", optionC = "Single bond", optionD = "No bond", correctAnswer = "C", explanation = "Ethane (C2H6) has a single covalent bond between carbons.", difficulty = "Medium"),
            
            // Chapter 20: Periodic Table
            QuizQuestion(subject = "Science", chapterId = 20, questionText = "Which element is in Group 1, Period 3?", optionA = "Sodium", optionB = "Magnesium", optionC = "Aluminum", optionD = "Silicon", correctAnswer = "A", explanation = "Sodium (Na) is in Group 1 (alkali metals), Period 3.", difficulty = "Hard"),
            QuizQuestion(subject = "Science", chapterId = 20, questionText = "Noble gases are in which group?", optionA = "Group 1", optionB = "Group 13", optionC = "Group 17", optionD = "Group 18", correctAnswer = "D", explanation = "Noble gases (He, Ne, Ar) are in Group 18.", difficulty = "Medium"),
            
            // ========== MATHEMATICS (Chapters 1-10) ==========
            // Chapter 1: Number System
            QuizQuestion(subject = "Math", chapterId = 1, questionText = "√2 is?", optionA = "Rational number", optionB = "Irrational number", optionC = "Whole number", optionD = "Integer", correctAnswer = "B", explanation = "√2 cannot be expressed as p/q, so it's irrational.", difficulty = "Hard"),
            QuizQuestion(subject = "Math", chapterId = 1, questionText = "The square root of 144 is?", optionA = "10", optionB = "12", optionC = "14", optionD = "11", correctAnswer = "B", explanation = "12 × 12 = 144", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 1, questionText = "7/9 + 2/9 = ?", optionA = "9/18", optionB = "1", optionC = "14/81", optionD = "1/3", correctAnswer = "B", explanation = "7/9 + 2/9 = 9/9 = 1", difficulty = "Easy"),
            
            // Chapter 2: Polynomials
            QuizQuestion(subject = "Math", chapterId = 2, questionText = "What is the degree of the polynomial 2x² + 3x + 1?", optionA = "1", optionB = "2", optionC = "3", optionD = "0", correctAnswer = "B", explanation = "Degree is the highest power of x, which is 2.", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 2, questionText = "If x = 2, find the value of x² + 3x + 2?", optionA = "10", optionB = "12", optionC = "14", optionD = "16", correctAnswer = "B", explanation = "4 + 6 + 2 = 12", difficulty = "Easy"),
            
            // Chapter 3: Linear Equations
            QuizQuestion(subject = "Math", chapterId = 3, questionText = "Solve: 2x + 3 = 9. Find x", optionA = "2", optionB = "3", optionC = "4", optionD = "5", correctAnswer = "B", explanation = "2x = 6, so x = 3", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 3, questionText = "A pair of linear equations has?", optionA = "No solution", optionB = "One unique solution or infinite solutions", optionC = "Exactly 2 solutions", optionD = "Cannot have solutions", correctAnswer = "B", explanation = "Pair of linear equations can have unique, infinite, or no solutions.", difficulty = "Hard"),
            
            // Chapter 4: Quadratic Equations
            QuizQuestion(subject = "Math", chapterId = 4, questionText = "The standard form of quadratic equation is?", optionA = "ax + b = 0", optionB = "ax² + bx + c = 0", optionC = "ax³ + bx + c = 0", optionD = "a + b + c = 0", correctAnswer = "B", explanation = "Quadratic form: ax² + bx + c = 0 where a ≠ 0", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 4, questionText = "Using quadratic formula, discriminant is?", optionA = "b - 4ac", optionB = "b² - 4ac", optionC = "b² + 4ac", optionD = "4ac - b²", correctAnswer = "B", explanation = "Discriminant = b² - 4ac determines nature of roots.", difficulty = "Medium"),
            
            // Chapter 5: Arithmetic Progressions
            QuizQuestion(subject = "Math", chapterId = 5, questionText = "In AP: 2, 5, 8, 11..., the common difference is?", optionA = "2", optionB = "3", optionC = "4", optionD = "5", correctAnswer = "B", explanation = "Common difference = 5 - 2 = 3", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 5, questionText = "The nth term of an AP is?", optionA = "a + (n-1)d", optionB = "an + d", optionC = "a + nd", optionD = "a - (n-1)d", correctAnswer = "A", explanation = "an = a + (n-1)d where a is first term, d is common difference", difficulty = "Medium"),
            
            // Chapter 6: Triangles
            QuizQuestion(subject = "Math", chapterId = 6, questionText = "Sum of angles in a triangle is?", optionA = "90°", optionB = "180°", optionC = "270°", optionD = "360°", correctAnswer = "B", explanation = "Sum of all three angles in a triangle = 180°", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 6, questionText = "By SSS congruence, two triangles are congruent if?", optionA = "All angles are equal", optionB = "All three sides are equal", optionC = "Two sides are equal", optionD = "Base and height are equal", correctAnswer = "B", explanation = "SSS: Side-Side-Side congruence requires all three corresponding sides equal.", difficulty = "Medium"),
            
            // Chapter 7: Coordinate Geometry
            QuizQuestion(subject = "Math", chapterId = 7, questionText = "Distance between (0,0) and (3,4) is?", optionA = "5", optionB = "6", optionC = "7", optionD = "8", correctAnswer = "A", explanation = "d = √(3² + 4²) = √25 = 5", difficulty = "Medium"),
            QuizQuestion(subject = "Math", chapterId = 7, questionText = "The midpoint of (2,4) and (6,8) is?", optionA = "(3,5)", optionB = "(4,6)", optionC = "(5,7)", optionD = "(2,4)", correctAnswer = "B", explanation = "Midpoint = ((2+6)/2, (4+8)/2) = (4,6)", difficulty = "Easy"),
            
            // Chapter 8: Circles
            QuizQuestion(subject = "Math", chapterId = 8, questionText = "Circumference of a circle is?", optionA = "πr", optionB = "2πr", optionC = "πr²", optionD = "2πr²", correctAnswer = "B", explanation = "Circumference C = 2πr", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 8, questionText = "Area of a circle is?", optionA = "2πr", optionB = "πr", optionC = "πr²", optionD = "2πr²", correctAnswer = "C", explanation = "Area A = πr²", difficulty = "Easy"),
            
            // Chapter 9: Trigonometry
            QuizQuestion(subject = "Math", chapterId = 9, questionText = "sin(90°) = ?", optionA = "0", optionB = "1/2", optionC = "1", optionD = "√3/2", correctAnswer = "C", explanation = "sin(90°) = 1", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 9, questionText = "cos(0°) = ?", optionA = "0", optionB = "1", optionC = "-1", optionD = "√2", correctAnswer = "B", explanation = "cos(0°) = 1", difficulty = "Easy"),
            
            // Chapter 10: Statistics and Probability
            QuizQuestion(subject = "Math", chapterId = 10, questionText = "Mean of 2, 4, 6, 8 is?", optionA = "4", optionB = "5", optionC = "6", optionD = "7", correctAnswer = "B", explanation = "(2+4+6+8)/4 = 20/4 = 5", difficulty = "Easy"),
            QuizQuestion(subject = "Math", chapterId = 10, questionText = "Probability of getting heads in a coin flip is?", optionA = "0", optionB = "1/2", optionC = "1", optionD = "2", correctAnswer = "B", explanation = "Fair coin has 1 favorable outcome out of 2 possible outcomes.", difficulty = "Easy"),
            
            // ========== SOCIAL STUDIES (Chapters 1-15) ==========
            // Chapter 1: Rise of Nationalism
            QuizQuestion(subject = "Social Studies", chapterId = 1, questionText = "Nationalism in Europe arose due to?", optionA = "Religious wars", optionB = "Enlightenment ideas and nation-states", optionC = "Economic depression", optionD = "Political alliances", correctAnswer = "B", explanation = "Enlightenment promoted ideas of nation-states and national identity.", difficulty = "Medium"),
            
            // Chapter 2: French Revolution
            QuizQuestion(subject = "Social Studies", chapterId = 2, questionText = "The French Revolution began in?", optionA = "1776", optionB = "1789", optionC = "1801", optionD = "1750", correctAnswer = "B", explanation = "French Revolution started in 1789 with storming of Bastille on July 14.", difficulty = "Easy"),
            QuizQuestion(subject = "Social Studies", chapterId = 2, questionText = "The main cause of French Revolution was?", optionA = "Religious persecution", optionB = "Decline of agriculture", optionC = "Economic crisis and social inequality", optionD = "Foreign invasion", correctAnswer = "C", explanation = "Economic crisis, social inequality between estates, and Enlightenment ideas.", difficulty = "Medium"),
            QuizQuestion(subject = "Social Studies", chapterId = 2, questionText = "The French king during Revolution was?", optionA = "Louis XIII", optionB = "Louis XIV", optionC = "Louis XVI", optionD = "Louis XVII", correctAnswer = "C", explanation = "Louis XVI ruled during the Revolution and was executed in 1793.", difficulty = "Easy"),
            
            // Chapter 3: Socialism in Europe
            QuizQuestion(subject = "Social Studies", chapterId = 3, questionText = "Socialism advocates?", optionA = "Private ownership", optionB = "Collective ownership of means of production", optionC = "Individual wealth accumulation", optionD = "Free market economy", correctAnswer = "B", explanation = "Socialism promotes collective ownership and distribution of wealth.", difficulty = "Medium"),
            QuizQuestion(subject = "Social Studies", chapterId = 3, questionText = "The Communist Manifesto was written by?", optionA = "Lenin", optionB = "Stalin", optionC = "Marx and Engels", optionD = "Trotsky", correctAnswer = "C", explanation = "Karl Marx and Friedrich Engels wrote 'The Communist Manifesto' in 1848.", difficulty = "Medium"),
            
            // Chapter 4: Print Culture
            QuizQuestion(subject = "Social Studies", chapterId = 4, questionText = "The printing press was invented by?", optionA = "Leonardo da Vinci", optionB = "Johannes Gutenberg", optionC = "Benjamin Franklin", optionD = "Thomas Edison", correctAnswer = "B", explanation = "Johannes Gutenberg invented the printing press (~1440).", difficulty = "Easy"),
            QuizQuestion(subject = "Social Studies", chapterId = 4, questionText = "The first book printed by Gutenberg was?", optionA = "Bible", optionB = "Quran", optionC = "Vedas", optionD = "Iliad", correctAnswer = "A", explanation = "Gutenberg's Bible (c.1455) was the first major book printed.", difficulty = "Hard"),
            
            // Chapter 5: Imperialism and India
            QuizQuestion(subject = "Social Studies", chapterId = 5, questionText = "The British East India Company arrived in India in?", optionA = "1500", optionB = "1600", optionC = "1700", optionD = "1800", correctAnswer = "B", explanation = "EIC established presence in India in early 1600s.", difficulty = "Hard"),
            QuizQuestion(subject = "Social Studies", chapterId = 5, questionText = "The Sepoy Mutiny occurred in?", optionA = "1850", optionB = "1857", optionC = "1875", optionD = "1890", correctAnswer = "B", explanation = "The Sepoy Mutiny (Indian Rebellion) occurred in 1857.", difficulty = "Medium"),
            
            // Chapter 6: Resources and Development
            QuizQuestion(subject = "Social Studies", chapterId = 6, questionText = "Which type of resource is renewable?", optionA = "Coal", optionB = "Petroleum", optionC = "Forest", optionD = "Natural gas", correctAnswer = "C", explanation = "Forest is a renewable resource that can regenerate.", difficulty = "Easy"),
            QuizQuestion(subject = "Social Studies", chapterId = 6, questionText = "Sustainable development means?", optionA = "Unlimited growth", optionB = "Growth that meets present needs without harming future", optionC = "Environmental protection only", optionD = "Economic growth only", correctAnswer = "B", explanation = "Sustainable development balances economic, social, and environmental needs.", difficulty = "Medium"),
            
            // Chapter 7: Water Resources
            QuizQuestion(subject = "Social Studies", chapterId = 7, questionText = "What percentage of Earth's water is fresh water?", optionA = "97%", optionB = "3%", optionC = "50%", optionD = "10%", correctAnswer = "B", explanation = "Only about 3% of Earth's water is fresh water.", difficulty = "Easy"),
            QuizQuestion(subject = "Social Studies", chapterId = 7, questionText = "The largest river in India by discharge is?", optionA = "Ganga", optionB = "Brahmaputra", optionC = "Godavari", optionD = "Yamuna", correctAnswer = "B", explanation = "Brahmaputra has the highest water discharge in India.", difficulty = "Hard"),
            
            // Chapter 8: Agriculture
            QuizQuestion(subject = "Social Studies", chapterId = 8, questionText = "Green Revolution in India was initiated to?", optionA = "Protect forests", optionB = "Increase agricultural production", optionC = "Reduce pollution", optionD = "Expand industries", correctAnswer = "B", explanation = "Green Revolution (1960s) aimed to increase food grain production.", difficulty = "Medium"),
            QuizQuestion(subject = "Social Studies", chapterId = 8, questionText = "The main cash crop of India is?", optionA = "Rice", optionB = "Wheat", optionC = "Cotton", optionD = "Sugarcane", correctAnswer = "C", explanation = "Cotton is one of India's major cash crops.", difficulty = "Medium"),
            
            // Chapter 9: Minerals and Energy
            QuizQuestion(subject = "Social Studies", chapterId = 9, questionText = "India's leading mineral resource is?", optionA = "Gold", optionB = "Iron", optionC = "Diamond", optionD = "Copper", correctAnswer = "B", explanation = "India has significant iron ore reserves.", difficulty = "Hard"),
            QuizQuestion(subject = "Social Studies", chapterId = 9, questionText = "The most important source of energy in India is?", optionA = "Nuclear power", optionB = "Hydropower", optionC = "Coal", optionD = "Solar", correctAnswer = "C", explanation = "Coal is India's primary source of energy.", difficulty = "Medium"),
            
            // Chapter 10: Industries
            QuizQuestion(subject = "Social Studies", chapterId = 10, questionText = "IT capital of India is?", optionA = "Mumbai", optionB = "Bangalore", optionC = "Delhi", optionD = "Hyderabad", correctAnswer = "B", explanation = "Bangalore (now Bengaluru) is known as India's IT capital.", difficulty = "Easy"),
            
            // Chapter 11: Power Sharing
            QuizQuestion(subject = "Social Studies", chapterId = 11, questionText = "In democracy, power is shared among?", optionA = "One leader", optionB = "Executive, Legislative, Judicial branches", optionC = "Military only", optionD = "Business leaders", correctAnswer = "B", explanation = "Power sharing (separation of powers) among three branches of government.", difficulty = "Medium"),
            
            // Chapter 12: Federalism
            QuizQuestion(subject = "Social Studies", chapterId = 12, questionText = "India is a federal structure because?", optionA = "All states are identical", optionB = "Power is shared between central and state governments", optionC = "Central government has absolute power", optionD = "States have no authority", correctAnswer = "B", explanation = "Federalism divides powers between national and state governments.", difficulty = "Medium"),
            QuizQuestion(subject = "Social Studies", chapterId = 12, questionText = "India has how many states (as per 2024)?", optionA = "28", optionB = "29", optionC = "30", optionD = "31", correctAnswer = "B", explanation = "India has 28 states and 8 Union territories (after 2019 changes).", difficulty = "Hard"),
            
            // Chapter 13: Democracy and Diversity
            QuizQuestion(subject = "Social Studies", chapterId = 13, questionText = "Democracy means?", optionA = "Rule by one person", optionB = "Rule by wealthy people", optionC = "Rule by the people", optionD = "Rule by military", correctAnswer = "C", explanation = "Democracy means government by and for the people.", difficulty = "Easy"),
            QuizQuestion(subject = "Social Studies", chapterId = 13, questionText = "Religious diversity in democracy is?", optionA = "A problem", optionB = "Should be prohibited", optionC = "An asset if managed through inclusive politics", optionD = "Ignored", correctAnswer = "C", explanation = "Diversity enriches democracy when inclusive political practices exist.", difficulty = "Hard"),
            
            // Chapter 14: Gender, Religion, Caste
            QuizQuestion(subject = "Social Studies", chapterId = 14, questionText = "Caste system in India is?", optionA = "Still practiced traditionally", optionB = "Legally prohibited for discrimination", optionC = "Encouraged by government", optionD = "A religious mandate", correctAnswer = "B", explanation = "Discrimination based on caste is legally prohibited in independent India.", difficulty = "Medium"),
            QuizQuestion(subject = "Social Studies", chapterId = 14, questionText = "Gender equality in India means?", optionA = "Women should only work at home", optionB = "Equal rights, dignity, and opportunities for all genders", optionC = "Men should dominate", optionD = "No difference in roles", correctAnswer = "B", explanation = "Gender equality ensures equal rights and opportunities regardless of gender.", difficulty = "Easy"),
            
            // Chapter 15: Political Parties and Elections
            QuizQuestion(subject = "Social Studies", chapterId = 15, questionText = "India has a?", optionA = "Two-party system", optionB = "One-party system", optionC = "Multi-party system", optionD = "No political parties", correctAnswer = "C", explanation = "India has a multi-party democratic system.", difficulty = "Easy"),
            QuizQuestion(subject = "Social Studies", chapterId = 15, questionText = "Universal Adult Suffrage in India means?", optionA = "Only rich can vote", optionB = "All adult citizens have right to vote", optionC = "Only men can vote", optionD = "Voting is optional", correctAnswer = "B", explanation = "UAS gives voting rights to all adult citizens regardless of status.", difficulty = "Easy")
        )

        repository.insertQuestions(questions)
    }
}
