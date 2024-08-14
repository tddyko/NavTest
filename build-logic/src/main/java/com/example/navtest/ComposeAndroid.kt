package com.example.navtest

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal class ComposeAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(plugins) { apply("org.jetbrains.kotlin.plugin.compose") }

            val libs = extensions.libs
            androidExtension.apply {
                dependencies {
                    val bom = libs.findLibrary("androidx-compose-bom").get()
                    add("implementation", platform(bom))
                    add("androidTestImplementation", platform(bom))

                    add("implementation", libs.findLibrary("androidx-compose-ui").get())
                    add("implementation", libs.findLibrary("androidx-compose-ui-tooling").get())

                    add("androidTestImplementation", libs.findLibrary("androidx-junit").get())
                    add(
                        "androidTestImplementation",
                        libs.findLibrary("androidx-espresso-core").get()
                    )
                    add(
                        "androidTestImplementation",
                        libs.findLibrary("androidx-compose-ui-test").get()
                    )

                    add(
                        "debugImplementation",
                        libs.findLibrary("androidx-compose-ui-tooling").get()
                    )
                    add(
                        "debugImplementation",
                        libs.findLibrary("androidx-compose-ui-test-manifest").get()
                    )
                }
            }

            extensions.getByType<ComposeCompilerGradlePluginExtension>().apply {
                enableStrongSkippingMode.set(true)
                includeSourceInformation.set(true)
            }
        }
    }
}