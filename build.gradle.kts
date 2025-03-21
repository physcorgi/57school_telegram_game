import org.jetbrains.kotlin.gradle.dsl.JvmTarget

val jvmTargetVersion = JavaVersion.VERSION_17

plugins {
    application
    alias(libs.plugins.kotlin.jvm)
    kotlin("plugin.spring") version "1.9.22"
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("plugin.jpa") version "1.9.22"
    jacoco
}

group = "com.example.blank"
version = "0.0.1"
application {
    mainClass.set("com.example.blank.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("ch.qos.logback:logback-classic")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.postgresql:postgresql")
    
    // Telegram Bot dependencies
    implementation("dev.inmo:tgbotapi:9.0.0")
    implementation("dev.inmo:tgbotapi-kotlinx-serialization:9.0.0")
    implementation("dev.inmo:tgbotapi-extensions-api:9.0.0")
    implementation("dev.inmo:tgbotapi-extensions-utils:9.0.0")
    implementation("dev.inmo:tgbotapi-extensions-common:9.0.0")
    implementation("dev.inmo:tgbotapi-extensions-behaviour-builder:9.0.0")
    implementation("dev.inmo:tgbotapi-extensions-utils-ktor:9.0.0")
    implementation("dev.inmo:tgbotapi-extensions-utils-coroutines:9.0.0")
    
    // Test dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.mockito:mockito-core:5.2.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation("ch.qos.logback:logback-classic")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = jvmTargetVersion.toString()
    }
}

kotlin {
    jvmToolchain(jvmTargetVersion.majorVersion.toInt())
}
