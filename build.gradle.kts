import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("io.ktor:ktor-client-logging-jvm:2.3.2")
            }
        }
        val commonMain by getting {
            dependencies {
                val ktorVersion = "2.3.2"
                implementation("io.ktor:ktor-client-java:$ktorVersion")
                implementation("io.insert-koin:koin-core:3.4.2")
                implementation("io.ktor:ktor-client-json:1.6.3")
                implementation("io.ktor:ktor-client-logging:1.6.3")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.2")
                implementation("io.ktor:ktor-serialization-gson:2.3.2")
                implementation("io.ktor:ktor-client-serialization:1.6.3")
                implementation("com.google.code.gson:gson:2.10.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Counter"
            packageVersion = "1.0.0"
        }
    }
}
