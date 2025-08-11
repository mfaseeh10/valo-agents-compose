plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "mfaseeh.compose.valorantagents"
    compileSdk = 35

    defaultConfig {
        applicationId = "mfaseeh.compose.valorantagents"
        minSdk = 21
        targetSdk = 35
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
    packaging {
        resources.excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
    
    lint {
        disable.add("NullSafeMutableLiveData")
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementation(project(":shared"))
    
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.compose.ui)
    implementation(libs.androidx.material)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.compose)

    // coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // splash
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.navigation.compose)
    
    // kotlin serialization for navigation args
    implementation(libs.kotlinx.serialization.json)

    //koin
    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

