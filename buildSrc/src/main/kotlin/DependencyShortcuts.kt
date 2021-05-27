import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

private const val IMPLEMENTATION = "implementation"
private const val TEST_IMPLEMENTATION = "testImplementation"

fun DependencyHandlerScope.implementation(dependency: LibraryDependency) {
    add(IMPLEMENTATION, dependency.toString())
}

fun DependencyHandler.addProject(name: String) {
    add(IMPLEMENTATION, project(name))
}

fun KotlinDependencyHandler.addProject(name: String) {
    implementation(project(name))
}

fun DependencyHandler.androidX() {
    add(IMPLEMENTATION, Dependencies.AndroidX.AppCompat)
    add(IMPLEMENTATION, Dependencies.AndroidX.Material)
    add(IMPLEMENTATION, Dependencies.AndroidX.KtxCore)
    add(IMPLEMENTATION, Dependencies.AndroidX.LifecycleCommon)
    add(IMPLEMENTATION, Dependencies.AndroidX.Fragment)
    add(IMPLEMENTATION, Dependencies.AndroidX.FragmentKtx)
}

fun DependencyHandler.androidUi() {
    androidX()
    add(IMPLEMENTATION, Dependencies.AndroidX.RecyclerView)
    add(IMPLEMENTATION, Dependencies.AndroidX.ConstraintLayout)
}

fun DependencyHandler.koin() {
    add(IMPLEMENTATION, Dependencies.Di.Koin)
    add(IMPLEMENTATION, Dependencies.Di.KoinAndroid)
}

fun KotlinDependencyHandler.mviKotlin() {
    implementation(Dependencies.Mvi.MviKotlin)
    implementation(Dependencies.Mvi.MviKotlinMain)
    implementation(Dependencies.Mvi.MviKotlinRx)
    implementation(Dependencies.Mvi.MviKotlinUtils)
    implementation(Dependencies.Mvi.MviKotlinCoroutines)
}

fun DependencyHandler.tests() {
    add(TEST_IMPLEMENTATION, Dependencies.Test.Mockk)
    add(TEST_IMPLEMENTATION, Dependencies.Test.AssertJ)
    add(TEST_IMPLEMENTATION, Dependencies.Test.Junit)
}

fun KotlinDependencyHandler.tests() {
    implementation(Dependencies.Test.Mockk)
    implementation(Dependencies.Test.AssertJ)
    implementation(Dependencies.Test.Junit)
}
