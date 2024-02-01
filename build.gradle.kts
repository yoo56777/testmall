plugins {
	java
	id("org.springframework.boot") version "2.7.16"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.testmall"
version = "v0.1"

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
	mavenCentral()
}

dependencies {
//	implementation(fileTree(mapOf("dir" to "lib", "include" to listOf("jdbcMx.jar"))))
//	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
//	implementation("com.google.code.gson:gson")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.session:spring-session-jdbc")
	implementation("org.springframework.session:spring-session-core")
	implementation("commons-codec:commons-codec:1.16.0")
	runtimeOnly("com.microsoft.sqlserver:mssql-jdbc")
	testImplementation ("org.apache.httpcomponents.client5:httpclient5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<Jar> {
	manifest {
		attributes("Class-Path" to "/usr/tandem/jdbcMx/T1275L37/lib/jdbcMx.jar")
	}
}