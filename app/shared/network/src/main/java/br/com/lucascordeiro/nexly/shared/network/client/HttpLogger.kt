package br.com.lucascordeiro.nexly.shared.network.client

import io.ktor.client.plugins.logging.Logger
import logcat.logcat

class HttpLogger : Logger {
    override fun log(message: String) {
        logcat(TAG) { message }
    }

    companion object {
        const val TAG = "HttpLogger"
        fun default() = HttpLogger()
    }
}