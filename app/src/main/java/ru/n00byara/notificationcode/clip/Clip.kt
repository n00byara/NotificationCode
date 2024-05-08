package ru.n00byara.notificationcode.clip

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge
import ru.n00byara.notificationcode.Constants
import java.util.regex.Matcher
import java.util.regex.Pattern

class Clip(
    private val context: Context,
    private val contentText: CharSequence,
    private val contentTitle: CharSequence? = null,
    val prefs: YukiHookPrefsBridge
) {

    val regExps = RegExps.regExps
    val compositeRegExp = RegExps.regExpForCompositeCode
    val phoneRegExps = RegExps.regExsForPhone
    val trackNumberRegExp = RegExps.regExpForTrackNumber

    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher
    private var found = false

    init {
        if (this.contentTitle != null) {
            this.addToClipBoard("${this.contentTitle} ${this.contentText}")
        } else {
            val text = getText()

            text?.let { contentText ->
                this.addToClipBoard(contentText)
            }
        }
    }

    private fun addToClipBoard(text: String) {
        val manager: ClipboardManager = this.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        val clipData = ClipData.newPlainText("text", text)
        manager.setPrimaryClip(clipData)
    }

    private fun getText(): String? {
        var result: String?

        result = getFromList(this.phoneRegExps)
        if (result != null && this.prefs.getBoolean(Constants.SWITCH_PHONE)) return result

        result = getFromList(this.regExps)
        if (result != null && this.prefs.getBoolean(Constants.SWITCH_CODE)) return result

        result = getCompositeCode()
        if (result != null && this.prefs.getBoolean(Constants.SWITCH_CODE)) return result

        result = getFromList(this.trackNumberRegExp)
        if (result != null && this.prefs.getBoolean(Constants.SWITHC_TRACK_NUMBER)) return result

        return result
    }

    private fun getFromList(list: List<String>): String? {

        list.forEach { regExp ->
            this.pattern = Pattern.compile(regExp)
            this.matcher = pattern.matcher(this.contentText)
            this.found = matcher.matches()

            if (this.found) return matcher.group(1)
        }
        return null
    }

    private fun getCompositeCode(): String? {
        this.pattern = Pattern.compile(this.compositeRegExp)
        this.matcher = pattern.matcher(this.contentText)
        this.found = matcher.matches()

        if (this.found) return this.matcher.group(2) + this.matcher.group(3)

        return null
    }
}