/*
 * Copyright (c) 2022 Komworld Dev Team
 *
 * Licensed under the General Public License, Version 3.0. (https://opensource.org/licenses/gpl-3.0/)
 */

plugins {
    `maven-publish`
    signing
}

val githubUser = "komworld"

publishing {
    repositories {
        mavenLocal()

        maven {
            name = "debug"
            url = rootProject.uri(".server/libraries")
        }

        maven {
            name = "komworld"

            credentials.runCatching {
                username = "admin"
                password = "bini0824!!"
            }.onFailure {
                logger.warn("Failed to authenticate nexus credentials")
            }

            url = uri(
                if ("SNAPSHOT" in version as String) {
                    "https://repo.komq.world/repository/komworld-snapshots"
                } else {
                    "https://repo.komq.world/repository/komworld-releases"
                }
            )
        }
    }

    publications {
        fun MavenPublication.setup(target: Project) {
            from(target.components["java"])
            artifact(target.tasks["sourcesJar"])
            artifact(target.tasks["dokkaJar"])

            pom {
                name.set(target.name)
                url.set("https://github.com/${githubUser}/${rootProject.name}")

                licenses {
                    license {
                        name.set("GNU General Public License version 3")
                        url.set("https://opensource.org/licenses/GPL-3.0")
                    }
                }

                developers {
                    developer {
                        id.set("qogusdn1017")
                        name.set("Bae Hyeon Woo")
                        email.set("qogusdn1017@naver.com")
                        url.set("https://github.com/qogusdn1017")
                        roles.addAll("developer")
                        timezone.set("Asia/Seoul")
                    }
                    developer {
                        id.set("dytroInc")
                        name.set("DytroDev")
                        email.set("dytrocodeworkshop@gmail.com")
                        url.set("https://github.com/dytroInc")
                        roles.addAll("developer")
                        timezone.set("Asia/Seoul")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/${githubUser}/${rootProject.name}.git")
                    developerConnection.set("scm:git:ssh://github.com:${githubUser}/${rootProject.name}.git")
                    url.set("https://github.com/${githubUser}/${rootProject.name}")
                }
            }
        }

        create<MavenPublication>("api") {
            val api = api
            artifactId = api.name
            setup(api)
        }

        create<MavenPublication>("core") {
            val core = core

            artifactId = core.name
            setup(core)

            core.tasks.jar { archiveClassifier.set("origin") }
        }
    }
}

signing {
    isRequired = true
    sign(publishing.publications)
}