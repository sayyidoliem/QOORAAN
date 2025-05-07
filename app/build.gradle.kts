plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)//parcelable
    alias(libs.plugins.ksp)//new method of kapt
}

android {
    namespace = "com.olimhousestudio.qooraan"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.olimhousestudio.qooraan"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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
    implementation(libs.androidx.room.common.jvm)
    implementation(libs.play.services.location)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //kotlin coroutine
    implementation(libs.kotlinx.coroutines.android)
    //navigation
    implementation(libs.androidx.navigation.compose)
    //room core
    implementation(libs.androidx.room.runtime)
    //room with kotlin Symbol Processing (KSP)
    ksp(libs.androidx.room.compiler)
    //Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)
    //google permission
    implementation(libs.accompanist.permissions)
    //google service location
    implementation(libs.play.services.location)
    //hilt with work manager when using Kotlin.
    ksp(libs.androidx.hilt.compiler)
    //render web view in android
    implementation(libs.agentweb.core)
    //retrofit for network requests
    implementation(libs.retrofit2.retrofit)
    //retrofit with converter gson
    implementation(libs.converter.gson)
    //sharepreferenced core
    implementation(libs.chibatching.kotpref)
    //kotpref auto initialization module
    implementation(libs.kotpref.initializer)
    //kotpref support saving enum value and ordinal
    implementation(libs.enum.support)
    //media player android
    implementation(libs.player)
    //m3 material icon
    implementation(libs.androidx.material.icons.extended)
    //lottie compose animation
    implementation(libs.lottie.compose)
    //for async image
    implementation(libs.coil3.coil.compose)
}