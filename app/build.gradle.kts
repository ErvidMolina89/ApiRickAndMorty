plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.wposs.apirickandmorty"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wposs.apirickandmorty"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Libreria de Carrusel
    implementation(libs.whynotimagecarousel)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    //Gson
    implementation(libs.gson)
    //image Slider
    implementation(libs.imageslidershow)
    //Picasso
    implementation(libs.picasso)
    //imagen circular
    implementation(libs.circleimageview)

}