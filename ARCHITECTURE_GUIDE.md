# ValorantAgents - Comprehensive Architecture & Build Guide

This document provides a complete overview of the ValorantAgents project architecture, build configuration, and development workflow.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Project Structure](#project-structure)
3. [Gradle Configuration](#gradle-configuration)
4. [Kotlin Multiplatform Setup](#kotlin-multiplatform-setup)
5. [Application Architecture](#application-architecture)
6. [iOS Build Process](#ios-build-process)
7. [Development Workflow](#development-workflow)
8. [Troubleshooting](#troubleshooting)

## Project Overview

ValorantAgents is a **Kotlin Multiplatform (KMP)** application that showcases Valorant game agents across multiple platforms:

- **Android** - Native Android app
- **iOS** - Native iOS app via Xcode
- **Desktop** - JVM-based desktop application

### Key Technologies

- **Kotlin Multiplatform Mobile** - Code sharing across platforms
- **Compose Multiplatform** - UI framework for all platforms
- **Ktor** - HTTP client for API calls
- **Room** - Local database with multiplatform support
- **Koin** - Dependency injection
- **Kotlin Serialization** - JSON parsing

## Project Structure

```
ValorantAgents/
├── composeApp/                    # Multiplatform module
│   ├── src/
│   │   ├── commonMain/           # Shared code (UI, business logic)
│   │   ├── androidMain/          # Android-specific implementations
│   │   ├── iosMain/              # iOS-specific implementations
│   │   └── desktopMain/          # Desktop-specific implementations
│   ├── build.gradle.kts          # Module build configuration
│   └── build/                    # Build outputs and frameworks
├── iosApp/                       # iOS app wrapper
│   ├── iosApp/                   # Swift UI code
│   ├── iosApp.xcodeproj/         # Xcode project
│   ├── Configuration/            # Xcode configuration
│   └── build_setup.sh           # Deprecated setup script
├── gradle/
│   ├── libs.versions.toml        # Version catalog
│   └── wrapper/                  # Gradle wrapper
├── build.gradle.kts              # Root build configuration
├── settings.gradle.kts           # Project settings
└── gradle.properties            # Gradle properties
```

## Gradle Configuration

### Root Build Configuration

The root `build.gradle.kts` defines shared plugin versions and applies them to submodules:

```kotlin
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    // ... other plugins
}
```

### Version Catalog (`libs.versions.toml`)

Centralizes dependency versions and configurations:

```toml
[versions]
kotlin = "2.0.21"
compose = "1.5.4"
ktor = "3.0.0"
koin = "4.0.1"

[libraries]
# Kotlin Multiplatform
androidx-lifecycle-viewmodel-cmp = { group = "org.jetbrains.androidx.lifecycle", name = "lifecycle-viewmodel", version = "2.8.3" }
jetbrains-compose-navigation = { group = "org.jetbrains.androidx.navigation", name = "navigation-compose", version = "2.8.0-alpha10"}

[plugins]
kotlinMultiplatform = { id = "org.jetbrains.kotlin.multiplatform", version.ref = "kotlin" }
jetbrainsCompose = { id = "org.jetbrains.compose", version.ref = "composePlugin" }
```

### Project Settings (`settings.gradle.kts`)

Configures repositories and includes modules:

```kotlin
rootProject.name = "ValorantAgents"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

include(":composeApp")
```

## Kotlin Multiplatform Setup

### Target Configuration

The `composeApp/build.gradle.kts` defines all supported platforms:

```kotlin
kotlin {
    // Android target
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    
    // iOS targets
    listOf(
        iosX64(),              // iOS Simulator (Intel)
        iosArm64(),            // iOS Device
        iosSimulatorArm64()    // iOS Simulator (Apple Silicon)
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    // Desktop target
    jvm("desktop")
}
```

### Source Set Dependencies

Dependencies are organized by source set hierarchy:

```kotlin
sourceSets {
    commonMain.dependencies {
        // Shared dependencies
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material3)
        implementation(libs.bundles.ktor)
        implementation(libs.bundles.coil)
        implementation(libs.koin.compose.cmp)
    }
    
    androidMain.dependencies {
        // Android-specific
        implementation(libs.androidx.activity.compose)
        implementation(libs.koin.android)
        implementation(libs.ktor.client.android)
    }
    
    nativeMain.dependencies {
        // iOS/Native shared
        implementation(libs.ktor.client.darwin)
    }
    
    desktopMain.dependencies {
        // Desktop-specific
        implementation(compose.desktop.currentOs)
        implementation(libs.kotlinx.coroutines.swing)
    }
}
```

## Application Architecture

The project follows **Clean Architecture** principles with clear separation of concerns:

### Layer Structure

1. **UI Layer** - Compose Multiplatform screens and ViewModels
2. **Domain Layer** - Business logic, use cases, and domain models
3. **Data Layer** - Repository pattern with local and remote data sources

### Dependency Injection with Koin

```kotlin
// CommonModule.kt - Shared dependencies
val commonModule = module {
    // Repositories
    single<AgentRepository> { AgentRepositoryImpl(get(), get()) }
    
    // Use cases
    single<GetAgents> { GetAgentsImpl(get()) }
    single<GetAgentDetails> { GetAgentDetailsImpl(get()) }
    
    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { AgentDetailViewModel(get()) }
}

// Platform-specific modules
expect val platformModule: Module
```

### Data Flow

```
UI (Compose) → ViewModel → UseCase → Repository → DataSource (Local/Remote)
```

## iOS Build Process

### Xcode Integration

The iOS app uses a **Shell Script Build Phase** that automatically builds the Kotlin framework:

1. **Xcode Build Trigger** - When you press Cmd+R in Xcode
2. **Shell Script Execution** - Runs the "Compile Kotlin Framework" build phase
3. **Gradle Task** - Executes `./gradlew :composeApp:embedAndSignAppleFrameworkForXcode`

### Framework Build Flow

```bash
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
```

This task automatically:
- Detects the target architecture (device vs simulator)
- Builds the appropriate Kotlin framework
- Places it in the correct Xcode framework search path
- Signs the framework for the target platform

### Framework Locations

- **Gradle Output**: `composeApp/build/bin/ios[Architecture]/debugFramework/ComposeApp.framework`
- **Xcode Expected**: `composeApp/build/xcode-frameworks/Debug/[sdk_name]/ComposeApp.framework`

### Xcode Build Settings

The iOS project is pre-configured with:

```
Framework Search Paths:
- $(inherited)
- $(SRCROOT)/../composeApp/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)

Other Linker Flags:
- $(inherited)
- -framework ComposeApp
```

### Environment Variables

Xcode provides these environment variables to the Gradle task:
- `CONFIGURATION` - Debug/Release
- `SDK_NAME` - iphoneos/iphonesimulator
- `ARCHS` - arm64/x86_64

## Development Workflow

### Making Changes

1. **Edit Kotlin Code** in `composeApp/src/commonMain/`
2. **Build & Run**:
   - **Android**: Run from Android Studio or `./gradlew :composeApp:installDebug`
   - **iOS**: Press Cmd+R in Xcode
   - **Desktop**: Run from IDE or `./gradlew :composeApp:run`

### iOS Development Cycle

```bash
# Open Xcode project
open iosApp/iosApp.xcodeproj

# Make Kotlin changes, then just build in Xcode (Cmd+R)
# Framework rebuilds automatically!
```

### Manual Framework Building (Debug Only)

For debugging or CI/CD purposes:

```bash
# iOS Simulator (Apple Silicon)
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64

# iOS Device
./gradlew :composeApp:linkDebugFrameworkIosArm64

# iOS Simulator (Intel)
./gradlew :composeApp:linkDebugFrameworkIosX64
```

## Platform-Specific Code

### Database Configuration

Each platform provides its own database builder:

```kotlin
// commonMain
expect fun getDatabaseBuilder(): RoomDatabase.Builder<ValoAgentDatabase>

// androidMain
actual fun getDatabaseBuilder(): RoomDatabase.Builder<ValoAgentDatabase> {
    val context = Android.applicationContext
    return Room.databaseBuilder<ValoAgentDatabase>(
        context = context,
        name = "valo_agent_database"
    )
}

// iosMain
actual fun getDatabaseBuilder(): RoomDatabase.Builder<ValoAgentDatabase> {
    return Room.databaseBuilder<ValoAgentDatabase>(
        name = "valo_agent_database",
        factory = { ValoAgentDatabase::class.instantiateImpl() }
    )
}
```

### Dependency Injection Modules

```kotlin
// Android platform module
actual val platformModule = module {
    single { getDatabaseBuilder() }
}

// iOS platform module  
actual val platformModule = module {
    single { getDatabaseBuilder() }
}
```

## Build Optimization

### Gradle Configuration

Key optimizations in `gradle.properties`:

```properties
# JVM memory settings
org.gradle.jvmargs=-Xmx2048m -Dfile.encoding=UTF-8

# Enable AndroidX
android.useAndroidX=true

# R class optimization
android.nonTransitiveRClass=true

# Multiplatform compatibility
kotlin.mpp.androidGradlePluginCompatibility.nowarn=true
```

### Compile-Time Optimizations

- **KSP (Kotlin Symbol Processing)** for Room database code generation
- **Compose Compiler** optimizations enabled
- **Type-safe project accessors** for better build performance

## Troubleshooting

### Common iOS Issues

1. **Framework Not Found**
   ```bash
   # Clean and rebuild
   ./gradlew clean
   # Build in Xcode (Cmd+R)
   ```

2. **Architecture Mismatch**
   - Ensure you're building for the correct simulator architecture
   - Apple Silicon Mac: use `iosSimulatorArm64`
   - Intel Mac: use `iosX64`

3. **Gradle Task Fails**
   - The `embedAndSignAppleFrameworkForXcode` task must run from Xcode
   - Don't run this task manually from terminal

### Build Performance

- Use `./gradlew --daemon` for faster subsequent builds
- Enable Gradle build cache in `gradle.properties`
- Consider using `--parallel` flag for multi-module builds

### IDE Configuration

- **Android Studio**: Use the latest version with KMP support
- **Xcode**: Version 15.0+ recommended for best compatibility
- **IntelliJ IDEA**: Ultimate edition recommended for full KMP support

## Summary

This ValorantAgents project demonstrates modern Kotlin Multiplatform development with:

- **Automated iOS integration** via Xcode build phases
- **Clean architecture** with proper separation of concerns
- **Modern build tooling** with version catalogs and KSP
- **Cross-platform UI** with Compose Multiplatform
- **Efficient dependency management** with Koin DI

The build system is designed to minimize manual steps while maintaining flexibility for each target platform. The iOS integration particularly showcases how KMP can seamlessly integrate with native toolchains.
