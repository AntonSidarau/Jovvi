private typealias dep = LibraryDependency

object Dependencies {

    object Plugins {

        object Android : dep(
            name = "com.android.tools.build:gradle", version = Versions.androidGradlePlugin
        )

        object Kotlin : dep(
            name = "org.jetbrains.kotlin:kotlin-gradle-plugin", version = Versions.kotlin
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
    }

    object Ui {

        object AdapterDelegates : dep(
            name = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-layoutcontainer",
            version = Versions.adapterDelegates
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
