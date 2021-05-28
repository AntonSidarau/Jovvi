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

                addProject(":business-topics")

                implementation(Dependencies.Di.Koin)
                implementation(Dependencies.Coroutines.Core)
            }
        }
    }
}
