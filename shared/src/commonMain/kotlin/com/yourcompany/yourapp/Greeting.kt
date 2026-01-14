package com.yourcompany.yourapp

import me.tatarka.inject.annotations.Inject

@Inject
class Greeting(
    private val platform: Platform,
) {
    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
