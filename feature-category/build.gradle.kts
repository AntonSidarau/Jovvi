androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    addProject(":business-category")

    androidUi()
    kodein()

    implementation(Dependencies.Ui.AdapterDelegates)
}
