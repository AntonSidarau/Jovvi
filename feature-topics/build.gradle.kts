androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    addProject(":business-category")
    addProject(":business-topics")

    androidX()
    androidUi()

    implementation(Dependencies.Ui.AdapterDelegates)
}
