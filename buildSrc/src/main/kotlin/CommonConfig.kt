import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

const val MID_SDK = 21
const val TARGET_SDK = 31
const val COMPILE_SDK = 31

fun Project.androidLibrary() {
    plugins.apply("com.android.library")
    plugins.apply("kotlin-android")

    androidLibrary {
        setUpAndroidSdkVersions()
        setUpAndroidConfiguration(this@androidLibrary)

        sourceSets {
            getByName("main").java.srcDirs("src/main/kotlin")
        }
    }
}

inline fun Project.androidDependencies(
    crossinline block: DependencyHandlerScope.() -> Unit
) {
    androidLibrary {
        dependencies {
            block()
        }
    }
}

fun BaseExtension.setUpAndroidSdkVersions() {
    compileSdkVersion(COMPILE_SDK)

    defaultConfig {
        targetSdk = TARGET_SDK
        minSdk = MID_SDK
    }
}

fun BaseExtension.setUpAndroidConfiguration(project: Project) {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lintOptions {
        disable("ContentDescription")
    }

    project.tasks.withType<KotlinCompile> {
        val version = "1.8"
        sourceCompatibility = version
        targetCompatibility = version

        kotlinOptions {
            jvmTarget = version
            noJdk = true
        }
    }

    project.dependencies {
        implementation(Dependencies.Utils.Kotlin)
    }
}

fun Project.androidLibrary(block: BaseExtension.() -> Unit) {
    extensions.getByType(BaseExtension::class.java).block()
}
