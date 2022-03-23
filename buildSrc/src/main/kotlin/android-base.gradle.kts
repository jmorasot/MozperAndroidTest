plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

dependencies {
    implementation(Dependencies.Build.kotlin)
    implementation(Dependencies.Build.coroutines)
}
