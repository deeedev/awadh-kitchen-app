#!/bin/bash

# The Awadh Kitchen - Java Environment Setup Script
# This script sets up the Java environment for the Android project

echo "🍽️ Setting up Java environment for The Awadh Kitchen..."

# Set Java environment variables
export JAVA_HOME="/opt/homebrew/opt/openjdk@17"
export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"

# Verify Java installation
echo "✅ Java version:"
java -version

echo ""
echo "🚀 Java environment is ready!"
echo "You can now run:"
echo "  ./gradlew test          # Run unit tests"
echo "  ./gradlew assembleDebug # Build debug APK"
echo "  ./gradlew clean         # Clean build"
echo ""
echo "💡 To make this permanent, add these lines to your ~/.zshrc:"
echo "   export JAVA_HOME=\"/opt/homebrew/opt/openjdk@17\""
echo "   export PATH=\"/opt/homebrew/opt/openjdk@17/bin:\$PATH\""
