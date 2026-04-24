# MyFirstClaudeApplication

An Android counter app built with Jetpack Compose and MVVM architecture.

## Tech Stack

| Tool | Version |
|---|---|
| Kotlin | 2.0.21 |
| Android Gradle Plugin | 8.9.1 |
| Jetpack Compose BOM | 2024.09.00 |
| Material 3 | via Compose BOM |
| Min SDK | 24 (Android 7.0) |
| Target SDK | 35 (Android 15) |
| JVM Target | Java 11 |

## Prerequisites

Before cloning, make sure you have the following installed:

- **Android Studio** Hedgehog (2023.1.1) or newer
- **JDK 17** — required by Android Gradle Plugin 8.9.1
- **Android SDK** with API level 24–35

To verify your Java version:
```bash
java -version   # must be 17 or higher
```

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/Ankita0702/MyFirstClaudeApplication.git
cd MyFirstClaudeApplication
```

### 2. Open in Android Studio

- Open Android Studio
- Select **File → Open** and choose the project directory
- Wait for Gradle sync to complete

### 3. Run the app

- Connect a physical device (USB debugging enabled) or start an emulator
- Click **Run ▶** or press `Shift + F10`

## Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Run unit tests
./gradlew test

# Run instrumented tests (requires connected device/emulator)
./gradlew connectedAndroidTest

# Run lint
./gradlew lint

# Clean build outputs
./gradlew clean
```

## Project Structure

```
app/src/main/java/com/example/myfirstclaudeapplication/
├── MainActivity.kt               # Single activity entry point
├── ui/
│   ├── screens/
│   │   └── CounterScreen.kt      # Counter UI composable
│   └── theme/
│       ├── Color.kt
│       ├── Theme.kt
│       └── Type.kt
└── viewmodel/
    └── CounterViewModel.kt       # State management with StateFlow
```

## Architecture

The app follows **MVVM** with a unidirectional data flow:

```
CounterScreen  →  CounterViewModel  →  StateFlow<Int>
     ↑                                       |
     └───────────── recompose ───────────────┘
```

- `ViewModel` holds state via `StateFlow` — no business logic in Composables
- UI observes state with `collectAsStateWithLifecycle()`

## Git Workflow

All changes must go through a pull request — direct commits to `main` are blocked.

```bash
# 1. Create a feature branch
git checkout -b feat/your-feature-name

# 2. Make changes and commit
git add <files>
git commit -m "feat: describe your change"

# 3. Push and open a PR
git push -u origin feat/your-feature-name
```

### Commit prefix conventions

| Prefix | Use for |
|---|---|
| `feat:` | New feature |
| `fix:` | Bug fix |
| `test:` | Adding or updating tests |
| `refactor:` | Code cleanup |
| `docs:` | Documentation changes |

## CI/CD

GitHub Actions runs on every pull request and push to `main`:

- **build-and-test** — compiles the debug APK and runs unit tests
- **lint** — runs Android lint; uploads HTML report as artifact on failure
