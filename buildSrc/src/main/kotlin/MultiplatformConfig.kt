import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.multiPlatformLibrary() {
    plugins.apply("kotlin-multiplatform")
    plugins.apply("com.android.library")

    androidLibrary {
        setUpAndroidSdkVersions()
    }

    kotlinMultiPlatform {
        android {
            publishLibraryVariants("release", "debug")
        }

        val ideaActive = System.getProperty("idea.active") == "true"
        iosX64(if (ideaActive) "ios" else "iosX64")
        iosArm64()

        sourceSets {
            commonMain {
                dependencies {
                    implementation(Dependencies.Utils.KotlinCommon)
                }
            }

            commonTest {
                dependencies {
                    implementation(Dependencies.Test.KotlinTestCommon)
                    implementation(Dependencies.Test.KotlinTestAnnotations)
                }
            }

            androidMain {
                dependsOn(commonMain)

                dependencies {
                    implementation(Dependencies.Utils.KotlinJdk7)
                }
            }

            androidTest {
                dependsOn(commonTest)

                dependencies {
                    implementation(Dependencies.Test.KotlinJunit)
                    tests()
                }
            }

            iosMain.dependsOn(commonMain)
            iosArm64Main.dependsOn(iosMain)
        }
    }

}

fun Project.kotlinMultiPlatform(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.getByType<KotlinMultiplatformExtension>().block()
}

typealias SourceSets = NamedDomainObjectContainer<KotlinSourceSet>

fun KotlinMultiplatformExtension.sourceSets(block: SourceSets.() -> Unit) {
    sourceSets.block()
}

private fun SourceSets.getOrCreate(name: String): KotlinSourceSet = findByName(name) ?: create(name)

private val SourceSets.commonMain: KotlinSourceSet get() = getOrCreate("commonMain")

fun SourceSets.commonMain(block: KotlinSourceSet.() -> Unit) {
    commonMain.apply(block)
}

private val SourceSets.commonTest: KotlinSourceSet get() = getOrCreate("commonTest")

fun SourceSets.commonTest(block: KotlinSourceSet.() -> Unit) {
    commonTest.apply(block)
}

private val SourceSets.androidMain: KotlinSourceSet get() = getOrCreate("androidMain")

fun SourceSets.androidMain(block: KotlinSourceSet.() -> Unit) {
    androidMain.apply(block)
}

private val SourceSets.androidTest: KotlinSourceSet get() = getOrCreate("androidTest")

fun SourceSets.androidTest(block: KotlinSourceSet.() -> Unit) {
    androidTest.apply(block)
}

private val SourceSets.iosMain: KotlinSourceSet get() = getOrCreate("iosMain")

private val SourceSets.iosArm64Main: KotlinSourceSet get() = getOrCreate("iosArm64Main")
