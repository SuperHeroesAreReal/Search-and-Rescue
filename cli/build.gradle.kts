plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(project(":core"))
}

application {
    applicationName = "ohbuoy"
    mainClass = "io.nodaiity.ohbuoy.cli.OhBuoyCliKt"
    applicationDefaultJvmArgs = listOf("-Dfile.encoding=UTF-8")
}

distributions {
    main {
        contents {
            from(rootProject.file("README.md")) {
                into("")
            }
        }
    }
}
