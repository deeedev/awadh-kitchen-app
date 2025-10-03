#!/bin/bash

# Quick Start Script for The Awadh Kitchen
# This script helps you get started with development and testing

set -e

echo "🚀 The Awadh Kitchen - Quick Start"
echo "=================================="

# Colors
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m'

print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

# Check if we're in the right directory
if [ ! -f "gradlew" ]; then
    echo "❌ Please run this script from the project root directory"
    exit 1
fi

# Make gradlew executable
chmod +x gradlew

echo ""
echo "📱 Available Commands:"
echo "1. Build and run the app"
echo "2. Run tests"
echo "3. Build release APK"
echo "4. Setup Firebase (optional)"
echo "5. Run lint checks"
echo "6. Generate reports"
echo ""

read -p "Choose an option (1-6): " choice

case $choice in
    1)
        print_info "Building and running the app..."
        ./gradlew assembleDebug
        print_success "Debug APK built successfully!"
        print_info "APK location: app/build/outputs/apk/debug/app-debug.apk"
        print_info "Install on device: adb install app/build/outputs/apk/debug/app-debug.apk"
        ;;
    2)
        print_info "Running comprehensive tests..."
        ./run-tests.sh
        ;;
    3)
        print_info "Building release APK..."
        ./gradlew assembleProductionRelease
        print_success "Release APK built successfully!"
        print_info "APK location: app/build/outputs/apk/production/release/app-production-release.apk"
        ;;
    4)
        print_info "Firebase setup instructions:"
        echo "1. Install Firebase CLI: npm install -g firebase-tools"
        echo "2. Login: firebase login"
        echo "3. Create project: firebase projects:create awadh-kitchen-dev"
        echo "4. Initialize: firebase init"
        echo "5. Download google-services.json and place in app/ directory"
        echo ""
        print_warning "Note: Firebase setup is optional for MVP testing"
        ;;
    5)
        print_info "Running lint checks..."
        ./gradlew lint
        print_info "Lint report: app/build/reports/lint-results-debug.html"
        ;;
    6)
        print_info "Generating test reports..."
        ./gradlew test
        ./gradlew jacocoTestReport
        print_success "Reports generated!"
        print_info "Test report: app/build/reports/tests/testDebugUnitTest/index.html"
        print_info "Coverage report: app/build/reports/jacoco/jacocoTestReport/html/index.html"
        ;;
    *)
        echo "❌ Invalid option. Please choose 1-6."
        exit 1
        ;;
esac

echo ""
print_success "✅ Operation completed successfully!"
echo ""
echo "📚 Next Steps:"
echo "- Read DEPLOYMENT.md for production deployment"
echo "- Check README.md for detailed documentation"
echo "- Run './run-tests.sh' for comprehensive testing"
echo ""
echo "🎉 Happy coding!"
