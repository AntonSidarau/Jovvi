enableFeaturePreview("GRADLE_METADATA")

rootProject.name = "Jovvi"

include(
    "app",

    ":common-ui",
    ":common-navigation"
)
