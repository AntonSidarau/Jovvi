androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    androidUi()
    kodein()
}
