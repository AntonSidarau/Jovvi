plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

multiPlatformLibrary()

kotlinMultiPlatform {
    sourceSets {
        commonMain {
            dependencies {
                addProject(":common-db")
                addProject(":common-mpp")

                addProject(":business-category")

                implementation(Dependencies.Di.Koin)
                implementation(Dependencies.Coroutines.Core)
            }
        }
    }
}
