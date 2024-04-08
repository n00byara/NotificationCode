package ru.n00byara.notificationcode.components.clip

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import java.util.regex.Matcher
import java.util.regex.Pattern

class Clip(private val context: Context, private val contentText: CharSequence) {
    private val regExps = mutableListOf(
        ".*?\n?.*?(\\d{6}).*?\n?.*?",
        ".*?\n?.*?(\\d{5}).*?\n?.*?",
        ".*?\n?.*?(\\d{4})([^(\\d\\.\\d)][^(\\d:\\d)]).*?\n?.*?",
        ".*?\n?.*?([^\\*]?\\d{4}).*?\n?.*?"
    )

    private val regExpForCompositeCode = ".*?\n?.*?(.*(\\d{3})[-\\s](\\d{3}).*).*?\n?.*?"
    private lateinit var pattern: Pattern
    private lateinit var matcher: Matcher
    private var found = false

    fun addToClipBoard() {
        val manager: ClipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val code: String? = getCode()

        code?.let { text ->
            val clipData = ClipData.newPlainText("text", text)
            manager.setPrimaryClip(clipData)
        }
    }

    private fun getCode(): String? {
        var result = getFromList(this.regExps)
        if (result != null) return result

        result = getCompositeCode()
        if(result != null) return result

        return null
    }

    private fun getCompositeCode(): String? {
        this.pattern = Pattern.compile(this.regExpForCompositeCode)
        this.matcher = pattern.matcher(this.contentText)
        this.found = matcher.matches()

        if (this.found) return matcher.group(2) + matcher.group(3)

        return null
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
}