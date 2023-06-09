import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    kotlin("plugin.jpa") version "1.8.21"
}

group = "com.d1t"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
// 위 스프링 도커 컴포즈 기능은 좀더 테스트 해봐야 할꺼 같음 일단 당장은 그냥 도커로 docker-compose 띄우는게 더 편함

    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // kotest 라이브러리 (코틀린 테스트 라이브러리)
    implementation("io.kotest:kotest-property-jvm:5.6.1")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.6.1")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.6.1")
    implementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    // mockk 라이브러리 (코틀린 모킹 라이브러리)
    testImplementation("io.mockk:mockk:1.13.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
