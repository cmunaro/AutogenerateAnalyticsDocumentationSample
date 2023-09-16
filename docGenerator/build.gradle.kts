plugins {
    id("org.jetbrains.kotlin.jvm")
    application
}

application {
    mainClass.set("cmunaro.github.analyticsdocumantationsample.doc.DocKt")
}

dependencies {
    implementation(project(":analytics"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.html.jvm)
}

repositories {
    mavenCentral()
}
