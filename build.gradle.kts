plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.intellij") version "1.17.3"
}

group = "com.yyz"
version = "1.0-SNAPSHOT"

repositories {
//    mavenCentral()
    maven {
        url = uri("https://maven.aliyun.com/repository/public")
    }
}

// 依赖
dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.10.0")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
//    version.set("2023.2.6")
//    type.set("IC") // Target IDE Platform
    // 修改使用本地Idea调试
    localPath.set("D:\\DevApp\\Jetbrains\\IntelliJ IDEA Ultimate")

    plugins.set(listOf(/* Plugin Dependencies */))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
    // 解决运行时控制台中文乱码
    withType<JavaExec> {
        jvmArgs = listOf("-Dfile.encoding=UTF-8", "-Dsun.stdout.encoding=UTF-8", "-Dsun.stderr.encoding=UTF-8")
    }
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    patchPluginXml {
        sinceBuild.set("232")
        untilBuild.set("242.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
