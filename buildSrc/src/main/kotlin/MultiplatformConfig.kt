import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun Project.multiPlatformLibrary() {
    plugins.apply("com.android.library")

    androidLibrary {
        setUpAndroidSdkVersions()
        setUpAndroidConfiguration(this@multiPlatformLibrary)

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
        }
    }

    kotlinMultiPlatform {
        android()

        // Block from https://github.com/cashapp/sqldelight/issues/2044#issuecomment-721299517.
        val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
        if (onPhone) {
            iosArm64("ios")
        } else {
            iosX64("ios")
        }

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

fun SourceSets.iosMain(block: KotlinSourceSet.() -> Unit) {
    iosMain.apply(block)
}
