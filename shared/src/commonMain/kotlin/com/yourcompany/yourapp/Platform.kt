package com.yourcompany.yourapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
