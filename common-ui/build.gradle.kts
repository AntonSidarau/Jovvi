androidLibrary()

dependencies {

    addProject(":common-navigation") //TODO remove

    androidX()
    androidUi()

    implementation(Dependencies.Ui.AdapterDelegates)
}
