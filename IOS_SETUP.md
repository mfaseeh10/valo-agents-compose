# iOS App Setup Instructions

## Prerequisites
- macOS with Xcode 15.0 or later
- iOS Simulator or physical iOS device

## Setup Steps

### 1. Open in Xcode
```bash
open iosApp/iosApp.xcodeproj
```

### 2. Build and Run
1. Select your target device (Simulator or physical device)
2. Press Cmd+R to build and run

**That's it!** 🎉 

The project uses automatic framework integration via Xcode's build process. When you build in Xcode, it automatically:
- Builds the Kotlin Multiplatform framework
- Links it with the correct architecture
- Places it in the right location

## How It Works

Your Xcode project includes a "Compile Kotlin Framework" build phase that runs:
```bash
./gradlew :composeApp:embedAndSignAppleFrameworkForXcode
```

This Gradle task automatically handles:
- Building the framework for the target architecture (simulator vs device)
- Signing the framework appropriately
- Placing it where Xcode expects it

## Build Settings (Already Configured)

The project is pre-configured with:

**Framework Search Paths:**
- `$(inherited)`
- `$(SRCROOT)/../composeApp/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)`

**Other Linker Flags:**
- `$(inherited)`
- `-framework ComposeApp`

## Troubleshooting

### Common Issues

#### 1. Framework Not Found
**Error:** `framework not found ComposeApp`

**Solutions:**
1. **Clean and rebuild** in Xcode: Product → Clean Build Folder
2. **Clean Gradle cache**: `./gradlew clean`
3. **Restart Xcode** and try building again

#### 2. Build Errors in Xcode
**Error:** Various compilation errors

**Solutions:**
- Clean Xcode build folder: Product → Clean Build Folder
- Reset Xcode cache: Shift+Cmd+K
- Restart Xcode
- Run `./gradlew clean` from terminal

#### 3. App Crashes on Launch
**Error:** App crashes immediately after launch

**Solutions:**
- Check that Koin initialization is working
- Verify all dependencies are properly linked
- Check console logs for specific error messages

#### 4. Gradle Task Fails
**Error:** Build fails with iOS architecture errors

**Solutions:**
- Ensure you're building from within Xcode (not manually running Gradle tasks)
- If manually testing Gradle tasks, you must set appropriate environment variables
- The `embedAndSignAppleFrameworkForXcode` task is designed to run from Xcode only

### 5. Simulator Issues
**Error:** App doesn't run on simulator

**Solutions:**
- Try different simulator devices
- Ensure your Mac architecture matches the simulator (Apple Silicon vs Intel)
- Clean build folder and try again

## Development Workflow

### Making Changes to Kotlin Code
1. Edit Kotlin code in `composeApp/src/commonMain`
2. Build and run in Xcode (Cmd+R)
3. Xcode automatically rebuilds the framework with your changes

### Fast Development Cycle
- **No manual steps needed!** 
- Just edit Kotlin code and press Cmd+R in Xcode
- The framework rebuilds automatically as part of Xcode's build process

## Advanced: Manual Framework Building (Usually Not Needed)

If you need to manually build frameworks for debugging:

```bash
# For iOS Simulator (Apple Silicon)
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64

# For iOS Simulator (Intel Mac)
./gradlew :composeApp:linkDebugFrameworkIosX64

# For iOS Device
./gradlew :composeApp:linkDebugFrameworkIosArm64
```

**Note:** These manual commands build frameworks to `composeApp/build/bin/`, but Xcode expects them in `composeApp/build/xcode-frameworks/`. The automatic `embedAndSignAppleFrameworkForXcode` task handles this for you.
