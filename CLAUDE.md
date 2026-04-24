# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code)
when working with code in this repository.

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
This is a single-module (`:app`) Jetpack Compose application
with a single-activity architecture.

**Package:** `com.example.myfirstclaudeapplication`

- `MainActivity` — entry point; sets up edge-to-edge display
  and hosts the Compose UI tree
- `ui/theme/` — `Color.kt`, `Theme.kt`, `Type.kt`; Material 3
  theming with automatic dark/light and dynamic color switching

## What to Add as Project Grows
- `viewmodel/` → ViewModels with StateFlow
- `ui/screens/` → Individual Compose screens
- `data/` → Repositories & data sources
- `model/` → Data classes
- Use Hilt for dependency injection when needed
- Use Navigation Compose for multiple screens

## Development Rules
- Use Kotlin only, never Java
- Follow MVVM architecture pattern
- Use ViewModel + StateFlow for state management
- No business logic inside Composables
- Use Flow instead of LiveData
- Follow Material 3 design guidelines

## Code Style
- Use `remember` and `mutableStateOf` for local UI state
- Use `@Preview` annotations for all Composables
- Keep Composables small and reusable

## Gradle Rules
- After ANY changes to gradle files, ALWAYS run
  ./gradlew assembleDebug to verify build succeeds
- If build fails, automatically fix and rebuild until successful
- Never leave gradle changes without verifying build works

## Testing Rules
- Always write unit tests for every ViewModel
- Always write UI tests for every screen
- Run all tests before creating PR
- Fix all test failures before pushing

## Git Rules - ALWAYS FOLLOW

### Commit Message Format
- feat: new feature
- fix: bug fix
- test: adding tests
- refactor: code cleanup
- docs: documentation changes
- chore: gradle/config changes

### Branch Naming
- feature/name → new features
- fix/name     → bug fixes
- refactor/name → code refactoring
- test/name    → adding tests
- docs/name    → documentation
- chore/name   → gradle/config changes

### Strict Rules
- NEVER commit or push directly to main
- ALWAYS create correct branch FIRST before any changes
- ALWAYS push to feature branch only
- ALWAYS create PR from feature branch to main
- NEVER merge to main directly


## Workflow - FOLLOW THIS EXACT ORDER - NEVER SKIP STEPS
1. Identify type of work and create correct branch
   (refer to Git Rules → Branch Naming above)
2. Make all changes on that branch only
3. Write tests for all new code
4. Commit to feature branch
5. Review code quality before pushing:
    - No bugs or memory leaks
    - No business logic in Composables
    - Proper coroutine scope usage
    - Proper error handling
    - All tests passing
6. Push branch to origin
7. Create PR from branch to main
8. Review PR thoroughly
9. Fix any PR review issues on same branch
10. Push fixes to same branch
11. Only merge to main AFTER all review issues fixed
12. NEVER merge directly to main without PR review
13. NEVER skip any of these steps

## Branch Lifecycle
- Keep using same branch until PR is merged
- Only create new branch for new tasks
- Never create new branch just to fix CI errors
- Never create new branch for PR review fixes