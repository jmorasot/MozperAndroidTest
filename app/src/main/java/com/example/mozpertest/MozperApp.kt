package com.example.mozpertest

import android.app.Application
import com.example.mozpertest.features.login.di.modules.LoginModule
import com.example.mozpertest.features.networking.di.modules.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MozperApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initInjection(this)
    }

    private fun initInjection(app: Application) {
        startKoin {
            androidContext(app)
            modules(
                listOf(
                    NetworkModule.module,
                    LoginModule.module
                )
            )
        }
    }
}
