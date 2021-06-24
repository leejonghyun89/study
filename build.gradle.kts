import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.32"
	kotlin("plugin.spring") version "1.4.32"
}

group = "kr.co"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations.all {
	exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
	exclude(group = "io.undertow", module = "undertow-websockets-jsr")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation ("org.springframework.boot:spring-boot-starter-webflux")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation ("org.springframework.boot:spring-boot-starter-undertow")
	implementation ("org.jetbrains.kotlin:kotlin-reflect")
	implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")

	runtimeOnly ("com.h2database:h2")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
