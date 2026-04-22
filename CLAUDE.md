# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build (debug)
./gradlew assembleDebug

# Build (release)
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Lint
./gradlew lint

# Clean
./gradlew clean
```

## Tech Stack

- **Language:** Kotlin 2.0.21 (official code style)
- **UI:** Jetpack Compose (BOM 2024.09.00), Material 3 with dynamic color support (Android 12+)
- **Build:** Gradle 8.11.1 with Kotlin DSL (`.kts`), AGP 8.9.0
- **Dependencies:** Managed via version catalog at `gradle/libs.versions.toml`
- **Min SDK:** 24 (Android 7.0) / **Target SDK:** 35 (Android 15)
- **JVM target:** Java 11

## Architecture

This is a single-module (`:app`) Jetpack Compose application with a single-activity architecture.

**Package:** `com.example.myfirstclaudeapplication`

- `MainActivity` — entry point; sets up edge-to-edge display and hosts the Compose UI tree
- `ui/theme/` — `Color.kt`, `Theme.kt` (`MyFirstClaudeApplicationTheme`), `Type.kt`; Material 3 theming with automatic dark/light and dynamic color switching

## Development Rules

- Use Kotlin only, never Java
- Follow MVVM architecture pattern
- Use ViewModel + StateFlow for state management
- No business logic inside Composables
- Use Flow instead of LiveData
- Follow Material 3 design guidelines

## What to Add as Project Grows

- `viewmodel/` → ViewModels with StateFlow
- `ui/screens/` → Individual Compose screens
- `data/` → Repositories & data sources
- `model/` → Data classes
- Use Hilt for dependency injection when needed
- Use Navigation Compose for multiple screens

## Code Style

- Use `remember` and `mutableStateOf` for local UI state
- Use `@Preview` annotations for all Composables
- Keep Composables small and reusable

## Important Rules

- After ANY changes to gradle files, ALWAYS run ./gradlew assembleDebug to sync and verify the build succeeds
- If build fails after gradle changes, automatically fix errors and rebuild until successful
- Never leave gradle changes without verifying the build works

## Git Workflow
- After every feature, stage and commit with descriptive message
- Always check .gitignore before first commit
- Push to origin main after every commit

## Git Commit Rules
- feat: new feature
- fix: bug fix
- test: adding tests
- refactor: code cleanup
- docs: documentation changes