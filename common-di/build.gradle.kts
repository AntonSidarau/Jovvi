plugins {
    kotlin("multiplatform")
}

multiPlatformLibrary()

androidDependencies {
    androidX()
    koin()
}


