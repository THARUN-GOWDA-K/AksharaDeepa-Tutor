# This is a configuration file for ProGuard.
# http://proguard.sourceforge.net/index.html#manual/usage.html

-keep class com.aksharadeepa.tutor.data.** { *; }
-keep class com.aksharadeepa.tutor.viewmodel.** { *; }

-keepclassmembers class * {
    *** *(...);
}

# Preserve line numbers for debugging stack traces.
-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
# -renamesourcefileattribute SourceFile
