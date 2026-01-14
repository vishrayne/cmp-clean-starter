package com.yourcompany.yourapp

import androidx.compose.ui.window.ComposeUIViewController
import com.yourcompany.yourapp.di.IosAppComponent
import com.yourcompany.yourapp.di.createComponent
import platform.UIKit.UIViewController

@Suppress("ktlint:standard:function-naming")
fun MainViewController(): UIViewController {
    val component = IosAppComponent::class.createComponent()

    return ComposeUIViewController { App(component.greeting) }
}
