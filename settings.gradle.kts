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

rootProject.name = "lesson4"
include(":app")
include(":binding")
include(":musicplayer")
include(":thread")
include(":data_thread")
include(":looper")
include(":cryptoloader")
include(":serviceapp")
include(":workmanager")
