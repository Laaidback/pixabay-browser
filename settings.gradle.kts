pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Pixabay Browser"
include(":app")
include(":feature:imageSearch")
include(":core")
include(":feature:imageDetails")
include(":imageDataSource")
