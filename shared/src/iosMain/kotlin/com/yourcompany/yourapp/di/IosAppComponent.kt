package com.yourcompany.yourapp.di

import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.MergeComponent
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn
import kotlin.reflect.KClass

@MergeComponent(AppScope::class)
@SingleIn(AppScope::class)
abstract class IosAppComponent : AppComponent

/**
 * **Visibility Constraint:**
 * Code within the shared iOS source set (`iosMain`) cannot directly access create functions generated
 * inside specific target source sets (e.g., `iosArm64`, `iosSimulatorArm64`).
 * See: https://kotlinlang.org/docs/whatsnew20.html#separation-of-common-and-platform-sources-during-compilation
 *
 * **Solution:**
 * Define an `expect` function adjacent to the component class. The compiler plugin will automatically
 * generate the `actual` implementation to instantiate the component.
 *
 * **Configuration:**
 * - The function must be annotated with `@MergeComponent.CreateComponent`.
 * - The return type must match the component class.
 * - The parameters must match the component's arguments.
 * - (Optional) You may use `KClass<ComponentType>` as a receiver.
 *
 * **Important:**
 * DO NOT name this function `create`. This prevents naming conflicts with the `create` function
 * that `kotlin-inject` generates within the iOS target source sets. Use a distinct name like `createIosComponent`.
 *
 * **Note on IDE Warnings:**
 * You may see a warning: "No actual for expect declaration...". This is normal, as the `actual`
 * is generated during the build. These will resolve once you compile the specific target.
 * However, if your OCD is exploding, you can force the generation immediately by running:
 * `./gradlew compileKotlinIosArm64`
 * OR
 * `./gradlew compileKotlinIosSimulatorArm64`
 */
@MergeComponent.CreateComponent
expect fun KClass<IosAppComponent>.createComponent(): IosAppComponent
