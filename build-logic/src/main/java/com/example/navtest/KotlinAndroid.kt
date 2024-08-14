package com.example.navtest

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class KotlinAndroidPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.android")

            androidExtension.apply {
                compileSdk = 34

                defaultConfig {
                    minSdk = 21
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                    isCoreLibraryDesugaringEnabled = true
                }

                buildFeatures {
                    buildConfig = true
                    compose = true
                }

                buildTypes {
                    getByName("release") {
                        isShrinkResources = true
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android.txt"),
                            "proguard-rules.pro"
                        )
                    }

                    getByName("debug") {
                        isShrinkResources = false
                        isMinifyEnabled = false
                    }
                }

                tasks.withType<KotlinCompile>().configureEach {
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                        val warningsAsErrors: String? by project
                        allWarningsAsErrors.set(warningsAsErrors.toBoolean())
                        freeCompilerArgs.addAll(
                            "-opt-in=kotlin.RequiresOptIn",
                            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                            "-opt-in=kotlinx.coroutines.FlowPreview",
                        )
                    }
                }

            }
            val libs = extensions.libs
            dependencies {
                add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
            }
        }
    }
}