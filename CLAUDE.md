# CLAUDE.md

## Build Commands
```bash
./gradlew assembleDebug      # Build debug
./gradlew assembleRelease    # Build release
./gradlew test               # Unit tests
./gradlew connectedAndroidTest # Instrumented tests
./gradlew lint               # Lint
./gradlew clean              # Clean
```

## Tech Stack
- **Language:** Kotlin 2.0.21
- **UI:** Jetpack Compose (BOM 2024.09.00), Material 3
- **Build:** Gradle 8.11.1, Kotlin DSL, AGP 8.9.0
- **Dependencies:** `gradle/libs.versions.toml`
- **SDK:** Min 24 / Target 35 | **JVM:** Java 11

## Architecture
Single-module Jetpack Compose, single-activity.
**Package:** `com.example.myfirstclaudeapplication`
- `MainActivity` → entry point, edge-to-edge, Compose host
- `ui/theme/` → Color, Theme, Type (Material 3, dark/light)

## Project Structure (as it grows)
- `viewmodel/` → ViewModels + StateFlow
- `ui/screens/` → Compose screens
- `data/` → Repositories
- `model/` → Data classes
- Hilt for DI | Navigation Compose for routing

## Development Rules
- Kotlin only, MVVM, ViewModel + StateFlow
- No business logic in Composables
- Flow over LiveData, Material 3 guidelines
- `remember`/`mutableStateOf` for local UI state
- `@Preview` on all Composables
- After ANY gradle change → run assembleDebug, fix until green

## Testing Rules
- Unit tests for every ViewModel
- UI tests for every screen
- All tests must pass before PR

## Git Rules - ALWAYS FOLLOW

### Commit Format
feat/fix/test/refactor/docs/chore: description

### Branch Naming
feature/ fix/ refactor/ test/ docs/ chore/ + name

### Strict Rules
- NEVER commit or push directly to main
- ALWAYS create correct branch FIRST
- ALWAYS create PR from branch to main
- NEVER merge to main without PR

## Branch Lifecycle - MANDATORY
### Before Every Task:
1. git fetch origin
2. git branch -a (check current branch)
3. If branch already merged → git checkout main
   → git pull origin main → git branch -d old-branch
4. Create fresh new branch

### During Task:
- Same branch until PR merged
- Same branch for CI fixes and PR review fixes

### After PR Merged:
- Delete local branch → git checkout main
- git pull origin main → fresh branch for next task

## Workflow - NEVER SKIP
1. Create correct branch (refer Branch Naming)
2. Make changes on that branch only
3. Write tests for all new code
4. Review: no bugs, no logic in Composables,
   proper coroutines, error handling, tests passing
5. Commit → Push → Create PR
6. Fix PR review issues on same branch
7. Merge only after all checks pass