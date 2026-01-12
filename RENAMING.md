# Renaming Guide

This guide walks you through renaming this template project for your own app without breaking
IntelliJ IDEA/Android Studio.

## 📋 What Needs to Change

1. Root project name
2. Application ID and namespaces
3. Package names in code
4. iOS bundle identifier
5. Directory structure (optional but recommended)

## 🔄 Step-by-Step Renaming Process

### Step 1: Close Android Studio/IntelliJ

**Important**: Close the IDE before making these changes to avoid cache issues.

### Step 2: Update Root Project Name

**File**: `settings.gradle.kts`

```kotlin
// Change from:
rootProject.name = "CMPDemo2"

// To:
rootProject.name = "YourAppName"
```

### Step 3: Update Android Application ID and Namespaces

#### In `androidApp/build.gradle.kts`:

```kotlin
android {
    // Change namespace
    namespace = "com.yourcompany.yourapp.android"

    defaultConfig {
        // Change application ID
        applicationId = "com.yourcompany.yourapp"
        // ... rest stays the same
    }
}
```

#### In `shared/build.gradle.kts`:

```kotlin
kotlin {
    android {
        // Change namespace
        namespace = "com.yourcompany.yourapp"
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
    packageOfResClass = "com.yourcompany.yourapp.shared.resources"
}
```

### Step 5: Update Package Names in Code

#### Update Shared Module Package

1. Open Android Studio
2. Navigate to `shared/src/commonMain/kotlin/com/example/cmpdemo2/`
3. Right-click on the `cmpdemo2` package folder
4. Select **Refactor** → **Rename**
5. Choose **"Rename directory"**
6. Enter your new package name: `yourapp`
7. Click **Refactor**
8. Repeat for parent directories if needed (`example` → `yourcompany`)

**Or manually:**

```bash
# Navigate to your project
cd shared/src/commonMain/kotlin

# Rename directories
mv com/example/cmpdemo2 com/example/yourapp
mv com/example com/yourcompany
```

#### Update AndroidManifest.xml

**File**: `androidApp/src/androidMain/AndroidManifest.xml`

```xml

<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application android:label="Your App Name"
    ...>
    <activity android:name=".MainActivity"
    ...>
</activity></application></manifest>
```

#### Update MainActivity Package

**File**: `androidApp/src/androidMain/kotlin/.../MainActivity.kt`

```kotlin
// Change package
package com.yourcompany.yourapp.android

import com.yourcompany.yourapp.App  // Update import
// ... rest of the file
```

**Rename directory structure:**

```bash
cd androidApp/src/androidMain/kotlin
mv com/example/cmpdemo2 com/yourcompany/yourapp
```

#### Update All Import Statements

Use Android Studio's **Find and Replace** (Cmd/Ctrl + Shift + R):

**Replace 1 - Package imports:**

- Find: `com.example.cmpdemo2`
- Replace: `com.yourcompany.yourapp`
- Scope: Whole project
- Click "Replace All"

**Replace 2 - Resource imports:**

- Find: `com.example.cmpdemo2.shared.resources`
- Replace: `com.yourcompany.yourapp.shared.resources`
- Scope: Whole project
- Click "Replace All"

### Step 6: Update iOS Bundle Identifier

1. Open `iosApp/iosApp.xcodeproj` in Xcode
2. Select the **iosApp** project in the navigator
3. Select the **iosApp** target
4. Go to the **General** tab
5. Under **Identity**, change **Bundle Identifier**:
   ```
   From: com.example.CMPDemo2
   To:   com.yourcompany.YourAppName
   ```

### Step 7: Update iOS Display Name (Optional)

In the same Xcode screen:

1. Under **Identity**, change **Display Name**:
   ```
   From: iosApp
   To:   Your App Name
   ```

### Step 8: Clean Build

```bash
# Clean all build artifacts
./gradlew clean

# Delete IDE caches
rm -rf .gradle
rm -rf .idea
rm -rf */build
rm -rf build
```

### Step 9: Reopen in Android Studio

1. **File** → **Open** → Select your project folder
2. Wait for Gradle sync to complete
3. **Build** → **Rebuild Project**
4. Run the Android app to verify everything works

### Step 10: Verify iOS (if applicable)

1. In terminal: `./gradlew :shared:assembleSharedDebugXCFramework`
2. Open `iosApp/iosApp.xcodeproj` in Xcode
3. Clean build folder: **Product** → **Clean Build Folder**
4. Run the app

## ✅ Verification Checklist

After renaming, verify:

- [ ] Android app builds and runs without errors
- [ ] iOS app builds and runs without errors
- [ ] No import errors in any files
- [ ] Compose resources load correctly
- [ ] App displays correct name on home screen
- [ ] No deprecation warnings during build
- [ ] Tests run successfully (if you have any)

## 🎯 Quick Reference: File Locations

| What to Change             | File Path                                               |
|----------------------------|---------------------------------------------------------|
| Root project name          | `settings.gradle.kts`                                   |
| Android app ID & namespace | `androidApp/build.gradle.kts`                           |
| Shared namespace           | `shared/build.gradle.kts`                               |
| Resource package           | `shared/build.gradle.kts`                               |
| MainActivity package       | `androidApp/src/androidMain/kotlin/.../MainActivity.kt` |
| App code package           | `shared/src/commonMain/kotlin/.../App.kt`               |
| Android manifest           | `androidApp/src/androidMain/AndroidManifest.xml`        |
| iOS bundle ID              | Xcode project settings                                  |

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
rm -rf .gradle
```

### Issue: Resources not found after rename

**Solution**: Make sure you updated the `packageOfResClass` in `shared/build.gradle.kts` and updated
all imports.

### Issue: iOS app crashes on launch

**Solution**: Rebuild the framework:

```bash
./gradlew :shared:assembleSharedDebugXCFramework
```

Then clean and rebuild in Xcode.

## 💡 Pro Tips

1. **Use Android Studio's refactoring tools** - They automatically update imports and references
2. **Do one step at a time** - Don't rename everything at once
3. **Commit between steps** - Use git to track changes and rollback if needed
4. **Test after each major change** - Catch issues early
5. **Keep a backup** - Make a copy before starting if you're unsure

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

- Should match your package name
- Must be unique on app stores
- Example: `com.yourcompany.yourapp`

### App Display Names

- Use Title Case: "My Awesome App"
- Keep it under 15 characters if possible
- Should be memorable and brandable

---

**Need help?** Open an issue on GitHub with the `question` label!