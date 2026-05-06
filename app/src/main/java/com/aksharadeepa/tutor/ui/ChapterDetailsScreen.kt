package com.aksharadeepa.tutor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aksharadeepa.tutor.data.model.Chapter

// Concepts/Topics data for each chapter
val chapterConcepts = mapOf(
    1 to listOf("Cell membrane structure", "Nucleus and organelles", "Prokaryotic vs Eukaryotic", "Mitochondria function", "Photosynthesis in chloroplasts"),
    2 to listOf("Meristematic tissue", "Dermal tissue", "Vascular tissue - Xylem", "Vascular tissue - Phloem", "Ground tissue"),
    3 to listOf("Epithelial tissue types", "Connective tissue", "Muscle tissue types", "Nerve tissue", "Blood tissue composition"),
    4 to listOf("Light reactions", "Dark reactions (Calvin cycle)", "Chlorophyll and photosystems", "Photolysis of water", "Glucose synthesis"),
    5 to listOf("Aerobic respiration stages", "Glycolysis process", "Krebs cycle", "Electron transport chain", "Anaerobic respiration"),
    6 to listOf("Brain structure", "Spinal cord function", "Neuron structure", "Reflex arc", "Neurotransmitters"),
    7 to listOf("Pituitary gland", "Thyroid hormones", "Pancreas and insulin", "Adrenal glands", "Hormone regulation"),
    8 to listOf("Flower structure", "Pollination types", "Fertilization process", "Seed formation", "Fruit development"),
    9 to listOf("Gamete formation", "Meiosis stages", "Fertilization", "Embryo development", "Sexual reproduction advantages"),
    10 to listOf("DNA structure", "Genes and alleles", "Heredity patterns", "Evolution theory", "Natural selection"),
    11 to listOf("Atomic structure", "Electrons and shells", "Molecular bonds", "Atomic mass", "Isotopes"),
    12 to listOf("Distance and displacement", "Speed and velocity", "Acceleration", "Graphical analysis", "Equations of motion"),
    13 to listOf("Newton's first law", "Newton's second law", "Newton's third law", "Types of forces", "Friction and motion"),
    14 to listOf("Work calculation", "Kinetic energy", "Potential energy", "Power definition", "Energy conservation"),
    15 to listOf("Sound wave properties", "Speed of sound", "Frequency and wavelength", "Doppler effect", "Sound applications"),
    16 to listOf("Exothermic reactions", "Endothermic reactions", "Combustion", "Decomposition", "Synthesis reactions"),
    17 to listOf("Acid definition", "Base definition", "pH scale", "Neutralization reaction", "Indicators and pH"),
    18 to listOf("Metal properties", "Non-metal properties", "Reactivity series", "Oxidation states", "Corrosion"),
    19 to listOf("Alkanes structure", "Alkenes structure", "Alkynes structure", "Functional groups", "Organic reactions"),
    20 to listOf("Periodic table organization", "Groups and periods", "Atomic number trends", "Electronegativity", "Valence electrons"),
    21 to listOf("Natural numbers", "Whole numbers", "Integers", "Rational numbers", "Irrational numbers"),
    22 to listOf("Polynomial definition", "Degree of polynomial", "Polynomial operations", "Factorization", "Zeros of polynomial"),
    23 to listOf("Linear equation solutions", "Pair of equations", "Graphical method", "Substitution method", "Elimination method"),
    24 to listOf("Quadratic formula", "Discriminant", "Roots nature", "Completing square", "Factorization method"),
    25 to listOf("First term and common difference", "General term formula", "Sum of n terms", "Arithmetic series", "Applications"),
    26 to listOf("Congruence criteria", "Triangle properties", "Similarity criteria", "Angle theorems", "Side-angle relationships"),
    27 to listOf("Distance formula", "Section formula", "Area using coordinates", "Midpoint theorem", "Centroid of triangle"),
    28 to listOf("Circle equation", "Tangent to circle", "Chord properties", "Arc and sector", "Circle theorems"),
    29 to listOf("Trigonometric ratios", "Special angles", "Trigonometric identities", "Graphs of trig functions", "Applications"),
    30 to listOf("Mean calculation", "Median and mode", "Probability basics", "Compound events", "Conditional probability"),
    31 to listOf("Nationalism definition", "Nation-state concept", "Enlightenment ideas", "Factors causing nationalism", "European unification"),
    32 to listOf("Causes of revolution", "Key events 1789-1799", "Declaration of Rights", "Reign of terror", "Revolutionary impact"),
    33 to listOf("Socialist ideas", "Karl Marx concepts", "Communist manifesto", "Russian revolution", "Soviet system"),
    34 to listOf("Printing press invention", "First printed books", "Impact on literacy", "Scientific revolution", "Spread of ideas"),
    35 to listOf("British colonialism", "East India Company", "Sepoy mutiny", "Indian independence", "Colonial resistance"),
    36 to listOf("Renewable resources", "Non-renewable resources", "Conservation methods", "Sustainable development", "Environmental protection"),
    37 to listOf("Water cycle", "Water scarcity", "River systems", "Groundwater", "Water management"),
    38 to listOf("Crop types", "Green revolution", "Soil types", "Farming methods", "Agricultural zones"),
    39 to listOf("Mineral types", "Coal reserves", "Petroleum resources", "Metallic minerals", "Energy sources"),
    40 to listOf("Manufacturing sectors", "Location factors", "Industrial zones", "Production processes", "Economic impact"),
    41 to listOf("Power separation", "Three branches", "Checks and balances", "Local governance", "Representation"),
    42 to listOf("Union and states", "Concurrent powers", "Constitutional framework", "Amendment process", "Federal structure"),
    43 to listOf("Democracy principles", "Representation", "Participation", "Diversity management", "Inclusive practices"),
    44 to listOf("Caste system", "Gender roles", "Religious diversity", "Minority rights", "Social equality"),
    45 to listOf("Political parties", "Electoral system", "Voting process", "Party functions", "Democratic participation")
)

@Composable
fun ChapterDetailsScreen(
    chapter: Chapter,
    onBackClick: () -> Unit,
    onStartQuiz: (Chapter) -> Unit
) {
    val concepts = chapterConcepts[chapter.id] ?: emptyList()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFACC8A2))
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1A2517))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Chapter ${chapter.chapterNumber}",
                    fontSize = 12.sp,
                    color = Color(0xFFACC8A2),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = chapter.chapterName,
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Content
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Concepts/Topics Section
            item {
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
                        Text(
                            text = "Topics in this Chapter",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A2517),
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        concepts.forEachIndexed { index, concept ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Text(
                                    text = "${index + 1}.",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1A2517),
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = concept,
                                    fontSize = 14.sp,
                                    color = Color.Black,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Info Cards
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F7EC))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "📚 How to Study",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A2517),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "1. Review all topics listed above\n2. Take the quiz to test your knowledge\n3. Focus on topics you find challenging",
                            fontSize = 12.sp,
                            color = Color(0xFF333333)
                        )
                    }
                }
            }

            // Progress Info
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "✅ Completion Status",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1A2517),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        LinearProgressIndicator(
                            progress = chapter.completionPercentage / 100f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp),
                            color = Color(0xFF4CAF50),
                            trackColor = Color(0xFFE0E0E0)
                        )
                        Text(
                            text = "${chapter.completionPercentage}% Complete",
                            fontSize = 12.sp,
                            color = Color(0xFF666666),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(8.dp)) }
        }

        // Action Button
        Button(
            onClick = { onStartQuiz(chapter) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A2517)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Start Quiz", fontSize = 16.sp, color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
