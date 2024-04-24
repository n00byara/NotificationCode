package ru.n00byara.notificationcode.components.terminal

import java.io.BufferedReader
import java.io.InputStreamReader

object Terminal {
    fun restartSystemui() {
        this.runCommand("killall com.android.systemui")
    }

    private fun runCommand(cmd: String): String {
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
        return output.toString()
    }
}