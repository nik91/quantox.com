# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# To preserve the info Crashlytics needs for readable crash reports
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
-keep public class * extends java.lang.Exception

# For faster builds with ProGuard, exclude Crashlytics
-keep class com.crashlytics.** { *; }
-dontwarn com.crashlytics.**

-ignorewarnings
-keep class !com.karovic.nikola.themovieapp.** { *; }
-keep class com.squareup.okhttp3.** { *; }
-keep class retrofit.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-keep class com.google.code.gson.** { *; }
-keep class com.squareup.retrofit2.** { *; }
-keep class com.karovic.nikola.themovieapp.ui.** { *; }
-keep class com.karovic.nikola.themovieapp.rest.** { *; }
-keep class com.karovic.nikola.themovieapp.model.** { *; }
-keep class com.karovic.nikola.themovieapp.db.dao.** { *; }