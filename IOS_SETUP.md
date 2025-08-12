# iOS App Setup Instructions

## Prerequisites
- macOS with Xcode 15.0 or later
- iOS Simulator or physical iOS device
- CocoaPods (if using)

## Setup Steps

### 1. Build the Kotlin Framework
First, ensure the Compose Multiplatform framework is built:

```bash
# From the root directory
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
./gradlew :composeApp:linkDebugFrameworkIosArm64
```

### 2. Set up Framework Paths (Automatic)
Run the setup script to automatically configure frameworks:

```bash
cd iosApp
./build_setup.sh
```

### 3. Open in Xcode
```bash
open iosApp/iosApp.xcodeproj
```

### 4. Configure Build Settings (if needed)
In Xcode, verify these settings:

**Framework Search Paths:**
- `$(inherited)`
- `$(SRCROOT)/../composeApp/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)`

**Other Linker Flags:**
- `$(inherited)`
- `-framework ComposeApp`

### 5. Build and Run
1. Select your target device (Simulator or physical device)
2. Press Cmd+R to build and run

## Troubleshooting

### Common Issues

#### 1. Framework Not Found
**Error:** `framework not found ComposeApp`

**Solution:**
```bash
# Clean and rebuild frameworks
./gradlew clean
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
cd iosApp && ./build_setup.sh
```

#### 2. Build Errors in Xcode
**Error:** Various compilation errors

**Solutions:**
- Clean Xcode build folder: Product → Clean Build Folder
- Reset Xcode cache: Shift+Cmd+K
- Restart Xcode

#### 3. App Crashes on Launch
**Error:** App crashes immediately after launch

**Solutions:**
- Check that Koin initialization is working
- Verify all dependencies are properly linked
- Check console logs for specific error messages

#### 4. Storyboard Not Found Error
**Error:** `Could not find a storyboard named 'Main'`

**Solution:**
This error occurs when Info.plist is configured for storyboard-based apps instead of SwiftUI. The Info.plist has been updated to use SwiftUI properly. If you encounter this:
- Ensure Info.plist doesn't reference storyboard files
- Verify `UIApplicationSceneManifest` is configured for SwiftUI
- Check that `@main` annotation is on your SwiftUI App struct

### 5. Simulator Issues
**Error:** App doesn't run on simulator

**Solutions:**
- Ensure you built for the correct architecture:
  - Apple Silicon Mac: `iosSimulatorArm64`
  - Intel Mac: `iosX64`
- Try different simulator devices

## Manual Framework Setup

If the automatic script doesn't work, you can manually set up frameworks:

```bash
# Create directories
mkdir -p composeApp/build/xcode-frameworks/Debug/iphonesimulator
mkdir -p composeApp/build/xcode-frameworks/Debug/iphoneos

# Build frameworks
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
./gradlew :composeApp:linkDebugFrameworkIosArm64

# Copy frameworks
cp -R composeApp/build/bin/iosSimulatorArm64/debugFramework/ComposeApp.framework \
      composeApp/build/xcode-frameworks/Debug/iphonesimulator/

cp -R composeApp/build/bin/iosArm64/debugFramework/ComposeApp.framework \
      composeApp/build/xcode-frameworks/Debug/iphoneos/
```

## Architecture Support

### Supported Targets
- **iOS Simulator (Apple Silicon):** `iosSimulatorArm64`
- **iOS Simulator (Intel):** `iosX64` 
- **iOS Device:** `iosArm64`

### Building for Different Targets
```bash
# For Apple Silicon simulator
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64

# For Intel simulator  
./gradlew :composeApp:linkDebugFrameworkIosX64

# For real devices
./gradlew :composeApp:linkDebugFrameworkIosArm64
```

## Development Workflow

### 1. Making Changes
1. Edit Kotlin code in `composeApp/src/commonMain`
2. Rebuild framework: `./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64`
3. Run setup script: `cd iosApp && ./build_setup.sh`
4. Build and run in Xcode

### 2. Quick Development Cycle
For faster iteration during development:

```bash
# Watch for changes and auto-rebuild (if available)
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64 --continuous

# Or use a simple script to rebuild on changes
```

## Project Structure

```
ValorantAgents/
├── composeApp/               # Shared Compose Multiplatform code
│   ├── src/commonMain/      # Shared UI and business logic
│   ├── src/iosMain/         # iOS-specific implementations
│   └── build/xcode-frameworks/ # Built frameworks for Xcode
├── iosApp/                  # iOS app wrapper
│   ├── iosApp/             # Swift UI code
│   ├── iosApp.xcodeproj/   # Xcode project
│   └── build_setup.sh      # Setup script
```

## Notes

- The iOS app acts as a thin wrapper around the Compose Multiplatform UI
- All business logic, UI, and navigation are shared with Android
- iOS-specific code should only be for platform integrations
- Framework needs to be rebuilt after any Kotlin code changes
