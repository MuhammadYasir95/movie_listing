plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("plugin.serialization") version "1.5.21"
//    kotlin("plugin.serialization") version "1.9.0"
}

android {
    namespace = "com.example.movielisting"
    compileSdk = 33


    packaging {
        resources.excludes.add("META-INF/versions/9/previous-compilation-data.bin")
    }

    defaultConfig {
        applicationId = "com.example.movielisting"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    val retrofit_version = "2.9.0"
    val gson_version = "2.8.9"
    val okhttp_version = "3.14.9"

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    implementation ("com.github.bumptech.glide:glide:4.15.1")
    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.google.code.gson:gson:$gson_version")
    implementation("com.squareup.okhttp3:okhttp:$okhttp_version")

//    implementation ("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.6.0-RC")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
