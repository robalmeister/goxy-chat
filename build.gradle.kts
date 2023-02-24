plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "pl.robalmeister"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
    maven { url = uri("https://repo.goxy.pl") }
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    sourceCompatibility = "1.8"
    targetCompatibility = "1.8"
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}
dependencies {
    compileOnly("org.github.paperspigot:paperspigot-api:1.8.8-R0.1-SNAPSHOT")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("pl.goxy.minecraft:goxy-pubsub-api:0.1.0")
    compileOnly("pl.goxy.minecraft:goxy-api:0.8.0")
    // https://mvnrepository.com/artifact/net.kyori/adventure-platform-bukkit
    implementation("net.kyori:adventure-platform-bukkit:4.2.0")

}
