package com.example.navtest

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            val libs = extensions.libs
            dependencies {
                "implementation"(libs.findLibrary("hilt.core").get())
                "ksp"(libs.findLibrary("hilt.compiler").get())
            }
        }
    }
}