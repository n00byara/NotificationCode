package ru.n00byara.notificationcode.components.clip

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.util.regex.Matcher
import java.util.regex.Pattern

class Clip(private val context: Context, private val contentText: CharSequence) {
    private val regExps = mutableListOf(
        ".*(\\d{6}).*",
        ".*(\\d{5}).*",
        ".*((\\d{3})[-\\s](\\d{3})).*",
        ".*(\\d{4})([^(\\d\\.\\d)][^(\\d:\\d)]).*",
        ".*([^\\*]\\d{4}).*"
    )

    fun addToClipBoard() {
        val manager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val code: String? = getCode()

        code?.let { text ->
            val clipData = ClipData.newPlainText("text", text)
            manager.setPrimaryClip(clipData)
        }
    }

    private fun getResultString(matcher: Matcher, index: Int): String {
        if (index == 2) return matcher.group(2) + matcher.group(3)
        return matcher.group(1)
    }

    private fun getCode(): String? {
        regExps.forEachIndexed { index, regExp ->
            val pattern = Pattern.compile(regExp)
            val matcher = pattern.matcher(contentText)
            val found = matcher.matches()

            if (found) return getResultString(matcher, index)
        }
        return null
    }
}