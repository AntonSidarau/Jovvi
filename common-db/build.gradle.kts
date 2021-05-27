plugins {
    kotlin("multiplatform")
    id("com.squareup.sqldelight")
}

sqldelight {
    database("JovviDatabase") {
        packageName = "com.jovvi.mobile.common_db"
    }
}

multiPlatformLibrary()

kotlinMultiPlatform {
    sourceSets {
        commonMain {
            dependencies {
                implementation(Dependencies.Db.SqlDelightCommon)
                implementation(Dependencies.Db.SqlDelightCoroutines)
                implementation(Dependencies.Di.Koin)
                implementation(Dependencies.Coroutines.Core)
            }
        }

        androidMain {
            dependencies {
                implementation(Dependencies.Db.SqlDelightAndroid)
            }
        }

        iosMain {
            dependencies {
                implementation(Dependencies.Db.SqlDelightIos)
            }
        }
    }
}
