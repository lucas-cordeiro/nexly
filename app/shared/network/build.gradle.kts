plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.serialization)
    alias(libs.plugins.secrets)
}

apply(from = "$rootDir/common-android.gradle")

android {
    namespace = "br.com.lucascordeiro.nexly.shared.network"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //Network
    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.contentnegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.utils.secrets)
}