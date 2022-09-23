package mz.co.bilheteira.buildsrc

object Dependencies {

    object AndroidX {
        const val core = "androidx.core:core-ktx:1.9.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Navigation {
            const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.5.2"
            const val navigationUI = "androidx.navigation:navigation-ui-ktx:2.5.2"
        }
    }

    object Material {
        const val mdc = "com.google.android.material:material:1.8.0-alpha01"
    }

    object Ktor {
        const val ktor = "io.ktor:ktor:1.2.5"
        const val ktorServer = "io.ktor:ktor-server-netty:1.2.5"
        const val ktorGson = "io.ktor:ktor-gson:1.2.5"
    }

    object Room {
        const val room = "androidx.room:room-ktx:2.4.2"
        const val runtime = "androidx.room:room-runtime:2.4.2"
        const val kapt = "androidx.room:room-compiler:2.4.2"
        const val annotationProcessor = "org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.5.0"
        const val secure = "net.zetetic:android-database-sqlcipher:4.4.0"
    }

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2"
    }

    object SupportLibrary {
        const val support = "androidx.legacy:legacy-support-v4:1.0.0"
    }

    object Dagger {
        const val hilt = "com.google.dagger:hilt-android:2.44"
        const val kapt = "com.google.dagger:hilt-android-compiler:2.44"
    }

    object Timber {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Test {
        const val coreTest = "androidx.test:core:1.4.0"
        const val coreTestExtenstion = "androidx.test.ext:junit:1.1.3"
        const val JUnit = "junit:junit:4.13.2"
        const val junit = "androidx.test.ext:junit:1.1.3"
        const val hamcrest = "org.hamcrest:hamcrest-all:1.3"
        const val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        const val mockkandroid = "io.mockk:mockk-android:1.12.3"
        const val mockkagent = "io.mockk:mockk-agent-jvm:1.12.3"
        const val roboeletric = "org.robolectric:robolectric:4.3.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2"
        const val truth = "com.google.truth:truth:1.0.1"
        const val expresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}