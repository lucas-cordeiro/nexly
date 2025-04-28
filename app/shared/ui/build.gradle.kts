plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/common-android.gradle")

android {
    namespace = "br.com.lucascordeiro.nexly.shared.ui"
}