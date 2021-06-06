private typealias dep = LibraryDependency

object Dependencies {

    object Plugins {

        object Android : dep(
            name = "com.android.tools.build:gradle", version = Versions.androidGradlePlugin
        )

        object Kotlin : dep(
            name = "org.jetbrains.kotlin:kotlin-gradle-plugin", version = Versions.kotlin
        )

        object SqlDelight : dep(
            name = "com.squareup.sqldelight:gradle-plugin",
            version = Versions.sqlDelight
        )
    }

    object AndroidX {

        object AppCompat : dep(name = "androidx.appcompat:appcompat", version = Versions.supportLib)

        object Annotation : dep(
            name = "androidx.annotation:annotation", version = Versions.androidAnnotation
        )

        object Material : dep(
            name = "com.google.android.material:material", version = Versions.material
        )

        object RecyclerView : dep(
            name = "androidx.recyclerview:recyclerview", version = Versions.recyclerView
        )

        object ConstraintLayout : dep(
            name = "androidx.constraintlayout:constraintlayout", version = Versions.constraintLayout
        )

        object KtxCore : dep(name = "androidx.core:core-ktx", version = Versions.ktx)

        object LifecycleCommon : dep(
            name = "androidx.lifecycle:lifecycle-common-java8", version = Versions.lifecycle
        )

        object Fragment : dep(name = "androidx.fragment:fragment", version = Versions.fragment)

        object FragmentKtx : dep(
            name = "androidx.fragment:fragment-ktx", version = Versions.fragment
        )

        object ViewPager : dep(
            name = "androidx.viewpager2:viewpager2", version = Versions.viewPager
        )

        object Insetter : dep(
            name = "dev.chrisbanes.insetter:insetter", version = Versions.insetter
        )
    }

    object Coroutines {

        object Core : dep(
            name = "org.jetbrains.kotlinx:kotlinx-coroutines-core",
            version = Versions.coroutines
        )

        object Android : dep(
            name = "org.jetbrains.kotlinx:kotlinx-coroutines-android",
            version = Versions.coroutines
        )
    }

    object Mvi {

        object MviKotlin : dep(
            name = "com.arkivanov.mvikotlin:mvikotlin",
            version = Versions.mviKotlin
        )

        object MviKotlinMain : dep(
            name = "com.arkivanov.mvikotlin:mvikotlin-main",
            version = Versions.mviKotlin
        )

        object MviKotlinRx : dep(
            name = "com.arkivanov.mvikotlin:rx",
            version = Versions.mviKotlin
        )

        object MviKotlinUtils : dep(
            name = "com.arkivanov.mvikotlin:utils-internal",
            version = Versions.mviKotlin
        )

        object MviKotlinCoroutines : dep(
            name = "com.arkivanov.mvikotlin:mvikotlin-extensions-coroutines",
            version = Versions.mviKotlin
        )
    }

    object Di {

        object Koin : dep(
            name = "io.insert-koin:koin-core",
            version = Versions.koin
        )

        object KoinAndroid : dep(
            name = "io.insert-koin:koin-android",
            version = Versions.koin
        )
    }

    object Db {

        object SqlDelightCommon : dep(
            name = "com.squareup.sqldelight:runtime",
            version = Versions.sqlDelight
        )

        object SqlDelightAndroid : dep(
            name = "com.squareup.sqldelight:android-driver",
            version = Versions.sqlDelight
        )

        object SqlDelightIos : dep(
            name = "com.squareup.sqldelight:native-driver",
            version = Versions.sqlDelight
        )

        object SqlDelightCoroutines : dep(
            name = "com.squareup.sqldelight:coroutines-extensions",
            version = Versions.sqlDelight
        )
    }

    object Ui {

        object AdapterDelegates : dep(
            name = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer",
            version = Versions.adapterDelegates
        )

        object Lottie : dep(
            name = "com.airbnb.android:lottie",
            version = Versions.lottie
        )
    }

    object Utils {

        object Kotlin : dep(name = "org.jetbrains.kotlin:kotlin-stdlib", version = Versions.kotlin)

        object KotlinCommon : dep(
            name = "org.jetbrains.kotlin:kotlin-stdlib-common", version = Versions.kotlin
        )

        object KotlinJdk7 : dep(
            name = "org.jetbrains.kotlin:kotlin-stdlib-jdk7", version = Versions.kotlin
        )
    }

    object Test {

        object Junit : dep(name = "junit:junit", version = Versions.junit)

        object Mockk : dep(name = "io.mockk:mockk", version = Versions.mockk)

        object AssertJ : dep(name = "org.assertj:assertj-core", version = Versions.assertj)

        object KotlinTestCommon : dep(
            name = "org.jetbrains.kotlin:kotlin-test-common", version = Versions.kotlin
        )

        object KotlinJunit : dep(
            name = "org.jetbrains.kotlin:kotlin-test-junit", version = Versions.kotlin
        )

        object KotlinTestAnnotations : dep(
            name = "org.jetbrains.kotlin:kotlin-test-annotations-common", version = Versions.kotlin
        )
    }
}
