plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/common-feature.gradle")

android {
    namespace = "br.com.lucascordeiro.nexly.feature.home"
}