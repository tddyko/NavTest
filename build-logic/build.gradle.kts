plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.ksp.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidCompose") {
            id = "navtest.android.compose"
            implementationClass = "com.example.navtest.ComposeAndroidPlugin"
        }

        register("androidHilt") {
            id = "navtest.android.hilt"
            implementationClass = "com.example.navtest.HiltAndroidPlugin"
        }

        register("kotlinHilt") {
            id = "navtest.kotlin.hilt"
            implementationClass = "com.example.navtest.HiltKotlinPlugin"
        }

        register("kotlinAndroid") {
            id = "navtest.kotlin.android"
            implementationClass = "com.example.navtest.KotlinAndroidPlugin"
        }
    }
}