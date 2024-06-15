package ru.n00byara.notificationcode

import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NCApplication : ModuleApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@NCApplication)
            modules(appModule)
        }
    }
}