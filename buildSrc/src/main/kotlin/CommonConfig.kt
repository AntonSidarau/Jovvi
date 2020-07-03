import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

const val MID_SDK = 21
const val TARGET_SDK = 29
const val COMPILE_SDK = 29

fun Project.androidLibrary() {
    androidLibrary {
        setUpAndroidSdkVersions()
        setUpAndroidConfiguration(this@androidLibrary)
    }
}

fun BaseExtension.setUpAndroidSdkVersions() {
    compileSdkVersion(COMPILE_SDK)

    defaultConfig {
        targetSdkVersion(TARGET_SDK)
        minSdkVersion(MID_SDK)
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
