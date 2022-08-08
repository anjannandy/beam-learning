plugins {
    id("java")
    id("com.diffplug.spotless") version "6.9.0"
    jacoco
}

group = "com.learning.anandy"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.beam/beam-sdks-java-core
    implementation("org.apache.beam:beam-sdks-java-core:2.40.0")

    // https://mvnrepository.com/artifact/org.apache.beam/beam-runners-direct-java
    implementation("org.apache.beam:beam-runners-direct-java:2.40.0")

    // https://mvnrepository.com/artifact/org.apache.beam/beam-sdks-java-extensions-google-cloud-platform-core
    implementation("org.apache.beam:beam-sdks-java-extensions-google-cloud-platform-core:2.40.0")


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}
tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.7"
    // reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}