androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    addProject(":business-topics")

    androidUi()
    kodein()

    implementation(Dependencies.AndroidX.ViewPager)

    implementation(Dependencies.Ui.AdapterDelegates)
}
