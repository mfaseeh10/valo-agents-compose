plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)   // new plugin for KSP
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mfaseeh.compose.valorantagents"
    compileSdk = 34

    defaultConfig {
        applicationId = "mfaseeh.compose.valorantagents"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain {
           languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
    packagingOptions {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
}

dependencies {
    val moshi_version = "1.14.0"
    val retrofit_version = "2.9.0"
    val coil_version = "2.4.0"
    val hilt_version = "2.44.2"
    val room_version = "2.5.2"

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)

    // moshi
    implementation(libs.moshi.kotlin)
    // coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // splash
    implementation(libs.androidx.core.splashscreen)

    // hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)

    // room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.paging)

    // kotlin
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

//kapt {
//    javacOptions {
//        // These options are normally set automatically via the Hilt Gradle plugin, but we
//        // set them manually to workaround a bug in the Kotlin 1.5.20
//        option("-Adagger.fastInit=ENABLED")
//        option("-Adagger.hilt.android.internal.disableAndroidSuperclassValidation=true")
//    }
//}