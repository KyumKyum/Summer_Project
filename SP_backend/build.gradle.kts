import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Dependencies {
    private object Versions{
        const val Hibernate_VERSION = "6.2.0."
    }

    object Spring{

    }

    object Utils{
        const val lombok = "org.projectlombok:lombok"
    }

    object Database {
        const val jpa = "org.springframework.boot:spring-boot-starter-data-jpa"
        const val jdbc = "org.springframework.boot:spring-boot-starter-jdbc"
        const val postgresql = "org.postgresql:postgresql"
        const val h2 = "com.h2database:h2"
        const val hibernate = "org.hibernate.validator:hibernate-validator:${Versions.Hibernate_VERSION}Final"
        const val redis = "org.springframework.boot:spring-boot-starter-data-redis"
    }

}

plugins {
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"
    kotlin("plugin.allopen") version "1.4.32"
}

group = "com.spproject"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


dependencies {
    implementation(Dependencies.Database.jpa)
    implementation(Dependencies.Database.jdbc)
    implementation(Dependencies.Database.hibernate)
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(Dependencies.Database.redis)
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly(Dependencies.Database.h2)
    runtimeOnly(Dependencies.Database.postgresql)
    annotationProcessor(Dependencies.Utils.lombok)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation(kotlin("test"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


allOpen {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.Embeddable")
    annotation("jakarta.persistence.MappedSuperclass")
}
