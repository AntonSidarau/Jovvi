import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler

private const val IMPLEMENTATION = "implementation"
private const val TEST_IMPLEMENTATION = "testImplementation"

fun DependencyHandlerScope.implementation(dependency: LibraryDependency) {
    add(IMPLEMENTATION, dependency.toString())
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
