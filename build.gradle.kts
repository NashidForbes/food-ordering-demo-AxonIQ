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


extra["springCloudVersion"] = "2022.0.3"

dependencies {
    implementation("org.axonframework:axon-spring-boot-starter:4.7.0")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:4.7.0")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-web:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-config:3.1.0")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.0")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("io.micrometer:micrometer-observation:1.11.3")
    implementation ("io.micrometer:micrometer-tracing-bridge-brave:1.1.4")
    implementation ("io.zipkin.reporter2:zipkin-reporter-brave:2.16.4")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.0")
    implementation("net.logstash.logback:logstash-logback-encoder:7.4")
    testImplementation("junit:junit:4.13.1")
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("com.h2database:h2")
    implementation(project(":core"))
    implementation("org.apache.commons:commons-lang3:3.12.0")
    testImplementation("org.axonframework:axon-test:4.6.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.1.0")
}

group = "io.axoniq"
version = "0.0.1-SNAPSHOT"
description = "Food Ordering"
java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
