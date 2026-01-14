package com.yourcompany.yourapp.di

import com.yourcompany.yourapp.Greeting
import software.amazon.lastmile.kotlin.inject.anvil.AppScope
import software.amazon.lastmile.kotlin.inject.anvil.ContributesTo
import software.amazon.lastmile.kotlin.inject.anvil.SingleIn

@ContributesTo(AppScope::class)
@SingleIn(AppScope::class)
interface AppComponent {
    // Your provides methods here
    val greeting: Greeting
}
