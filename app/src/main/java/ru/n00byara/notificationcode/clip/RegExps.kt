package ru.n00byara.notificationcode.clip

object RegExps {
    val regExps = listOf(
        ".*?\n?.*?(\\d{6}).*?\n?.*?",
        ".*?\n?.*?(\\d{5}).*?\n?.*?",
        ".*?\n?.*?(\\d{4})([^(\\d\\.\\d)][^(\\d:\\d)]).*?\n?.*?",
        ".*?\n?.*?([^\\*]?\\d{4}).*?\n?.*?"
    )

    val regExpForCompositeCode = ".*?\n?.*?(.*(\\d{3})[-\\s](\\d{3}).*).*?\n?.*?"

    val regExsForPhone = listOf(
        ".*?\n?.*?(\\+\\d?.\\(\\d{3}\\)?.\\d{3}[\\s|\\-]\\d{2}[\\s|\\-]\\d{2}).*?\n?.*?",
        ".*?\n?.*?(\\d{2}-\\d{2}-\\d{2}).*?\n?.*?"
    )

    val regExpForTrackNumber = listOf(".*?\n?.*?(\\w\\w\\d{9}\\w\\w).*?\n?.*?")
}