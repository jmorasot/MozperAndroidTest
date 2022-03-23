plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Config.Android.targetSdk)

    defaultConfig {
        applicationId = "com.example.mozpertest"
        minSdkVersion(Config.Android.minSdk)
        targetSdkVersion(Config.Android.targetSdk)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        signingConfig = signingConfigs.getByName("debug")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(file("proguard-android-optimize.txt"), file("proguard-rules.pro"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        val options = this
        options.jvmTarget = "1.8"
    }

    viewBinding {
        isEnabled = true
    }

    flavorDimensions("app")

    productFlavors {
        create("production") {
            dimension = "app"
            signingConfig = signingConfigs.getByName("debug")
            applyValues(this, GradleProperties.ProjectProperties.MOZPER_PRODUCTION)
        }

        create("dev") {
            dimension = "app"
            signingConfig = signingConfigs.getByName("debug")
            applyValues(this, GradleProperties.ProjectProperties.MOZPER_STAGE)
        }
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.4.1")
    appCompat()
    testing()
    lifecycle()
    retrofit()
    coroutines()
    room()

    implementation(Dependencies.Injection.koin)
    implementation(Dependencies.AndroidX.recyclerView)
    implementation(Dependencies.AndroidX.cardView)
}

fun applyValues(
    productFlavor: com.android.build.gradle.internal.dsl.ProductFlavor,
    properties: GradleProperties.ProjectProperties
) {
    productFlavor.apply {
        buildConfigField(
            "String",
            "BASE_URL",
            GradleProperties.getProperty(properties, "api.url")!!
        )
    }
}
