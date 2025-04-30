plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/common-android.gradle")

android {
    namespace = "br.com.lucascordeiro.nexly.shared.test"
}

dependencies {
    implementation(project(":app:shared:network"))

    //Network
    implementation(libs.ktor.core)
    implementation(libs.ktor.okhttp)
    implementation(libs.ktor.contentnegotiation)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.logging)
    implementation(libs.utils.secrets)

    //Test
    implementation(libs.coroutines.test)
    implementation(libs.koin.test)
    implementation(libs.ktor.mock)
    implementation(libs.mockk.android)
    implementation(libs.junit)
    implementation(libs.ymir.test)
    implementation(libs.androidx.ui.test.junit4)
    implementation(libs.test.androidx.runner)
    implementation(libs.androidx.ui.tooling)
    implementation(libs.androidx.ui.test.manifest)
}