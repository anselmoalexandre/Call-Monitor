package mz.co.bilheteira.buildsrc

object Dependencies {

    object AndroidX {
        val core = "androidx.core:core-ktx:1.7.0"
        val appCompat = "androidx.appcompat:appcompat:1.5.1"
        val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Navigation {
            val navigationFragment = "androidx.navigation:navigation-fragment-ktx:2.5.2"
            val navigationUI = "androidx.navigation:navigation-ui-ktx:2.5.2"
        }
    }

    object Material {
        val mdc = "com.google.android.material:material:1.5.0-alpha04"
    }

    object Ktor {
        val ktor = "io.ktor:ktor:1.2.5"
        val ktorServer = "io.ktor:ktor-server-netty:1.2.5"
        val ktorGson = "io.ktor:ktor-gson:1.2.5"
    }

    object Room {
        val room = "androidx.room:room-ktx:2.4.2"
        val runtime = "androidx.room:room-runtime:2.4.2"
        val kapt = "androidx.room:room-compiler:2.4.2"
    }

    object Retrofit {
        val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        val converter = "com.squareup.retrofit2:converter-moshi:2.9.0"
        val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.8"
    }

    object Moshi {
        val moshi = "com.squareup.moshi:moshi-kotlin:1.13.0"
        val adapter = "com.squareup.moshi:moshi-adapters:1.13.0"
        val codegen = "com.squareup.moshi:moshi-kotlin-codegen:1.13.0"
    }

    object Lottie {
        val lottie = "com.airbnb.android:lottie:5.2.0"
    }

    object Coroutines {
        val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2"
    }

    object SupportLibrary {
        val support = "androidx.legacy:legacy-support-v4:1.0.0"
    }

    object DaggerHilt {
        val hilt = "com.google.dagger:hilt-android:2.42"
        val kapt = "com.google.dagger:hilt-android-compiler:2.42"
    }

    object Timber {
        val timber = "com.jakewharton.timber:timber:5.0.1"
    }

    object Apache {
        val commons = "org.apache.commons:commons-io:1.3.2"
    }

    object Test {
        val coreTest = "androidx.test:core:1.4.0"
        val coreTestExtenstion = "androidx.test.ext:junit:1.1.3"
        val JUnit = "junit:junit:4.13.2"
        val hamcrest = "org.hamcrest:hamcrest-all:1.3"
        val coreTesting = "androidx.arch.core:core-testing:2.1.0"
        val roboeletric = "org.robolectric:robolectric:4.3.1"
        val coroutinesTests = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.2"
        val truth = "com.google.truth:truth:1.0.1"
        val expresso = "androidx.test.espresso:espresso-core:3.4.0"
    }
}