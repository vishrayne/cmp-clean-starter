plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.androidLint) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.spotless) apply false
}

subprojects {
    apply(plugin = "com.diffplug.spotless")

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt")

            ktlint("1.7.1")
                .customRuleSets(
                    // Add Compose-specific ktlint rules
                    listOf(
                        "io.nlopez.compose.rules:ktlint:${rootProject.libs.versions.compose.rules.get()}"
                    )
                )
                .editorConfigOverride(
                    mapOf(
                        "indent_style" to "space",
                        "max_line_length" to "100",
                        "ktlint_standard_no-wildcard-imports" to "enabled",
                        "ktlint_standard_multiline-expression-wrapping" to "disabled",
                        "ktlint_standard_parameter-list-wrapping" to "enabled",
                        "ktlint_standard_argument-list-wrapping" to "enabled",
                        "ktlint_class_signature_rule_force_multiline_when_parameter_count_greater_or_equal_than" to "1",
                        "ktlint_function_signature_rule_force_multiline_when_parameter_count_greater_or_equal_than" to "2",
                        "ktlint_standard_function-expression-body" to "disabled",
                        "ktlint_standard_no-empty-first-line-in-class-body" to "disabled",
                        "ktlint_function_naming_ignore_when_annotated_with" to "Composable"
                    ),
                )
        }

        kotlinGradle {
            target("*.gradle.kts")
            ktlint("1.7.1")
        }

        format("xml") {
            target("**/*.xml")
            targetExclude("**/build/**/*.xml")
            leadingTabsToSpaces(4)
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}
