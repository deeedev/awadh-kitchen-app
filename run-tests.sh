#!/bin/bash

# The Awadh Kitchen - Testing Script
# This script runs comprehensive tests for the Android app

set -e

echo "🧪 Starting comprehensive testing for The Awadh Kitchen..."

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_status() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Check if we're in the right directory
if [ ! -f "gradlew" ]; then
    print_error "Please run this script from the project root directory"
    exit 1
fi

# Make gradlew executable
chmod +x gradlew

print_status "Running unit tests..."
./gradlew test
if [ $? -eq 0 ]; then
    print_success "Unit tests passed!"
else
    print_error "Unit tests failed!"
    exit 1
fi

print_status "Running lint checks..."
./gradlew lint
if [ $? -eq 0 ]; then
    print_success "Lint checks passed!"
else
    print_warning "Lint checks found issues (see report above)"
fi

print_status "Running connected tests..."
./gradlew connectedAndroidTest
if [ $? -eq 0 ]; then
    print_success "Connected tests passed!"
else
    print_error "Connected tests failed!"
    exit 1
fi

print_status "Building debug APK..."
./gradlew assembleDebug
if [ $? -eq 0 ]; then
    print_success "Debug APK built successfully!"
else
    print_error "Debug APK build failed!"
    exit 1
fi

print_status "Building release APK..."
./gradlew assembleProductionRelease
if [ $? -eq 0 ]; then
    print_success "Release APK built successfully!"
else
    print_error "Release APK build failed!"
    exit 1
fi

print_status "Running code coverage..."
./gradlew jacocoTestReport
if [ $? -eq 0 ]; then
    print_success "Code coverage report generated!"
    print_status "Coverage report available at: app/build/reports/jacoco/jacocoTestReport/html/index.html"
else
    print_warning "Code coverage report generation failed"
fi

print_status "Running dependency check..."
./gradlew dependencyCheckAnalyze
if [ $? -eq 0 ]; then
    print_success "Dependency check passed!"
else
    print_warning "Dependency check found issues"
fi

print_status "Generating test reports..."
echo "📊 Test Reports Generated:"
echo "  - Unit Tests: app/build/reports/tests/testDebugUnitTest/index.html"
echo "  - Lint Report: app/build/reports/lint-results-debug.html"
echo "  - Coverage Report: app/build/reports/jacoco/jacocoTestReport/html/index.html"
echo "  - Dependency Check: app/build/reports/dependency-check-report.html"

print_success "🎉 All tests completed successfully!"
print_status "APK files generated:"
echo "  - Debug: app/build/outputs/apk/debug/app-debug.apk"
echo "  - Release: app/build/outputs/apk/production/release/app-production-release.apk"

print_status "📱 Ready for deployment!"
