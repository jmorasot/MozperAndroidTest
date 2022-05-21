import org.gradle.api.artifacts.dsl.DependencyHandler

object Config {
    object Android {
        const val minSdk = 21
        const val targetSdk = 32
        const val buildTools = "30.0.2"
    }
}

object BuildVersions {
    const val kotlin = Classpath.Dependencies.Versions.kotlin
}

object Classpath {
    object Dependencies {
        internal object Versions {
            const val gradle = "3.6.3"
            const val kotlin = "1.6.10"
        }

        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    }
}

object Dependencies {
    internal object Versions {
        const val lifecycle = "2.4.1"
        const val room = "2.2.5"
        const val retrofit = "2.7.2"
        const val interceptor = "4.2.1"

        const val appcompat = "1.4.1"
        const val coroutines = "1.6.0"
        const val constraint = "2.1.1"
        const val recycler = "1.2.0-alpha03"
        const val card = "1.0.0"
        const val koin = "3.0.1"
        const val glide = "4.11.0"
        const val glideTransformations = "4.1.0"
        const val shimmerLayout = "2.1.0"
        const val skeleton = "1.1.2"
    }

    object Room {
        const val database = "androidx.room:room-runtime:${Versions.room}"
        const val processor = "androidx.room:room-compiler:${Versions.room}"
        const val coroutines = "androidx.room:room-ktx:${Versions.room}"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
    }

    object UI {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideTransformations =
            "jp.wasabeef:glide-transformations:${Versions.glideTransformations}"
        const val skeleton = "com.ethanhua:skeleton:${Versions.skeleton}"
        const val shimmerLayout = "io.supercharge:shimmerlayout:${Versions.shimmerLayout}"
    }

    object Build {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildVersions.kotlin}"
    }

    object Design {
        const val material = "com.google.android.material:material:${Versions.recycler}"
    }

    object Injection {
        const val koin = "io.insert-koin:koin-android:${Versions.koin}"
    }

    object AndroidX {
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recycler}"
        const val cardView = "androidx.cardview:cardview:${Versions.card}"
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    }
}

fun DependencyHandler.appCompat() {
    api(Dependencies.AndroidX.appCompat)
    api(Dependencies.AndroidX.constraintLayout)
    api(Dependencies.AndroidX.recyclerView)
    api(Dependencies.Design.material)
}

fun DependencyHandler.retrofit() {
    api(Dependencies.Retrofit.retrofit)
    api(Dependencies.Retrofit.gson)
    api(Dependencies.Retrofit.interceptor)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.AndroidX.lifecycle)
    implementation(Dependencies.AndroidX.lifecycleRuntime)
    implementation(Dependencies.AndroidX.lifecycleViewModel)
}

fun DependencyHandler.room() {
    api(Dependencies.Room.database)
    api(Dependencies.Room.coroutines)
    kapt(Dependencies.Room.processor)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.Build.coroutinesAndroid)
    implementation(Dependencies.Build.coroutines)
}

fun DependencyHandler.ui() {
    api(Dependencies.UI.glide)
    api(Dependencies.UI.glideTransformations)
    api(Dependencies.UI.shimmerLayout)
    api(Dependencies.UI.skeleton)
}

fun DependencyHandler.testing() {
    testImplementation(TestDependencies.Java.junit)
    androidTestImplementation(TestDependencies.Android.junit)
    androidTestImplementation(TestDependencies.Android.espresso)
}

fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}

fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}

fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}

private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}
