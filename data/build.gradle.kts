import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
}

val localProperties = Properties().apply {
    val localFile = project.rootProject.file("local.properties")
    if (localFile.exists()) {
        load(localFile.inputStream())
    }
}


kotlin {
    androidTarget()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)

        }
        commonMain.dependencies {
            implementation(projects.domain)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.datetime)
        }
        commonTest.dependencies {

        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.moneyplusplus.data"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${localProperties.getProperty("BASE_URL")}\""
            )
        }
        release {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${localProperties.getProperty("BASE_URL")}\""
            )
        }
    }

}