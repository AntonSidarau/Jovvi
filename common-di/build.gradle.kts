plugins {
    kotlin("multiplatform")
}

multiPlatformLibrary()
androidLibraryMultiplatform()

androidDependencies {
    androidX()
}


