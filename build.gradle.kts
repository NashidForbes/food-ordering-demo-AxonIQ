/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    java
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    implementation("org.axonframework:axon-spring-boot-starter:4.7.0")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:4.7.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator:3.1.0")
    testImplementation("junit:junit:4.13.1")
    runtimeOnly("org.hsqldb:hsqldb:2.4.1")
    implementation("com.h2database:h2")
    testImplementation("org.axonframework:axon-test:4.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
}

group = "io.axoniq.food-ordering"
version = "0.0.1-SNAPSHOT"
description = "Food Ordering"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}