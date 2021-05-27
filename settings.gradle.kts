rootProject.name = "Jovvi"

include(
    "app",

    ":common",
    ":common-db",
    ":common-di",
    ":common-mpp",
    ":common-navigation",
    ":common-ui",

    ":business-category",
    ":business-topics",

    ":feature-about-us",
    ":feature-category",
    ":feature-hot-question",
    ":feature-question",
    ":feature-topics"
)