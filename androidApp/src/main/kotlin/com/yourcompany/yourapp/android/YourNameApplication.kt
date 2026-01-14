package com.yourcompany.yourapp.android

import android.app.Application
import com.yourcompany.yourapp.di.AndroidAppComponent
import com.yourcompany.yourapp.di.create

class YourNameApplication : Application() {
    val component: AndroidAppComponent by lazy {
        AndroidAppComponent::class.create(this)
    }
}
