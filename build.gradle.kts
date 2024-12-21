plugins {
    id("java")
    id("application")
}

group = "com.om1cael"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.postgresql:postgresql:42.7.4")
}

application {
    mainClass.set("com.om1cael.Main")
}

tasks.withType<JavaExec> {
    standardInput = System.`in`
}