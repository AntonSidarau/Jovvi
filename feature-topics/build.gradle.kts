androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    addProject(":business-category")
    addProject(":business-topics")

    androidUi()
    kodein()

    implementation(Dependencies.Ui.AdapterDelegates)
}
