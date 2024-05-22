plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.anarstore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.anarstore"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation("io.appwrite:sdk-for-android:5.1.0")


    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.0")

    // LiveData-State
    implementation("androidx.compose.runtime:runtime-livedata:1.6.0")

    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")

    // Koin
    implementation("dev.burnoo:cokoin:1.0.0")
    implementation("dev.burnoo:cokoin-android-viewmodel:1.0.0") // for Androidx ViewModel
    implementation("dev.burnoo:cokoin-android-navigation:1.0.0") // for Compose Navigation


    // Retrofit
    // server =>
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")


    // Room
    implementation("androidx.room:room-ktx:2.6.1")
    //noinspection KaptUsageInsteadOfKsp
    kapt("androidx.room:room-compiler:2.6.1")

    // System Ui Controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.27.0")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Lottie Animation
    implementation("com.airbnb.android:lottie-compose:5.2.0")

    //firebase
    //noinspection BomWithoutPlatform
    implementation("com.google.firebase:firebase-bom:32.8.1")
    implementation("com.google.firebase:firebase-auth-ktx:22.3.1")
    implementation("com.google.firebase:firebase-database:20.3.1")

    //appwrite
    implementation("io.appwrite:sdk-for-android:5.1.0")

    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")


}
kapt {
    correctErrorTypes = true
}



