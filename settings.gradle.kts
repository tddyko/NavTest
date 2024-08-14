pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "NavTest"
include(":app")


include(":feature")


include(
    ":core",
    ":core:designsystem",
    ":core:domain",
    ":core:model",
    ":core:ui",
    ":core:navigation"
)


include(
    ":feature:main",
    ":feature:home",
    ":feature:setting"
)