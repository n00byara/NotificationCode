package ru.n00byara.notificationcode.components.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.components.application.ApplicationsList
import ru.n00byara.notificationcode.components.clip.Clip

class NotificationService : NotificationListenerService() {
    val prefs = ModuleApplication.appContext.prefs(Constants.SETTINGS_NAME)
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val applicationsList = ApplicationsList(this.baseContext).getAllApplications()
        val appInfo = applicationsList.find { it.packageName == sbn.packageName }

        if (prefs.getInt(Constants.USE_CASE) == 1) {
            appInfo?.let { appInfo ->
                val notification = sbn.notification

                if (appInfo.isSystem) {
                    notification.extras?.let { extras ->
                        extras.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                            Clip(this.baseContext, contentText, prefs = prefs)
                        }
                    }
                } else {
                    if (appInfo.isActive) {
                        notification.extras?.let { extras ->
                            extras.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                                if (appInfo.name == Constants.SHAZAM_APP_NAME && notification.channelId == Constants.SHAZAM_CHANNEL_ID) {
                                    val contentTitle = extras.getCharSequence(Constants.EXTRA_TITLE)

                                    Clip(this.baseContext, contentText, contentTitle, prefs = prefs)
                                } else {
                                    Clip(this.baseContext, contentText, prefs = prefs)
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