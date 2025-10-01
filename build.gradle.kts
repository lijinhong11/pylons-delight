import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    java
    idea
    id("com.gradleup.shadow") version "9.0.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("io.freefair.lombok") version "8.13.1"
}

group = project.properties["group"]!!

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases")
}

val coreVersion = project.properties["pylon-core.version"] as String
val baseVersion = project.properties["pylon-base.version"] as String

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.9-R0.1-SNAPSHOT")
    compileOnly("io.github.pylonmc:pylon-core:$coreVersion")
    compileOnly("io.github.pylonmc:pylon-base:$baseVersion")

    implementation("com.jeff-media:MorePersistentDataTypes:2.4.0")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
}

tasks.shadowJar {
    mergeServiceFiles()

    exclude("com/jeff_media/morepersistentdatatypes/DataType_1_18_1.class")
    relocate("com.jeff_media.morepersistentdatatypes", "io.github.lijinhong11.pylonsdelight.libraries.datatypes")

    archiveBaseName = project.name
    archiveClassifier = null
}

bukkit {
    name = project.properties["name"] as String
    main = project.properties["main-class"] as String
    version = project.version.toString()
    apiVersion = "1.21"
    depend = listOf("PylonCore")
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
}

tasks.runServer {
    downloadPlugins {
        github("pylonmc", "pylon-core", coreVersion, "pylon-core-$coreVersion.jar")
    }
    downloadPlugins {
        github("pylonmc", "pylon-base", coreVersion, "pylon-base-$coreVersion.jar")
    }
    maxHeapSize = "4G"
    minecraftVersion("1.21.9")
}