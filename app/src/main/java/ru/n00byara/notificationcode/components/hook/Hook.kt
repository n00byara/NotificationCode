package ru.n00byara.notificationcode.components.hook

import android.app.Notification
import android.content.Context
import com.highcapable.yukihookapi.YukiHookAPI.encase
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.NotificationClass
import com.highcapable.yukihookapi.hook.type.android.Notification_BuilderClass
import com.highcapable.yukihookapi.hook.type.android.ParcelClass
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit
import ru.n00byara.notificationcode.components.clip.Clip

@InjectYukiHookWithXposed
object Hook : IYukiHookXposedInit {
    private val EXTRA_TEXT = "android.text"

    override fun onHook() = encase {
        loadApp {
            var context: Context? = null

            Notification_BuilderClass.constructor {
                param(ContextClass, NotificationClass)
            }.hook {
                after {
                    context = args(0).cast<Context>()
                }
            }

            NotificationClass.constructor {
                param(ParcelClass)
            }.hook {
                after {
                    val notification = instance<Notification>()

                    notification.extras.takeIf { context != null }?.let { text ->
                        val clip = Clip(context!!, text.getCharSequence(EXTRA_TEXT)!!)
                        clip.addToClipBoard()
                    }
                }
            }
        }
    }
}