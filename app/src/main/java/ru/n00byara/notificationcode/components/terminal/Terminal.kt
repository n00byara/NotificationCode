package ru.n00byara.notificationcode.components.terminal

import java.io.BufferedReader
import java.io.InputStreamReader

object Terminal {
    fun restartSystemui() {
        this.runCommand("killall com.android.systemui")
    }

    fun restartModule() {
        this.runCommand(
            "killall ru.n00byara.notificationcode && am start -n ru.n00byara.notificationcode/.ui.activities.SettingsActivity"
        )
    }

    private fun runCommand(cmd: String) {
        val process = Runtime.getRuntime().exec(arrayOf("su", "-c", cmd))

        val reader = BufferedReader(
            InputStreamReader(process.inputStream)
        )

        var read: Int
        val buffer = CharArray(4096)
        val output = StringBuffer()

        while (reader.read(buffer).also { read = it } > 0) {
            output.append(buffer, 0, read)
        }
        reader.close()

        process.waitFor()
    }
}