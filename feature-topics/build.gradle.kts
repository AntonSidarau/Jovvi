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
                addProject(":business-category")
                addProject(":business-topics")

                implementation(Dependencies.Di.Koin)
                implementation(Dependencies.Coroutines.Core)
            }
        }
    }
}

androidDependencies {
    addProject(":common-navigation")
    addProject(":common-ui")
    addProject(":common-di")

    androidUi()
    koin()

    implementation(Dependencies.Ui.AdapterDelegates)
}
