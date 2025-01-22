# Lab 1: Converting the Banking Project to Gradle

## Objective
Learn how to use Gradle by converting your existing Java Banking Project into a Gradle build system project.

---

## Prerequisites
- Completed Java Banking Project.
- Installed Gradle on your system. ([Installation Guide](https://gradle.org/install/))
- Familiarity with your IDE's Gradle integration (e.g., IntelliJ IDEA or Eclipse).

---

## Instructions

### Step 1: Initialize Gradle in Your Project
1. In your intelliJ create a new application for banking using gradle as the build tool
2. Copy your functionality from project 1 to this project
3. Get any dependancies converted over to the gradle.build file (if you used any)

### Step 2: Configure the `build.gradle` File
1. Open the `build.gradle` file in your project.
2. Ensure the following dependencies are included:
   ```groovy
   plugins {
       id 'java'
       id 'application'
   }

   application {
       mainClass = 'com.example.Main' // Replace with your actual main class path
   }

   repositories {
       mavenCentral()
   }

   dependencies {
       testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0' // Update version if needed
   }
   ```
3. Adjust the `mainClass` property to reflect the main class of your Banking Project.

### Step 3: Organize Your Project
1. Move your source code to the following directory structure:
   ```
   src/main/java
   ```
2. Move your test code to:
   ```
   src/test/java
   ```
3. Ensure all necessary resource files are under:
   ```
   src/main/resources
   ```

### Step 4: Build and Run
1. Run the build command to verify the project setup:
   ```
   gradle build
   ```
2. Run the application:
   ```
   gradle run
   ```

### Step 5: Test Your Application
1. Execute tests using Gradle:
   ```
   gradle test
   ```
2. Ensure all tests pass successfully.

---



## Bonus
Explore additional Gradle features such as custom tasks, dependency management, or multi-module projects.

---
