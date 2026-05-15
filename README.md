# Akshara-Deepa Tutor - Android App

A self-study companion application for 10th-grade SSLC students designed to help track syllabus, take quizzes, monitor strength, and maintain daily learning goals.

## Features

### 1. **Syllabus Tracker**
- View complete chapter checklist for Science, Math, and Social Studies
- Mark chapters as complete with progress tracking
- Visual progress bars showing completion percentage
- Subject-wise organization

### 2. **Quiz Mode**
- 5-question daily mock quizzes
- Pre-loaded questions with simulated data (50-100 questions per subject)
- Multiple choice format (A, B, C, D)
- Instant feedback with detailed explanations
- Progress tracking and score calculation

### 3. **Strength Map**
- Visual representation of performance across all subjects
- Color-coded strength indicators (Green: 80%+, Orange: 60-80%, Red: <60%)
- Chapter completion statistics
- Subject-wise performance metrics

### 4. **Daily Goal**
- Set and track daily learning targets
- Visual progress indicator
- Motivational reminders
- Goal completion tracking

## Technical Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Repository Pattern
- **Database**: Room (Local SQLite)
- **Navigation**: Jetpack Navigation
- **Coroutines**: For asynchronous operations
- **Target API**: 34 (Android 14)
- **Minimum API**: 21 (Android 5.0)

## Project Structure

```
app/
├── src/main/
│   ├── java/com/aksharadeepa/tutor/
│   │   ├── data/
│   │   │   ├── database/       # Room DAO and Database
│   │   │   ├── model/          # Data models
│   │   │   └── repository/     # Repository pattern
│   │   ├── viewmodel/          # MVVM ViewModels
│   │   ├── ui/                 # Jetpack Compose screens
│   │   ├── MainActivity.kt
│   │   └── MockDataHelper.kt
│   ├── res/
│   │   └── values/             # String resources
│   └── AndroidManifest.xml
└── build.gradle.kts            # App dependencies

build.gradle.kts                 # Project-level gradle
settings.gradle.kts              # Project settings
```

## Setup Instructions

### Prerequisites
- Android Studio (Electric Eel or later)
- JDK 8 or higher
- Android SDK (API 34 recommended)

### Installation

1. **Open the project in Android Studio**
   - File > Open > Select the project directory

2. **Sync Gradle**
   - Android Studio should automatically prompt to sync gradle files
   - If not: File > Sync Now

3. **Configure SDK**
   - If SDK is not configured, go to Tools > SDK Manager
   - Install Android SDK Platform 34 and Build Tools 34.0.0

4. **Create/Connect Device or Emulator**
   - For testing on an actual device: Enable USB Debugging
   - For emulator: Tools > Device Manager > Create Virtual Device

5. **Build the project**
   - Build > Rebuild Project (or Ctrl+B)

6. **Run the app**
   - Run > Run 'app' (or Shift+F10)
   - Select your device/emulator from the popup

## Key Components

### ViewModels
- **SyllabusTrackerViewModel**: Manages chapter data and progress
- **QuizViewModel**: Handles quiz state and scoring
- **StrengthMapViewModel**: Calculates and displays subject strengths
- **DailyGoalViewModel**: Manages daily learning goals

### Database
- **Room Database**: 5 entities (Chapter, QuizQuestion, QuizAttempt, UserAnswer, Progress)
- **DAOs**: Separate data access objects for each entity
- **Pre-populated Data**: 50+ mock questions across 3 subjects

### UI Screens
- **SyllabusTrackerScreen**: Chapter management with progress bars
- **QuizModeScreen**: Question display and answer selection
- **StrengthMapScreen**: Subject performance visualization
- **DailyGoalScreen**: Daily goal tracking and motivation

## Features Implementation Details

### Offline Functionality
- All data is stored locally in Room database
- App works 100% offline once syllabus is loaded
- No internet connection required after initial setup

### Progress Synchronization
- Progress updates instantly across all screens
- Quiz scores automatically update subject strength map
- Chapter completion reflected in syllabus tracker

### Scoring Algorithm
- Formula: (Correct Answers / Total Questions) × 100
- Scores tracked per attempt
- Average performance calculated per subject

## Testing

To test the app:

1. **Test Syllabus Tracker**
   - Navigate to Syllabus tab
   - Switch between subjects
   - Mark chapters as complete

2. **Test Quiz Mode**
   - Click on a chapter
   - Answer 5 questions
   - Verify score calculation
   - Review correct answers

3. **Test Strength Map**
   - Take multiple quizzes
   - Check strength updates
   - Verify color coding

4. **Test Daily Goal**
   - Complete daily goal
   - Verify goal completion animation
   - Reset and try again

## Build Variants

- **Debug**: Includes debugging symbols, no minification
- **Release**: Minified, optimized for production

To build release APK:
```bash
./gradlew bundleRelease
```

## Error Troubleshooting

### Gradle Build Issues
```
Solution: 
1. File > Invalidate Caches > Invalidate and Restart
2. Delete .gradle folder and rebuild
```

### Database Errors
```
Solution:
1. App uses fallbackToDestructiveMigration
2. Clear app data: Settings > Apps > Akshara-Deepa Tutor > Clear Cache & Data
3. Reinstall app
```

### Compose Issues
```
Solution:
1. Ensure Compose compiler version matches (1.5.3)
2. Update Android Studio to latest version
3. Clear gradle cache
```

## Future Enhancements

- Firebase integration for progress sync across devices
- Personalized recommendations based on weak areas
- Timed practice tests
- Analytics dashboard
- Push notifications for daily reminders
- Offline mode with sync capabilities
- Custom question creation by teachers

## License

Educational Project - MindMatrix VTU Internship Program

## Support

For issues or questions:
1. Check the troubleshooting section
2. Verify gradle sync is complete
3. Ensure SDK is properly configured
4. Clear app cache and reinstall

## Author
THARUN GOWDA K - 1VE22CS172
SRI VENKATESHWRA COLLEGE OF ENGINEERING
Developed as part of MindMatrix VTU Internship Program
Project Title: Android App Development using GenAI - Akshara-Deepa Tutor (Education)
