plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    // android gradle plugin, required by custom plugin
    implementation("com.android.tools.build:gradle:4.0.1")
    // kotlin plugin, required by custom plugin
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
}
