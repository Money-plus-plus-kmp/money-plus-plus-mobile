import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            dependencies {
                // Compose
                implementation(compose.runtime)
                implementation(projects.designSystem)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(projects.designSystem)
                implementation(projects.domain)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
                implementation(libs.kotlinx.coroutines.core)

                // koin
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.compose.viewmodel)
                api(libs.koin.annotations)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        androidMain {
            dependencies {
                implementation(libs.androidx.ui.tooling)
            }
        }
        iosMain {
            dependencies {
            }
        }

        sourceSets.named("commonMain").configure {
            kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
        }
    }
}

ksp {
    arg("KOIN_USE_COMPOSE_VIEWMODEL", "true")
    arg("KOIN_CONFIG_CHECK", "true")
}

dependencies {
    // Debug
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Koin KSP
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

android {
    namespace = "com.moneyplusplus.presentation"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}