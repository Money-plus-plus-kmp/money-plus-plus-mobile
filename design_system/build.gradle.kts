import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    jvm()
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val iosX64 = iosX64()
    val iosArm64 = iosArm64()
    val iosSimulatorArm64 = iosSimulatorArm64()

    listOf(iosX64, iosArm64, iosSimulatorArm64).forEach { target: KotlinNativeTarget ->
        target.binaries.framework {
            baseName = "DesignSystem"
            isStatic = true
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                // Compose
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.uiTooling)
                implementation(compose.material3)
            }
        }

        commonTest {
            dependencies {

            }
        }

        androidMain {
            dependencies {

            }
        }


        iosMain {
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.moneyplusplus.design_system"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}