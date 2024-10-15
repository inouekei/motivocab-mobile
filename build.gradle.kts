// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
//    alias(libs.plugins.android.application) apply false
//    alias(libs.plugins.kotlin.android) apply false
//    id("com.google.devtools.ksp") version "1.9.0-1.0.13"
    id("com.android.application") version "8.6.0" apply false
    id("com.android.library") version "8.6.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}

buildscript {
    extra.apply {
        set("room_version", "2.6.0")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}