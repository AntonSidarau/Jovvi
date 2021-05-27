androidLibrary()

dependencies {
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-di")
    addProject(":common-ui")

    androidUi()
    koin()
}
