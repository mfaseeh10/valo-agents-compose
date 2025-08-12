#!/bin/bash

# Script to set up iOS frameworks for Xcode

echo "🚀 Setting up iOS frameworks for Xcode..."

# Create the xcode-frameworks directory structure
echo "📁 Creating framework directories..."
mkdir -p ../composeApp/build/xcode-frameworks/Debug/iphonesimulator
mkdir -p ../composeApp/build/xcode-frameworks/Debug/iphoneos
mkdir -p ../composeApp/build/xcode-frameworks/Release/iphonesimulator
mkdir -p ../composeApp/build/xcode-frameworks/Release/iphoneos

# Build the frameworks
echo "🔨 Building iOS frameworks..."
cd ..

echo "  → Building for iOS Simulator (Apple Silicon)..."
./gradlew :composeApp:linkDebugFrameworkIosSimulatorArm64
if [ $? -eq 0 ]; then
    echo "  ✅ iOS Simulator framework built successfully"
else
    echo "  ❌ Failed to build iOS Simulator framework"
    exit 1
fi

echo "  → Building for iOS Device..."
./gradlew :composeApp:linkDebugFrameworkIosArm64
if [ $? -eq 0 ]; then
    echo "  ✅ iOS Device framework built successfully"
else
    echo "  ❌ Failed to build iOS Device framework"
    exit 1
fi

# Copy frameworks to expected locations
echo "📋 Copying frameworks to Xcode locations..."
if [ -d "composeApp/build/bin/iosSimulatorArm64/debugFramework/ComposeApp.framework" ]; then
    cp -R composeApp/build/bin/iosSimulatorArm64/debugFramework/ComposeApp.framework composeApp/build/xcode-frameworks/Debug/iphonesimulator/
    echo "  ✅ Simulator framework copied"
else
    echo "  ❌ Simulator framework not found"
fi

if [ -d "composeApp/build/bin/iosArm64/debugFramework/ComposeApp.framework" ]; then
    cp -R composeApp/build/bin/iosArm64/debugFramework/ComposeApp.framework composeApp/build/xcode-frameworks/Debug/iphoneos/
    echo "  ✅ Device framework copied"
else
    echo "  ❌ Device framework not found"
fi

echo ""
echo "✅ Setup complete!"
echo ""
echo "Next steps:"
echo "1. Open iosApp.xcodeproj in Xcode"
echo "2. Select your target device/simulator"
echo "3. Press Cmd+R to build and run"
echo ""
echo "Note: The ComposeApp module should now be available in Xcode!"
