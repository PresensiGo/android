import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    kotlin("plugin.serialization") version "2.2.0"
}

android {
    namespace = "com.rizalanggoro.presensigo"
    compileSdk = 35

    sourceSets {
        getByName("main").kotlin.srcDirs("../openapi/src/main/kotlin")
    }

    defaultConfig {
        applicationId = "com.rizalanggoro.presensigo"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release") {
            storeFile = file("release.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
        }
    }

    buildTypes {
        val properties = Properties()
        properties.load(rootProject.file(".env").inputStream())

        debug {
            buildConfigField("String", "API_BASE_URL", properties.getProperty("API_BASE_URL"))

            applicationIdSuffix = ".debug"
            resValue("string", "app_name", "PresensiGo Debug")
        }

        release {
            buildConfigField("String", "API_BASE_URL", properties.getProperty("API_BASE_URL"))

            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // koin dependency injection
    implementation("io.insert-koin:koin-android:4.0.3")
    implementation("io.insert-koin:koin-androidx-compose:4.0.3")
    implementation("io.insert-koin:koin-androidx-compose-navigation:4.0.3")

    // ktor client
    implementation("io.ktor:ktor-client-core:3.2.2")
    implementation("io.ktor:ktor-client-cio:3.2.2")
    implementation("io.ktor:ktor-client-content-negotiation:3.2.2")
    implementation("io.ktor:ktor-serialization-gson:3.2.2")
    implementation("io.ktor:ktor-client-auth:3.2.2")
    implementation("io.ktor:ktor-client-logging:3.2.2")

    // gson serialization
    implementation("com.google.code.gson:gson:2.13.1")

    // kotlin functional programming
    implementation("io.arrow-kt:arrow-core:2.1.0")

    // lucide icons for jetpack compose
    implementation("com.composables:icons-lucide:1.0.0")
}