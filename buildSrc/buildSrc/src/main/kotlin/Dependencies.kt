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
