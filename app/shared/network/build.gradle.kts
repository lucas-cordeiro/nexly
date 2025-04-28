plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.serialization)
}

apply(from = "$rootDir/common-android.gradle")

android {
    namespace = "br.com.lucascordeiro.nexly.shared.network"
}

dependencies {
    //Network
    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.contentnegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.utils.serialization)
}