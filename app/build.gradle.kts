plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    setUpAndroidSdkVersions()
    setUpAndroidConfiguration(project)

    defaultConfig {
        applicationId = "com.jovvi.mobile"
        versionCode = 1
        versionName = "1.0"
    }

    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
    }

    signingConfigs {
        val release by creating {

        }
    }

    buildTypes {
        val debug by getting {
            signingConfig = signingConfigs.getByName("debug")
        }

        val release by getting {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    addProject(":common")
    addProject(":common-mpp")
    addProject(":common-navigation")
    addProject(":common-ui")

    addProject(":business-category")
    addProject(":business-topics")

    addProject(":feature-category")
    addProject(":feature-topics")

    androidX()

    tests()
}

