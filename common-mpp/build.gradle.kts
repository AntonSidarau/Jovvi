plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

multiPlatformLibrary()

androidLibrary {
    dependencies {
        androidX()
        implementation(Dependencies.AndroidX.Annotation)
    }
}
