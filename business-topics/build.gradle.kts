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
            }
        }
    }
}
