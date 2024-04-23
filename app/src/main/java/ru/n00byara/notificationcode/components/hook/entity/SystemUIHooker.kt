package ru.n00byara.notificationcode.components.hook.entity

import android.app.Notification
import android.content.Context
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.constructor
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.type.android.ContextClass
import com.highcapable.yukihookapi.hook.type.android.NotificationClass
import com.highcapable.yukihookapi.hook.type.android.Notification_BuilderClass
import com.highcapable.yukihookapi.hook.type.android.ParcelClass
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.components.application.HookedAppInfo
import ru.n00byara.notificationcode.components.clip.Clip

object SystemUIHooker : YukiBaseHooker() {
    override fun onHook() {
        loadApp {
            val prefs = ModuleApplication().prefs(Constants.SETTINGS_NAME)
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
                if (prefs.getInt(Constants.USE_CASE) == 0) {
                    val notification = instance<Notification>()

                    context?.let { context ->
                        val appInfo = HookedAppInfo(context)

                        if (appInfo.isSystem) {
                            notification.extras?.let { extras ->
                                extras.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                                    Clip(appContext!!, contentText, prefs = prefs)
                                }
                            }
                        } else {
                            if (appInfo.isActive) {
                                notification.extras?.let { extras ->
                                    extras.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                                        if (appInfo.name == Constants.SHAZAM_APP_NAME && notification.getChannelId() == Constants.SHAZAM_CHANNEL_ID) {
                                            val contentTitle = extras.getCharSequence(Constants.EXTRA_TITLE)

                                            Clip(appContext!!, contentText, contentTitle, prefs = prefs)
                                        } else {
                                            Clip(appContext!!, contentText, prefs = prefs)
                                        }
                                    }
                                }
                            } else {
                                // .. ??
                            }
                        }
                    }
                }
            }
        }
    }
}