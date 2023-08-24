// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.1.0")
        classpath ("com.google.gms:google-services:4.3.15")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.43.2")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.0")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven {setUrl("https://clojars.org/repo/") }
        maven {setUrl("https://dl.bintray.com/amulyakhare/maven")}
    }
}