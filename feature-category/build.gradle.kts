androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    addProject(":business-category")

    androidX()
    androidUi()

    implementation(Dependencies.Ui.AdapterDelegates)
}
