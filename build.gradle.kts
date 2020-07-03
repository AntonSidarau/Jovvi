buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Dependencies.Plugins.Kotlin)
        classpath(Dependencies.Plugins.Android)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
