// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    repositories {
        mavenCentral()
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}