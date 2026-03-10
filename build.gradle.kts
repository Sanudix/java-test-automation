plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0"
}

group = "io.github.sanudix.automation"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

// ─── Версии зависимостей ───────────────────────────────────────
val allureVersion = "2.32.0"
val aspectjVer = "1.9.23"
val restAssuredVersion = "5.4.0"
val selenideVersion = "7.14.0"
val jacksonVersion = "2.17.0"
val lombokVersion = "1.18.32"
val junitVersion = "5.10.0"

val agent: Configuration by configurations.creating

dependencies {

    // ─── JUnit 5 ───────────────────────────────────────────────
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // ─── Web UI (Selenide) ─────────────────────────────────────
    implementation("com.codeborne:selenide:$selenideVersion")

    // ─── API (RestAssured) ─────────────────────────────────────
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    implementation("io.rest-assured:json-path:$restAssuredVersion")
    implementation("io.rest-assured:xml-path:$restAssuredVersion")

    // ─── Mobile (Appium + Selenide-Appium) ─────────────────────
    implementation("io.appium:java-client:9.3.0")
    implementation("com.codeborne:selenide-appium:$selenideVersion")

    // ─── Allure ────────────────────────────────────────────────
    implementation("io.qameta.allure:allure-junit5:$allureVersion")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
    implementation("io.qameta.allure:allure-rest-assured:$allureVersion")

    // ─── Jackson ───────────────────────────────────────────────
    implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jacksonVersion")

    // ─── Assertions ────────────────────────────────────────────
    testImplementation("org.assertj:assertj-core:3.25.3")

    // ─── Config (Owner) ────────────────────────────────────────
    implementation("org.aeonbits.owner:owner:1.0.12")

    // ─── Utils ─────────────────────────────────────────────────
    implementation("com.github.romankh3:image-comparison:4.4.0")
    implementation("com.google.code.findbugs:jsr305:3.0.2")

    // ─── Lombok ────────────────────────────────────────────────
    compileOnly("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")

    // ─── Logging ───────────────────────────────────────────────
    implementation("org.slf4j:slf4j-simple:2.0.13")

    // ─── AspectJ ───────────────────────────────────────────────
    agent("org.aspectj:aspectjweaver:$aspectjVer")
}

// ─── Прокидываем все -D параметры в тесты ──────────────────────
tasks.withType<Test> {
    useJUnitPlatform()
    jvmArgs("-javaagent:${agent.singleFile}")
    systemProperty("allure.results.directory", "build/allure-results")

    // Прокидываем ВСЕ системные свойства в тесты
    // Это позволяет Jenkins передавать -Denv=remote, -DwebBrowserName=firefox и т.д.
    systemProperties(System.getProperties().map { it.key.toString() to it.value.toString() }.toMap())

    // Параллельность через -Dthreads=N
    val threads = System.getProperty("threads", "1")
    systemProperty("junit.jupiter.execution.parallel.enabled", threads != "1")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "fixed")
    systemProperty("junit.jupiter.execution.parallel.config.fixed.parallelism", threads)
}

// ─── Задачи для запуска по тегам ───────────────────────────────
// Использование: ./gradlew clean test -Dtag=api -Denv=remote

tasks.test {
    val tag = System.getProperty("tag", "")
    if (tag.isNotBlank()) {
        useJUnitPlatform { includeTags(tag) }
    }
}

// ─── Allure ────────────────────────────────────────────────────
allure {
    version.set(allureVersion)

    adapter {
        allureJavaVersion.set(allureVersion)
        aspectjVersion.set(aspectjVer)
        autoconfigure.set(true)
        autoconfigureListeners.set(true)
        aspectjWeaver.set(true)

        frameworks {
            junit5 {
                enabled.set(true)
            }
        }
    }

    report {
        dependsOnTests.set(true)
    }
}
