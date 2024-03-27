package ru.n00byara.notificationcode.components.hook

import android.app.Notification

import com.highcapable.yukihookapi.YukiHookAPI.encase
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.type.android.NotificationClass
import com.highcapable.yukihookapi.hook.type.android.ParcelClass
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

import ru.n00byara.notificationcode.components.clip.Clip

@InjectYukiHookWithXposed
object Hook : IYukiHookXposedInit {
    private val EXTRA_TEXT = "android.text"

    override fun onHook() = encase {
        loadApp {
            NotificationClass.constructor {
                param(ParcelClass)
            }.hook {
                after {
                    val notification = instance<Notification>()

                    notification.extras?.let {
                        it.getCharSequence(EXTRA_TEXT)?.let { text ->
                            val clip = Clip(appContext!!, text)
                            clip.addToClipBoard()
                        }
                    }
                }
            }
        }
    }
}