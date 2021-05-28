plugins {
    kotlin("multiplatform")
}

multiPlatformLibrary()

kotlinMultiPlatform {
    sourceSets {
        commonMain {
            dependencies {
                mviKotlin()
                addProject(":common-mpp")
                addProject(":business-topics")
                addProject(":business-question")

                implementation(Dependencies.Di.Koin)
                implementation(Dependencies.Coroutines.Core)
            }
        }
    }
}

androidDependencies {
    addProject(":common-navigation")
    addProject(":common-di")
    addProject(":common-ui")

    androidUi()
    koin()

    implementation(Dependencies.AndroidX.ViewPager)

    implementation(Dependencies.Ui.AdapterDelegates)
}
