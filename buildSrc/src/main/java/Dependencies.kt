@file:Suppress("MayBeConstant")

object AppVersion {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val compileSdk = 30
    val minSdk = 23
    val targetSdk = 30

    val kotlin = "1.5.0"
    val agp = "4.2.1"

    val dagger = "2.35.1"
    val hilt = "2.35"
    val androidXLifecycleVersion = "2.4.0-alpha01"
    val androidxFragmentVersion = "1.3.3"
    val androidXRoomVersion = "2.3.0"
    val autoDisposeVersion = "1.0.0"
    val moshiVersion = "1.12.0"
    val okhttpVersion = "4.9.0"
    val retrofit = "2.9.0"
    val retrofitCoroutines = "0.9.2"
    val nav = "2.3.5"
    val page = "3.0.0"
}

object Kotlin {
    val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
}

object AndroidX {
    val appCompat = "androidx.appcompat:appcompat:1.3.0-rc01"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    val coreKtx = "androidx.core:core-ktx:1.5.0-rc02"
    val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidxFragmentVersion}"
    val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"
    val lifecycleVieModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.androidXLifecycleVersion}"
    val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidXLifecycleVersion}"
    val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidXLifecycleVersion}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.androidXLifecycleVersion}"
    val lifecycleVieModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidXLifecycleVersion}"
    val roomCompiler = "androidx.room:room-compiler:${Versions.androidXRoomVersion}"
    val roomKtx = "androidx.room:room-ktx:${Versions.androidXRoomVersion}"
    val roomRuntime = "androidx.room:room-runtime:${Versions.androidXRoomVersion}"
    val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav}"
    val navigationUIKtx = "androidx.navigation:navigation-ui-ktx:${Versions.nav}"
    val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    val pagination = "androidx.paging:paging-runtime-ktx:${Versions.page}"
}
object Coil {
    val coil = "io.coil-kt:coil:1.2.1"
}

object Google {
    val material = "com.google.android.material:material:1.3.0"
}

object DI {
    val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val daggerAndroidAnnotationProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val daggerAnnotationProcessor = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    val daggerHilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    val daggerHiltAnnotationProcessor = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    val daggerHiltGradlePluginClasspath = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    val daggerHiltGradlePlugin = "dagger.hilt.android.plugin"
    val daggerHiltTesting = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
    val javaxInject = "javax.inject:javax.inject:1"
}

object AutoDispose {
    val base = "com.uber.autodispose:autodispose-ktx:${Versions.autoDisposeVersion}"
    val androidKtx = "com.uber.autodispose:autodispose-android-ktx:${Versions.autoDisposeVersion}"
    val lifecycleKtx = "com.uber.autodispose:autodispose-lifecycle-ktx:${Versions.autoDisposeVersion}"
    val arch = "com.uber.autodispose:autodispose-android-archcomponents:${Versions.autoDisposeVersion}"
    val archKtx = "com.uber.autodispose:autodispose-android-archcomponents-ktx:${Versions.autoDisposeVersion}"
    val lifecycleTest =
        "com.uber.autodispose:autodispose-android-archcomponents-test-ktx:${Versions.autoDisposeVersion}"
}

object Moshi {
    val core = "com.squareup.moshi:moshi:${Versions.moshiVersion}"
    val codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshiVersion}"
}

object LoggerFramework {
    val timber = "com.jakewharton.timber:timber:4.7.1"
}

object Network {
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
}

object Rx {
    val kotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    val java = "io.reactivex.rxjava2:rxjava:2.2.19"
    val android = "io.reactivex.rxjava2:rxandroid:2.0.1"
}

object Retrofit {
    val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val retrofitCoroutineAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutines}"
}

object TestDependencies {
    val junit = "junit:junit:4.13.2"
    val junitExt = "androidx.test.ext:junit:1.1.2"
    val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
}
