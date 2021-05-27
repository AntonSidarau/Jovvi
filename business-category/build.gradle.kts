plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

multiPlatformLibrary()

kotlinMultiPlatform {
    sourceSets {
        commonMain {
            dependencies {
                addProject(":common-mpp")
                addProject(":common-db")

                implementation(Dependencies.Di.Koin)
                implementation(Dependencies.Coroutines.Core)
            }
        }
    }
}
