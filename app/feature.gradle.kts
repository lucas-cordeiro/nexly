plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.serialization)
}

apply(from = "$rootDir/common-feature.gradle")

dependencies {
    // Shared
    implementation(project(":app:shared:ui"))

    implementation(libs.utils.serialization)
}
