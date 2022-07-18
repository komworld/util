import java.io.FileFilter

plugins {
    kotlin("jvm") version "1.7.0"
    id("org.jetbrains.dokka") version "1.6.21" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.johnrengelman.shadow")

    repositories {
        maven(Repositories.Paper)
    }

    @Suppress("GradlePackageUpdate")
    dependencies {
        compileOnly(Dependencies.Paper.API)
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
//        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:${project.properties["coroutinesVersion"]}")

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
    }
}

fun Project.preparePublish() {
    apply(plugin = "org.jetbrains.dokka")

    tasks {
        create<Jar>("sourcesJar") {
            archiveClassifier.set("sources")
            from(sourceSets["main"].allSource)
        }

        create<Jar>("dokkaJar") {
            archiveClassifier.set("javadoc")
            dependsOn("dokkaHtml")

            from("$buildDir/dokka/html/") {
                include("**")
            }
        }
    }
}

api.preparePublish()
core.preparePublish()

tasks {
    register<DefaultTask>("setupModules") {
        doLast {
            var previousPrefix = ""
            val currentPrefix = project.name
            val formattedDirectories = rootDir.listFiles(FileFilter { it.isDirectory && it.name != ".idea" && it.name != ".git" && it.name != ".gradle" && it.name != "gradle" && it.name != "buildSrc" && it.name != "out" })
                ?.toMutableList()

            val prefixIsDifferent = formattedDirectories?.any { project ->
                val nameMutable = project.name.split('-').toMutableList()
                nameMutable.removeLast()
                val projectName = nameMutable.joinToString("-")
                previousPrefix = projectName
                projectName != rootProject.name
            }

            if (prefixIsDifferent == true) {
                fun rename(suffix: String) {
                    val from = "$previousPrefix-$suffix"
                    val to = "$currentPrefix-$suffix"
                    file(from).takeIf { it.exists() }?.renameTo(file(to))
                }

                rename("api")
                rename("core")
                rename("dongle")
                rename("paper")
                rename("bungee")
                rename("publish")
            }
        }
    }
}