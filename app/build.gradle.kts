plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.randomnumbergenerator"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.randomnumbergenerator"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // THIS IS WHERE YOU ADD THE sourceSets BLOCK
    sourceSets {
        getByName("main") {
            assets.srcDirs("src/main/assets", "data")
            // You can also write it as:
            // assets.srcDirs += listOf("data")
            // if you want to ensure the default "src/main/assets" is also included
            // and just add "data" to the existing list.
            // The first option (assets.srcDirs("src/main/assets", "data")) explicitly
            // lists all asset directories.
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
