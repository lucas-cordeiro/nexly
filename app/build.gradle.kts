plugins {
    alias(libs.plugins.android.application)
}

apply(from = "$rootDir/common-android.gradle")

android {
    namespace = "br.com.lucascordeiro.nexly"
    compileSdk = 35

    defaultConfig {
        applicationId = "br.com.lucascordeiro.nexly"
    }
}

dependencies {
    // Shared
    implementation(project(":app:shared:ui"))
    implementation(project(":app:shared:network"))
    implementation(project(":app:shared:navigation"))

    // Feature
    implementation(project(":app:feature:home"))
    implementation(project(":app:feature:details"))


    implementation(libs.utils.appstartup)
}