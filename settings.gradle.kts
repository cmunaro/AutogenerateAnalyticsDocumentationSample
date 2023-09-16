pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AnalyticsDocumantationSample"
include(":androidApp")
include(":shared")
include(":analytics")
include(":docGenerator")
