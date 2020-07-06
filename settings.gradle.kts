enableFeaturePreview("GRADLE_METADATA")

rootProject.name = "Jovvi"

include(
    "app",

    ":common",
    ":common-mpp",
    ":common-navigation",
    ":common-ui",

    ":business-category",
    ":business-topics",

    ":feature-category",
    ":feature-question",
    ":feature-topics"
)
