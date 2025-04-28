package br.com.lucascordeiro.nexly

import android.app.Application
import logcat.AndroidLogcatLogger
import logcat.LogPriority

class NexlyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Initialize logcat
        AndroidLogcatLogger.installOnDebuggableApp(this, minPriority = LogPriority.DEBUG)
    }
}