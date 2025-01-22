


# Lab 2: Exploring Apache Commons Lang

## Objective
Discover and utilize the Apache Commons Lang library to explore its methods, read documentation, and integrate it into a Java project.

---

## Prerequisites
- Installed Gradle on your system.
- Familiarity with adding dependencies to a Gradle project.

---

## Instructions

### Step 1: Set Up a New Gradle Project
1. Create a new Gradle project using the following command:
   ```
   gradle init
   ```
2. Choose `application` as the project type and `Java` as the language.

### Step 2: Add Apache Commons Lang as a Dependency
1. Open the `build.gradle` file and add the following dependency:
   ```groovy
   dependencies {
       implementation 'org.apache.commons:commons-lang3:3.13.0' // Adjust version if necessary
   }
   ```
2. Reload the Gradle project to download the dependency.

### Step 3: Explore the Documentation
1. Visit the [Apache Commons Lang Documentation](https://commons.apache.org/proper/commons-lang/).
2. Identify key classes and methods, such as:
   - `StringUtils`
   - `NumberUtils`
   - `RandomStringUtils`

### Step 4: Implement Sample Code
1. Write Java code to demonstrate the following:
   - Use `StringUtils` for string manipulation.
   - Generate random strings using `RandomStringUtils`.
   - Perform numeric operations using `NumberUtils`.
2. Place the code in:
   ```
   src/main/java
   ```
3. Example:
   ```java
   import org.apache.commons.lang3.StringUtils;
   import org.apache.commons.lang3.RandomStringUtils;
   import org.apache.commons.lang3.math.NumberUtils;

   public class ApacheCommonsExample {
       public static void main(String[] args) {
           // StringUtils example
           String reversed = StringUtils.reverse("BankingProject");
           System.out.println("Reversed String: " + reversed);

           // RandomStringUtils example
           String random = RandomStringUtils.randomAlphanumeric(10);
           System.out.println("Random String: " + random);

           // NumberUtils example
           boolean isNumber = NumberUtils.isCreatable("12345");
           System.out.println("Is '12345' a number? " + isNumber);
       }
   }
   ```

---

## Bonus
- Explore additional utilities in Apache Commons Lang, such as `DateUtils` or `ObjectUtils`.
- Create unit tests for your implementations.
