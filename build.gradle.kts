import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.netflix.dgs.codegen") version "5.0.6"
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    kotlin("kapt") version "1.5.31"


    idea
//    kotlin("idea")
}


//idea {
//    module {
//        sourceDirs += file("generated/")
//    }
//}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {

    implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")



    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("com.querydsl:querydsl-mongodb")
    implementation("com.querydsl:querydsl-apt")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


//    kapt("org.springframework.boot:spring-boot-configuration-processor")
    kapt("com.querydsl:querydsl-apt:4.4.0:general")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//val generatedKotlinSources = project.file("build/generated").path
//kapt {
//    javacOptions {
//        option("-Akapt.kotlin.generated=$generatedKotlinSources\"")
//    }
//
//    annotationProcessor("org.springframework.data.mongodb.repository.support.MongoAnnotationProcessor")
//}
tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
    generateClient = true
    packageName = "com.example.dgs.generated"
    schemaPaths = listOf(
        "src/main/resources/schema"
    ).toMutableList()
    language = "kotlin"
//    generateInterfaces = true
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
