plugins {
    id("com.android.library")
    kotlin("multiplatform")
}

multiPlatformLibrary()

androidLibrary {
    dependencies {
        implementation(Dependencies.AndroidX.Annotation)
    }
}
