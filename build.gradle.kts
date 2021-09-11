import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("com.netflix.dgs.codegen") version "5.0.6"
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"

}

//extra["kotlin.version"] = "1.4.32"
//
//plugins {
//	kotlin("jvm") version "1.4.32"
//	kotlin("plugin.spring") version "1.5.0"
//	id("com.netflix.dgs.codegen") version "5.0.6"
//	id("org.springframework.boot") version "2.4.10"
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

	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//dependencies {
//	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))
//	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
//	implementation("com.netflix.graphql.dgs:graphql-dgs-extended-scalars")
//	implementation("org.springframework.boot:spring-boot-starter-web")
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//	implementation("com.github.javafaker:javafaker:1.+")
//
//	testImplementation("org.springframework.boot:spring-boot-starter-test")
//}

tasks.withType<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask> {
	generateClient = true
	packageName = "com.example.dgs.generated"
	schemaPaths = listOf("/Users/ultish/projects/dgs/src/main/resources" +
		"/schema").toMutableList()
	language = "kotlin"
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