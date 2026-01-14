# Compose Multiplatform Clean Architecture Template

A modern, production-ready Compose Multiplatform project template with clean architecture,
dependency injection, code quality tools, and AGP 9 compatible configuration for Android and iOS.

[![Kotlin](https://img.shields.io/badge/Kotlin-2.3.0-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-1.9.3-brightgreen)](https://www.jetbrains.com/compose-multiplatform/)
[![AGP](https://img.shields.io/badge/AGP-8.14.3-green)](https://developer.android.com/studio/releases/gradle-plugin)
[![kotlin-inject](https://img.shields.io/badge/kotlin--inject-0.9.0-orange)](https://github.com/evant/kotlin-inject)
[![Spotless](https://img.shields.io/badge/Spotless-8.1.0-purple)](https://github.com/diffplug/spotless)

## ✨ What Makes This Template Special?

This template solves a critical issue with the default Kotlin Multiplatform wizard and provides a *
*production-ready** foundation with enterprise-grade tooling:

### The Problem with Default Templates

The standard KMP wizard generates projects that combine `kotlinMultiplatform` and
`androidApplication` plugins in a single module, which triggers deprecation warnings that will break
with Android Gradle Plugin 9.0.0+.

### This Template's Solution

✅ **Proper separation**: Shared KMP code in dedicated modules  
✅ **Modern plugin**: Uses `com.android.kotlin.multiplatform.library`  
✅ **Clean architecture**: Clear boundaries between shared and platform code  
✅ **Dependency Injection**: kotlin-inject + kotlin-inject-anvil for compile-safe DI  
✅ **Design System**: Dedicated module for shared UI components and theming  
✅ **Code Quality**: Spotless + ktlint + Compose lints pre-configured  
✅ **No warnings**: Future-proof for AGP 9.0.0+  
✅ **Production-ready**: Battle-tested structure for real-world apps

## 🏗️ Project Structure

```
your-app-name/
├── shared/                          # Core business logic & utilities
│   ├── src/
│   │   ├── commonMain/             # Shared code (Platform, Greeting, DI)
│   │   ├── androidMain/            # Android-specific implementations
│   │   └── iosMain/                # iOS-specific implementations
│   └── build.gradle.kts            # Uses androidKotlinMultiplatformLibrary plugin
│
├── designsystem/                    # Shared UI Design System
│   ├── src/
│   │   ├── commonMain/
│   │   │   └── kotlin/
│   │   │       └── designsystem/
│   │   │           ├── Button.kt   # Reusable UI components
│   │   │           └── theme/      # Theme, Colors, Typography
│   │   ├── androidMain/            # Android-specific theme impl
│   │   └── iosMain/                # iOS-specific theme impl
│   └── build.gradle.kts            # Shared design system module
│
├── androidApp/                      # Pure Android application module
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   └── YourNameApplication.kt  # DI setup
│   │   │   ├── AndroidManifest.xml
│   │   │   └── res/                # App icon, strings, etc.
│   │   ├── test/                   # Unit tests
│   │   └── androidTest/            # Instrumentation tests
│   └── build.gradle.kts            # Pure Android app configuration
│
├── iosApp/                          # iOS application
│   ├── iosApp/
│   │   ├── ContentView.swift
│   │   └── iOSApp.swift
│   └── iosApp.xcodeproj
│
├── gradle/
│   └── libs.versions.toml          # Centralized dependency management
├── build.gradle.kts                # Root build with Spotless config
├── settings.gradle.kts             # Module configuration
└── .editorconfig                   # Code style configuration
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
4. Follow the [Renaming Instructions](RENAMING.md)

#### Option 2: Manual Clone

```bash
git clone https://github.com/yourusername/cmp-clean-starter.git MyAwesomeApp
cd MyAwesomeApp
# Follow RENAMING.md guide
```

### First Build

```bash
# Format code with Spotless
./gradlew spotlessApply

# Build for all platforms
./gradlew build

# Run Android app
./gradlew :androidApp:installDebug

# Build iOS framework
./gradlew :shared:assembleSharedDebugXCFramework
# Then open iosApp/iosApp.xcodeproj in Xcode and run
```

## 📝 Renaming the Project

See the detailed [RENAMING.md](RENAMING.md) guide for step-by-step instructions on customizing this
template for your project. The guide covers:

- Updating package names across all modules
- Renaming theme and typography
- Updating Application class name
- iOS bundle identifier changes

## 🎯 Key Features

### Multi-Module Architecture

- **`shared/`**: Core business logic, platform abstractions, dependency injection setup
- **`designsystem/`**: Reusable UI components, theming system, typography
- **`androidApp/`**: Android application entry point with custom Application class
- **`iosApp/`**: iOS application with SwiftUI

### Dependency Injection (kotlin-inject)

- **Compile-safe DI**: No reflection, all checks at compile time
- **kotlin-inject-anvil**: Automatic component merging across modules
- **Scoped components**: `AndroidAppComponent`, `IosAppComponent`, shared `AppComponent`
- **Example setup included**: See `YourNameApplication.kt` and DI package structure

### Design System Module

- **Shared theming**: Material3 theme with platform-specific implementations
- **Custom components**: Ability to provide platform specific components with expect/actual
  implementations

### Code Quality Tools

#### Spotless + ktlint

- Automatic code formatting on build
- Consistent style across team
- Pre-configured with sensible defaults (feel free to change this!)
- Run manually: `./gradlew spotlessApply`

#### Compose Lints

- **Slack Compose Lint Checks**: Android Lint-based rules for Compose
- **Compose Rules**: ktlint-based rules integrated with Spotless
- Catches common Compose mistakes at build time
- Enforces best practices automatically

### Latest KMP Plugin

- **`androidKotlinMultiplatformLibrary`**: Official plugin from Google
- **No deprecation warnings**: Ready for AGP 9.0.0+
- **Optimized for KMP**: Better IDE support and build performance

## 🛠️ Technology Stack

| Component             | Version        | Purpose                               |
|-----------------------|----------------|---------------------------------------|
| Kotlin                | 2.3.0          | Programming language                  |
| Compose Multiplatform | 1.9.3          | UI framework                          |
| Android Gradle Plugin | 8.11.2         | Android build system                  |
| kotlin-inject         | 0.9.0          | Compile-safe dependency injection     |
| kotlin-inject-anvil   | 0.1.6          | Component merging for multi-module DI |
| KSP                   | 2.3.4          | Kotlin Symbol Processing              |
| Spotless              | 8.1.0          | Code formatting                       |
| ktlint                | (via Spotless) | Kotlin linter                         |
| Compose Lints         | 1.4.2          | Slack's Compose lint checks           |
| Compose Rules         | 0.4.26         | ktlint Compose rules                  |
| Android Target SDK    | 36             | Android API level                     |
| Android Min SDK       | 24             | Android 7.0+ support                  |

## 📚 What's Included

### Dependencies

- ✅ Compose Multiplatform (Material3, Foundation, Resources)
- ✅ kotlin-inject runtime & compiler
- ✅ kotlin-inject-anvil for multi-module DI
- ✅ Spotless with ktlint and Compose rules
- ✅ Slack Compose Lint checks
- ✅ Kotlin Test framework

### Configuration

- ✅ Gradle version catalogs (type-safe dependencies)
- ✅ KSP for compile-time code generation
- ✅ Proper JVM target configuration (Java 11)
- ✅ Multi-module dependency injection setup
- ✅ Automated code formatting on build
- ✅ Compose best practices enforcement

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

Then use in module `build.gradle.kts`:

```kotlin
sourceSets {
    commonMain.dependencies {
        implementation(libs.ktor.client.core)
    }
}
```

### Using Dependency Injection

**1. Define an injectable class in `shared`:**

```kotlin
@Inject
class GreetingService(private val platform: Platform) {
    fun greet(): String = "Hello from ${platform.name}!"
}
```

**2. Add to component:**

```kotlin
@ContributesTo(AppScope::class)
interface GreetingComponent {
    val greetingService: GreetingService
}
```

**3. Use in Android:**

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val app = application as YourNameApplication
        val greeting = app.component.greetingService.greet()

        setContent {
            YourAppTheme {
                Text(greeting)
            }
        }
    }
}
```

### Customizing the Design System

**Update theme colors in `designsystem/src/commonMain/kotlin/designsystem/theme/Color.kt`:**

```kotlin
val md_theme_light_primary = Color(0xFF6750A4)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
// ... customize all colors
```

**Update typography in `Theme.kt`:**

```kotlin
val YourAppTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = YourCustomFont,
        fontWeight = FontWeight.Bold,
        fontSize = 57.sp,
    ),
    // ... customize all text styles
)
```

### Running Code Quality Checks

```bash
# Format all code
./gradlew spotlessApply

# Check formatting without changes
./gradlew spotlessCheck

# Run all lints
./gradlew lint

# Run tests
./gradlew test
```

### Adding New Platforms

To add desktop support:

**1. Update `shared/build.gradle.kts`:**

```kotlin
jvm("desktop")
```

**2. Add desktop source set:**

```kotlin
val desktopMain by getting {
    dependencies {
        implementation(compose.desktop.currentOs)
    }
}
```

**3. Create desktop app module** or add desktop-specific code

## 🐛 Troubleshooting

### "Unresolved reference: Res"

**Solution**: Check imports use the correct package:

```kotlin
import com.yourcompany.yourapp.shared.resources.Res
```

### iOS Framework Not Found

**Solution**:

```bash
./gradlew clean
./gradlew :shared:assembleSharedDebugXCFramework
```

Then rebuild in Xcode.

### Spotless Formatting Fails

**Solution**:

```bash
./gradlew spotlessApply
```

This will auto-fix most formatting issues.

### KSP Generated Code Not Found

**Solution**: Clean and rebuild:

```bash
./gradlew clean
./gradlew :shared:kspCommonMainKotlinMetadata
./gradlew build
```

### Dependency Injection Component Not Generated

**Solution**: Check that:

1. KSP plugin is applied to the module
2. kotlin-inject dependencies are in `commonMain`
3. `@Inject` and `@ContributesTo` annotations are used correctly
4. Rebuild the project

## 📖 Learn More

- [Kotlin Multiplatform Documentation](https://kotlinlang.org/docs/multiplatform.html)
- [Compose Multiplatform Documentation](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Android KMP Library Plugin Guide](https://developer.android.com/kotlin/multiplatform/plugin)
- [kotlin-inject Documentation](https://github.com/evant/kotlin-inject)
- [kotlin-inject-anvil Documentation](https://github.com/amzn/kotlin-inject-anvil)
- [Spotless Documentation](https://github.com/diffplug/spotless)

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open
an issue first to discuss what you would like to change.

### Areas for Contribution

- [ ] Desktop platform support
- [ ] Web (Wasm) platform support
- [ ] Navigation library integration
- [ ] Network layer example (Ktor/Apollo-Kotlin)
- [ ] Local database example (SQLDelight/Room)
- [ ] CI/CD configuration (GitHub Actions)
- [ ] Documentation improvements

## 📄 License

This template is available under the MIT License. See [LICENSE](LICENSE) for details.

## 🙏 Acknowledgments

This template was created to address the deprecation warnings in the default Kotlin Multiplatform
wizard and to provide a production-ready starting point with modern tooling. It follows official
recommendations from:

- [Android's KMP Plugin Documentation](https://developer.android.com/kotlin/multiplatform/plugin)
- [JetBrains' KMP Project Structure Guide](https://kotl.in/kmp-project-structure-migration)
- [kotlin-inject Best Practices](https://github.com/evant/kotlin-inject)

Special thanks to:

- JetBrains for Compose Multiplatform and Kotlin
- Google for Android tooling and active development of Compose
- Evan Tatarka for `kotlin-inject`
- Amazon for `kotlin-inject-anvil`
- Slack for Compose Lint Checks
- Anthropic for Claude, which assisted in the design and documentation of this template

## 💬 Support

- **Issues**: [GitHub Issues](https://github.com/yourusername/kmp-clean-starter/issues)
- **Discussions
  **: [GitHub Discussions](https://github.com/yourusername/kmp-clean-starter/discussions)
- **Stack Overflow**: Tag questions with `kotlin-multiplatform` and `compose-multiplatform`

---

**Star ⭐ this repository if you find it useful!**

Made with ❤️ for the Kotlin Multiplatform community
