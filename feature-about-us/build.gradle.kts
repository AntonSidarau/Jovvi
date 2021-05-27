androidLibrary()

dependencies {
    addProject(":common-navigation")
    addProject(":common-di")
    addProject(":common-ui")

    androidUi()

    koin()
}
