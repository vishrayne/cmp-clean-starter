# Compose Multiplatform Clean Architecture Template

A modern, production-ready Compose Multiplatform project template with clean architecture and
future-proof configuration for Android and iOS.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.0-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.9.3-brightgreen)](https://www.jetbrains.com/compose-multiplatform/)
[![AGP](https://img.shields.io/badge/AGP-8.14.3-green)](https://developer.android.com/studio/releases/gradle-plugin)

## ✨ What Makes This Template Special?

This template solves a critical issue with the default Kotlin Multiplatform wizard: **it eliminates
deprecation warnings** and uses the officially recommended project structure that's compatible with
Android Gradle Plugin 9.0.0+.

### The Problem with Default Templates

The standard KMP wizard generates projects that combine `kotlinMultiplatform` and
`androidApplication` plugins in a single module, which triggers this warning:

```
w: The 'org.jetbrains.kotlin.multiplatform' plugin deprecated compatibility 
with Android Gradle plugin: 'com.android.application'
```

### This Template's Solution

✅ **Proper separation**: Shared KMP code in a dedicated module  
✅ **Modern plugin**: Uses `com.android.kotlin.multiplatform.library`  
✅ **Clean architecture**: Clear boundaries between shared and platform code  
✅ **No warnings**: Future-proof for AGP 9.0.0+  
✅ **Production-ready**: Optimized structure for real-world apps

## 🏗️ Project Structure

```
your-app-name/
├── shared/                          # Kotlin Multiplatform shared module
│   ├── src/
│   │   ├── commonMain/             # Shared Compose UI & business logic
│   │   │   ├── kotlin/
│   │   │   └── composeResources/
│   │   ├── androidMain/            # Android-specific implementations
│   │   ├── iosMain/                # iOS-specific implementations
│   │   └── commonTest/             # Shared tests
│   └── build.gradle.kts            # Uses androidKotlinMultiplatformLibrary plugin
│
├── androidApp/                      # Pure Android application module
│   ├── src/
│   │   └── androidMain/
│   │       ├── kotlin/
│   │       │   └── MainActivity.kt  # Thin wrapper calling shared App()
│   │       └── AndroidManifest.xml
│   └── build.gradle.kts            # Pure Android app configuration
│
├── iosApp/                          # iOS application
│   └── iosApp.xcodeproj
│
├── gradle/
│   └── libs.versions.toml          # Centralized dependency management
├── build.gradle.kts                # Root build configuration
└── settings.gradle.kts             # Module configuration
```

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Koala (2024.1.1) or later
- **JDK** 17 or later
- **Xcode** 15.0+ (for iOS development)
- **CocoaPods** (for iOS dependencies, if needed)

### Using This Template

#### Option 1: GitHub Template (Recommended)

1. Click **"Use this template"** button at the top of this repository
2. Create your new repository
3. Clone it locally
4. Follow the [Renaming Instructions](#-renaming-the-project) below

#### Option 2: Manual Clone

```bash
git clone https://github.com/yourusername/compose-multiplatform-clean-template.git MyAwesomeApp
cd MyAwesomeApp
# Follow renaming instructions below
```

### First Build

```bash
# Build for all platforms
./gradlew build

# Run Android app
./gradlew :androidApp:installDebug

# Build iOS framework
./gradlew :shared:assembleSharedDebugXCFramework
# Then open iosApp/iosApp.xcodeproj in Xcode and run
```

## 📝 Renaming the Project

See the detailed [RENAMING.md](RENAMING.md) guide for step-by-step instructions on how to rename
this template for your project.

**Quick summary:**

1. Update `rootProject.name` in `settings.gradle.kts`
2. Update `applicationId` and `namespace` in both modules
3. Update package names in code
4. Update iOS bundle identifier in Xcode
5. Rename directories to match new package structure

## 🎯 Key Features

### Shared Module (`shared/`)

- **Latest KMP Plugin**: Uses `com.android.kotlin.multiplatform.library` (AGP 8.12+)
- **Compose Multiplatform**: Share UI code across platforms

### Android App (`androidApp/`)

- **Pure Android module**: No KMP plugin conflicts
- **Minimal wrapper**: Just calls shared `App()` composable
- **Standard Android structure**: Familiar to Android developers
- **Compose tooling**: Full preview and debugging support

### iOS App (`iosApp/`)

- **Native iOS app**: Built with SwiftUI
- **Shared framework**: Consumes KMP shared code
- **Standard Xcode project**: Familiar to iOS developers

## 🛠️ Technology Stack

| Component             | Version | Purpose              |
|-----------------------|---------|----------------------|
| Kotlin                | 2.3.0   | Programming language |
| Compose Multiplatform | 1.9.3   | UI framework         |
| Android Gradle Plugin | 8.14.3  | Android build system |
| Android Target SDK    | 36      | Android API level    |
| Android Min SDK       | 24      | Android 7.0+ support |
| Lifecycle ViewModel   | 2.9.6   | State management     |

## 📚 What's Included

### Dependencies

- ✅ Compose Multiplatform (Material3, Foundation, Resources)
- ✅ Lifecycle ViewModel & Runtime Compose
- ✅ Activity Compose

### Configuration

- ✅ Android resource processing
- ✅ iOS framework generation

## 🎨 Customization

### Adding Dependencies

Dependencies are managed in `gradle/libs.versions.toml`:

```toml
[versions]
ktor = "3.0.0"

[libraries]
ktor-client-core = { module = "io.ktor:ktor-client-core", version.ref = "ktor" }

[plugins]
kotlinSerialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
```

Then use in `shared/build.gradle.kts`:

```kotlin
sourceSets {
    commonMain.dependencies {
        implementation(libs.ktor.client.core)
    }
}
```

### Adding New Platforms

To add desktop support:

1. Update `shared/build.gradle.kts`:

```kotlin
jvm("desktop")
```

2. Create `desktopApp` module or add desktop source set

3. Configure desktop-specific dependencies

## 🐛 Troubleshooting

### "Unresolved reference: Res"

**Solution**: Make sure imports use the correct package:

```kotlin
import com.yourcompany.yourapp.shared.resources.Res
import com.yourcompany.yourapp.shared.resources.*
```

### iOS Framework Not Found

**Solution**:

```bash
./gradlew clean
./gradlew :shared:assembleSharedDebugXCFramework
```

Then rebuild in Xcode.

### Compose Preview Not Working

**Solution**: Make sure `debugImplementation(compose.uiTooling)` is in `androidApp/build.gradle.kts`

### Build Warnings About Plugins

**Solution**: This template is already configured correctly! If you see warnings, check that you
haven't modified the plugin configuration.

## 📖 Learn More

- [Kotlin Multiplatform Documentation](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform Documentation](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Android KMP Library Plugin Guide](https://developer.android.com/kotlin/multiplatform/plugin)
- [KMP Project Structure Migration](https://kotl.in/kmp-project-structure-migration)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open
an issue first to discuss what you would like to change.

### Areas for Contribution

- [ ] Desktop platform support
- [ ] Web (Wasm) platform support
- [ ] Additional architecture examples (MVI, Clean Architecture)
- [ ] CI/CD configuration
- [ ] More comprehensive testing setup
- [ ] Documentation improvements

## 📄 License

This template is available under the MIT License. See [LICENSE](LICENSE) for details.

## 🙏 Acknowledgments

This template was created to address the deprecation warnings in the default Kotlin Multiplatform
wizard and follows the official recommendations from:

- [Android's KMP Plugin Documentation](https://developer.android.com/kotlin/multiplatform/plugin)
- [JetBrains' KMP Project Structure Guide](https://kotl.in/kmp-project-structure-migration)

## 💬 Support

- **Issues
  **: [GitHub Issues](https://github.com/yourusername/compose-multiplatform-clean-template/issues)
- **Discussions
  **: [GitHub Discussions](https://github.com/yourusername/compose-multiplatform-clean-template/discussions)
- **Stack Overflow**: Tag your questions with `kotlin-multiplatform` and `compose-multiplatform`

---

**Star ⭐ this repository if you find it useful!**

Made with ❤️ for the Kotlin Multiplatform community