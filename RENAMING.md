# Renaming Guide

This guide walks you through renaming this template project for your own app without breaking
IntelliJ IDEA/Android Studio. The template uses several custom names that need updating across
multiple modules.

## 📋 What Needs to Change

1. Root project name
2. Application ID and namespaces (3 modules: shared, designsystem, androidApp)
3. Package names in all code files
4. Custom Application class name (`YourNameApplication`)
5. Theme and Typography names (`YourAppTheme`, `YourAppTypography`)
6. iOS bundle identifier and display name
7. Directory structure to match new packages

## 🔄 Step-by-Step Renaming Process

### Step 1: Close Android Studio/IntelliJ

**Important**: Close the IDE before making these changes to avoid cache issues.

### Step 2: Update Root Project Name

**File**: `settings.gradle.kts`

```kotlin
// Change from:
rootProject.name = "YourProjectName"

// To:
rootProject.name = "MyAwesomeApp"
```

### Step 3: Update Application IDs and Namespaces

You need to update three modules: `shared`, `designsystem`, and `androidApp`.

#### In `androidApp/build.gradle.kts`:

```kotlin
android {
    // Change namespace
    namespace = "com.mycompany.myapp.android"  // Your new package

    defaultConfig {
        // Change application ID
        applicationId = "com.mycompany.myapp"  // Your new app ID
        // ... rest stays the same
    }
}
```

#### In `shared/build.gradle.kts`:

```kotlin
kotlin {
    android {
        // Change namespace
        namespace = "com.mycompany.myapp"  // Your new package
        // ... rest stays the same
    }
}
```

#### In `designsystem/build.gradle.kts`:

```kotlin
kotlin {
    android {
        // Change namespace
        namespace = "com.mycompany.myapp.designsystem"  // Your new package
        // ... rest stays the same
    }
}
```

### Step 4: Update Compose Resources Package

**File**: `shared/build.gradle.kts`

```kotlin
compose.resources {
    publicResClass = true
    // Change package
    packageOfResClass = "com.mycompany.myapp.shared.resources"
}
```

### Step 5: Rename Custom Classes

You need to rename these custom class/object names in your code:

#### 1. Application Class

**File**: `androidApp/src/main/kotlin/.../YourNameApplication.kt`

```kotlin
// Change class name from:
class YourNameApplication : Application()

// To:
class MyAwesomeApplication : Application()
```

Also update in **`AndroidManifest.xml`**:

```xml

<application android:name=".MyAwesomeApplication"...>
```

#### 2. Theme Name

**File**: `designsystem/src/commonMain/kotlin/.../theme/Theme.kt`

```kotlin
// Change function name from:
@Composable
fun YourAppTheme(...)

// To:
@Composable
fun MyAwesomeTheme(...)
```

#### 3. Typography Name

**File**: `designsystem/src/commonMain/kotlin/.../theme/Type.kt`

```kotlin
// Change val name from:
val YourAppTypography = Typography(...)

// To:
val MyAwesomeTypography = Typography(...)
```

And update its usage in `Theme.kt`:

```kotlin
MaterialTheme(
    colorScheme = colorScheme,
    typography = MyAwesomeTypography,  // Updated reference
    content = content,
)
```

### Step 6: Update Package Names Using Android Studio

Now reopen Android Studio and use its powerful refactoring tools:

#### Refactor Package Names

1. **Open Android Studio** and let it index the project
2. Navigate to any of these package folders:
    - `shared/src/commonMain/kotlin/com/yourcompany/yourapp/`
    - `designsystem/src/commonMain/kotlin/com/yourcompany/yourapp/designsystem/`
    - `androidApp/src/main/kotlin/com/yourcompany/yourapp/android/`

3. **Right-click on the package folder** (e.g., `yourapp`)
4. Select **Refactor** → **Rename**
5. Choose **"Rename directory"**
6. Enter your new package name: `myapp`
7. Click **Refactor**
8. Android Studio will update imports across all files!
9. **Repeat for parent directories** if needed (`yourcompany` → `mycompany`)

**Pro tip**: Start from the deepest package level and work your way up.

### Step 7: Update All Import Statements (Manual Verification)

After refactoring, verify and update any remaining imports using Find & Replace:

**Use Android Studio's Find and Replace** (Cmd/Ctrl + Shift + R):

**Replace 1 - Main package:**

- Find: `com.yourcompany.yourapp`
- Replace: `com.mycompany.myapp`
- Scope: Whole project
- Click "Replace All"

**Replace 2 - Resource package:**

- Find: `com.yourcompany.yourapp.shared.resources`
- Replace: `com.mycompany.myapp.shared.resources`
- Scope: Whole project
- Click "Replace All"

**Replace 3 - Theme usages:**

- Find: `YourAppTheme`
- Replace: `MyAwesomeTheme`
- Scope: Whole project
- Click "Replace All"

**Replace 4 - Typography usages:**

- Find: `YourAppTypography`
- Replace: `MyAwesomeTypography`
- Scope: Whole project
- Click "Replace All"

### Step 8: Update Directory Structure

Now rename the actual directories to match your new package names:

```bash
# Navigate to each source root and rename directories
cd shared/src/commonMain/kotlin
mv com/yourcompany/yourapp com/mycompany/myapp

cd designsystem/src/commonMain/kotlin
mv com/yourcompany/yourapp com/mycompany/myapp

cd androidApp/src/main/kotlin
mv com/yourcompany/yourapp com/mycompany/myapp

# Repeat for androidMain, iosMain, test directories as needed
```

### Step 9: Update iOS Bundle Identifier

1. Open `iosApp/iosApp.xcodeproj` in Xcode
2. Select the **iosApp** project in the navigator
3. Select the **iosApp** target
4. Go to the **General** tab
5. Under **Identity**, change **Bundle Identifier**:
   ```
   From: com.yourcompany.YourProjectName
   To:   com.mycompany.MyAwesomeApp
   ```

### Step 10: Update iOS Display Name (Optional)

In the same Xcode screen:

1. Under **Identity**, change **Display Name**:
   ```
   From: iosApp
   To:   MyAwesomeApp
   ```

### Step 11: Update App Display Name

**File**: `androidApp/src/main/res/values/strings.xml`

```xml

<resources>
    <string name="app_name">MyAwesomeApp</string>
</resources>
```

### Step 12: Update Test Package Names

Update the test assertion in instrumentation tests:

**File**: `androidApp/src/androidTest/kotlin/.../ExampleInstrumentedTest.kt`

```kotlin
@Test
fun useAppContext() {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals("com.mycompany.myapp", appContext.packageName)  // Updated
}
```

### Step 13: Clean Build

```bash
# Clean all build artifacts
./gradlew clean

# Delete IDE caches
rm -rf .gradle
rm -rf .idea
rm -rf */build
rm -rf build

# Delete KSP generated code
rm -rf */build/generated
```

### Step 14: Reopen in Android Studio

1. **File** → **Open** → Select your project folder
2. Wait for Gradle sync and indexing to complete
3. **Build** → **Rebuild Project**
4. Run **Spotless** to format everything:
   ```bash
   ./gradlew spotlessApply
   ```
5. Run the Android app to verify

### Step 15: Verify iOS (if applicable)

1. In terminal: `./gradlew :shared:assembleSharedDebugXCFramework`
2. Open `iosApp/iosApp.xcodeproj` in Xcode
3. Clean build folder: **Product** → **Clean Build Folder**
4. Run the app

## ✅ Verification Checklist

After renaming, verify:

- [ ] Android app builds and runs without errors
- [ ] iOS app builds and runs without errors
- [ ] No import errors in any files (check all modules)
- [ ] Compose resources load correctly
- [ ] Theme (`MyAwesomeTheme`) is applied correctly
- [ ] Custom Application class (`MyAwesomeApplication`) is instantiated
- [ ] Dependency injection works (check component creation)
- [ ] App displays correct name on home screen
- [ ] No deprecation warnings during build
- [ ] Spotless formatting passes: `./gradlew spotlessCheck`
- [ ] All tests run successfully
- [ ] KSP code generation works (rebuild if needed)

## 🎯 Quick Reference: What to Rename

| What                     | Where                           | Example                                |
|--------------------------|---------------------------------|----------------------------------------|
| Root project name        | `settings.gradle.kts`           | `MyAwesomeApp`                         |
| Application ID           | `androidApp/build.gradle.kts`   | `com.mycompany.myapp`                  |
| Namespace (androidApp)   | `androidApp/build.gradle.kts`   | `com.mycompany.myapp.android`          |
| Namespace (shared)       | `shared/build.gradle.kts`       | `com.mycompany.myapp`                  |
| Namespace (designsystem) | `designsystem/build.gradle.kts` | `com.mycompany.myapp.designsystem`     |
| Resource package         | `shared/build.gradle.kts`       | `com.mycompany.myapp.shared.resources` |
| Application class        | `YourNameApplication.kt`        | `MyAwesomeApplication`                 |
| Theme function           | `designsystem/.../Theme.kt`     | `MyAwesomeTheme`                       |
| Typography object        | `designsystem/.../Type.kt`      | `MyAwesomeTypography`                  |
| Package directories      | All `src/` folders              | `com/mycompany/myapp/...`              |
| iOS bundle ID            | Xcode project settings          | `com.mycompany.MyAwesomeApp`           |
| App name                 | `res/values/strings.xml`        | `MyAwesomeApp`                         |

## 🐛 Common Issues

### Issue: "Cannot resolve symbol after rename"

**Solution**:

```bash
./gradlew clean
# In Android Studio: File → Invalidate Caches → Invalidate and Restart
```

### Issue: "Duplicate class found" error

**Solution**: Old build artifacts. Run:

```bash
./gradlew clean
rm -rf .gradle */build build
```

### Issue: Resources not found after rename

**Solution**:

1. Verify `packageOfResClass` in `shared/build.gradle.kts`
2. Update all resource imports
3. Rebuild: `./gradlew :shared:build`

### Issue: Dependency injection component not found

**Solution**: KSP needs to regenerate code:

```bash
./gradlew clean
./gradlew :shared:kspCommonMainKotlinMetadata
./gradlew build
```

### Issue: Theme/Typography not found

**Solution**: Check that you:

1. Renamed the function/object in the definition file
2. Updated all usages (use Find & Replace)
3. Synced Gradle
4. Rebuilt the project

### Issue: Application class not instantiated

**Solution**:

1. Check `AndroidManifest.xml` has correct `android:name`
2. Verify class name matches the one in manifest
3. Clean and rebuild

### Issue: iOS app crashes on launch

**Solution**: Rebuild the framework:

```bash
./gradlew clean
./gradlew :shared:assembleSharedDebugXCFramework
```

Then clean and rebuild in Xcode.

### Issue: Spotless formatting fails after rename

**Solution**: Auto-fix formatting:

```bash
./gradlew spotlessApply
```

## 💡 Pro Tips

1. **Use Android Studio's refactoring tools** - They automatically update imports and references
2. **Do one module at a time** - Start with `shared`, then `designsystem`, then `androidApp`
3. **Commit between major steps** - Use git to track changes and rollback if needed
4. **Test after each module** - Catch issues early before moving to the next module
5. **Keep a backup** - Copy the original template before starting
6. **Run Spotless at the end** - Ensures consistent formatting: `./gradlew spotlessApply`
7. **Rebuild KSP** - If DI isn't working, clean and rebuild to regenerate code

## 📝 Suggested Naming Conventions

### Package Names

- Use reverse domain notation: `com.yourcompany.appname`
- Use lowercase letters only
- Avoid underscores and hyphens
- Keep it simple and memorable

**Good examples:**

- `com.acme.todoapp`
- `io.github.username.projectname`
- `dev.yourname.coolapp`

**Avoid:**

- `com.my-company.my_app` (hyphens/underscores)
- `com.Company.AppName` (uppercase)
- `app.todo` (too generic)

### Application ID

- Should match your main package name
- Must be unique on app stores
- Example: `com.yourcompany.yourapp`

