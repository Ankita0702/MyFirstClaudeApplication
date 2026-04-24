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

## AI Development Setup

This project uses Claude Code CLI and GitHub CLI to streamline AI-assisted development.

### 1. Claude Code CLI

Install and authenticate Claude Code:

```bash
# Install via npm
npm install -g @anthropic-ai/claude-code

# Launch Claude Code in the project directory
cd MyFirstClaudeApplication
claude
```

On first launch Claude Code will prompt you to log in with your Anthropic account.

> Requires Node.js 18+. Check with `node --version`.

### 2. GitHub CLI (gh)

Install and authenticate the GitHub CLI:

```bash
# Install via Homebrew (macOS)
brew install gh

# Authenticate with your GitHub account
gh auth login
```

Follow the prompts to authenticate via browser. Select **HTTPS** and **GitHub.com** when asked.

Verify authentication:
```bash
gh auth status
```

### 3. Git Hooks Setup

A pre-commit hook blocks accidental commits directly to `main`. Set it up after cloning:

```bash
# Copy the hook into your local .git/hooks directory
cp scripts/pre-commit .git/hooks/pre-commit
chmod +x .git/hooks/pre-commit
```

The hook will print an error and abort the commit if you are on the `main` branch:
```
Error: Cannot commit to main! Create a feature branch first.
```

### 4. Tips for Working with Claude

- **Be specific** — describe the exact file, function, or behaviour you want changed
- **One task at a time** — focused prompts produce cleaner, more reviewable diffs
- **Review before committing** — always read Claude's changes before staging them
- **Use branch-first workflow** — ask Claude to create a branch before making changes; it follows the rules in `CLAUDE.md`
- **Commit message conventions** — Claude follows the prefix conventions (`feat:`, `fix:`, `test:`, `refactor:`, `docs:`) defined in `CLAUDE.md`
- **Iterate** — if a change isn't right, describe what's wrong and Claude will fix it

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
