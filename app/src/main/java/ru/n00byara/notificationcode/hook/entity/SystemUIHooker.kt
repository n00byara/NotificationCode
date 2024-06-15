package ru.n00byara.notificationcode.hook.entity

import android.app.Notification
import android.content.Context
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.NotificationClass
import com.highcapable.yukihookapi.hook.type.android.Notification_BuilderClass
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.NCApplication
import ru.n00byara.notificationcode.application.HookedAppInfo
import ru.n00byara.notificationcode.clip.Clip

object SystemUIHooker : YukiBaseHooker() {
    override fun onHook() {
        loadApp {
            val prefs = NCApplication().prefs(Constants.SETTINGS_NAME)

            Notification_BuilderClass.constructor {
                param(ContextClass, NotificationClass)
            }.hook()
                .after {
                    val hookedContext = args(0).cast<Context>()!!
                    val notification = args(1).cast<Notification>()!!

                    val appInfo = HookedAppInfo(hookedContext)

                    if (!(appInfo.isSystem || appInfo.isActive)) return@after

                    notification.extras?.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                        var contentTitle: CharSequence? = null
                        if (Constants.SHAZAM_CHANNEL_ID == notification.channelId) {
                            notification.extras.getCharSequence(Constants.EXTRA_TITLE)?.let {
                                contentTitle = it
                            }
                        }

                        Clip(
                            context = appContext!!,
                            contentText = contentText,
                            contentTitle = contentTitle,
                            prefs = prefs
                        )
                    }
                }
        }
    }
}