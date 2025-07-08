import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.google.ksp)
	alias(libs.plugins.kotlin.safeargs)
}

android {
	namespace = "com.github.abusaeed_shuvo.cryptocurrencyapp"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.github.abusaeed_shuvo.cryptocurrencyapp"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}
	buildFeatures {
		viewBinding = true
	}
	buildToolsVersion = "36.0.0"
	ndkVersion = "29.0.13599879 rc2"
}
kotlin {
	compilerOptions {
		jvmTarget.set(JvmTarget.JVM_21)
	}
}

dependencies {

	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)
	implementation(libs.androidx.activity)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.androidx.navigation.ui.ktx)
	implementation(libs.androidx.navigation.fragment.ktx)
	implementation(libs.androidx.fragment)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	implementation(libs.androidx.navigation.dynamic.features.fragment)


	implementation(libs.retrofit)
	implementation(libs.retrofit.converter.gson)

	implementation(libs.okhttp)
	implementation(libs.okhttp.logging.interceptor)

	implementation(libs.kotlinx.coroutines.core)
	implementation(libs.kotlinx.coroutines.android)

	implementation(libs.lifecycle.viewmodel.ktx)
	implementation(libs.lifecycle.runtime.ktx)
}