plugins {
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    jvm()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.koin.core)
        }
        commonTest.dependencies {
                    // Core multiplatform test
                    implementation(kotlin("test"))
                    implementation(kotlin("test-annotations-common"))

                    // Coroutines test helpers
                    implementation(libs.kotlinx.coroutines.test)

                    // Mocking for KMP (requires plugin)
                    implementation(libs.mokkery.core)
                }
            }
}