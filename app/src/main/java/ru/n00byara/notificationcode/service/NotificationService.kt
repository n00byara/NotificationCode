package ru.n00byara.notificationcode.service

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.highcapable.yukihookapi.hook.factory.prefs
import com.highcapable.yukihookapi.hook.xposed.application.ModuleApplication
import ru.n00byara.notificationcode.Constants
import ru.n00byara.notificationcode.application.ApplicationsList
import ru.n00byara.notificationcode.clip.Clip

class NotificationService : NotificationListenerService() {
    val prefs = ModuleApplication.appContext.prefs(Constants.SETTINGS_NAME)
    override fun onNotificationPosted(sbn: StatusBarNotification) {
        val applicationsList = ApplicationsList(this.baseContext).getAllApplications()
        val appInfo = applicationsList.find { it.packageName == sbn.packageName }

        if (this.prefs.getInt(Constants.USE_CASE) == 1) {
            appInfo?.let { appInfo ->
                val notification = sbn.notification

                if (appInfo.isSystem) {
                    notification.extras?.let { extras ->
                        extras.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                            Clip(
                                context = this.baseContext,
                                contentText = contentText,
                                prefs = this.prefs
                            )
                        }
                    }
                } else {
                    if (appInfo.isActive) {
                        notification.extras?.let { extras ->
                            extras.getCharSequence(Constants.EXTRA_TEXT)?.let { contentText ->
                                if (appInfo.name == Constants.SHAZAM_APP_NAME && notification.channelId == Constants.SHAZAM_CHANNEL_ID) {
                                    val contentTitle = extras.getCharSequence(Constants.EXTRA_TITLE)

                                    Clip(
                                        context = this.baseContext,
                                        contentText = contentText,
                                        contentTitle = contentTitle,
                                        prefs = this.prefs
                                    )
                                } else {
                                    Clip(context = this.baseContext,
                                        contentText = contentText,
                                        prefs = this.prefs
                                    )
                                }
                            }
                        }
                    } else {
                        // Do nothing
                    }
                }
            }
        }
    }
}