buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }

    dependencies {
        classpath(Dependencies.Plugins.Kotlin)
        classpath(Dependencies.Plugins.Android)
        classpath(Dependencies.Plugins.SqlDelight)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}
