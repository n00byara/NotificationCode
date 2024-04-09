package ru.n00byara.notificationcode.hook.entity

import android.app.Notification
import android.content.Context
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.NotificationClass
import com.highcapable.yukihookapi.hook.type.android.Notification_BuilderClass
import com.highcapable.yukihookapi.hook.type.android.ParcelClass
import ru.n00byara.notificationcode.applicationinfo.HookedAppInfo
import ru.n00byara.notificationcode.clip.Clip

object SystemUIHooker : YukiBaseHooker() {
    const val EXTRA_TEXT = "android.text"
    const val EXTRA_TITLE = "android.title"
    override fun onHook() {
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
            }.hook().after {
                val notification = instance<Notification>()

                context?.let { context ->
                    val appInfo = HookedAppInfo(context)

                    if (appInfo.isSystem) {
                        notification.extras?.let { extras ->
                            extras.getCharSequence(EXTRA_TEXT)?.let { contentText ->
                                Clip(appContext!!, contentText)
                            }
                        }
                    } else {
                        if (appInfo.isActive) {
                            notification.extras?.let { extras ->
                                extras.getCharSequence(EXTRA_TEXT)?.let { contentText ->
                                    if (appInfo.name == "Shazam" && notification.getChannelId() == "notification_shazam_match_v1") {
                                        val contentTitle = extras.getCharSequence(EXTRA_TITLE)

                                        Clip(appContext!!, contentText, contentTitle)
                                    } else {
                                        Clip(appContext!!, contentText)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}