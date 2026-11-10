# Software Engineering (ISE) Winter Semester 2026/27

This repository contains a minimal Spring Boot application in Kotlin for the lecture on Spring Boot. `DemoApp.kt` and
`HelloController.kt` are the "Hello, World!" program from the slides. A greeting example shows constructor injection,
configuration properties, and a profile. [Coffee Kitty](https://github.com/se-uhd/coffee-kitty), the running example of
the course, uses the same concepts in an application with four modules and a PostgreSQL database. The application in
this repository needs neither Docker nor a database.

## Setup

The project needs the Temurin JDK 25 and Gradle 9.8. [mise](https://mise.jdx.dev) installs both tools in the versions
that [`mise.toml`](mise.toml) specifies. You do not need to install Kotlin or Spring Boot because Gradle downloads the
Kotlin compiler and the Spring Boot libraries during the build. In the repository directory, allow mise to use
`mise.toml`:

```shell
mise trust
```

Then install the JDK and Gradle:

```shell
mise install
```

If you have not activated mise in your shell with `mise activate`, prefix each command with `mise exec --`, for example
`mise exec -- gradle build`.

Because the project has no Gradle wrapper, IntelliJ IDEA has to use the Gradle installation from mise. Otherwise,
IntelliJ generates a wrapper with its bundled Gradle version and warns that this version is outdated. Open the
repository directory in IntelliJ and go to Settings | Build, Execution, Deployment | Build Tools | Gradle. Select "Local
installation" and enter the Gradle home, which is in the directory where mise installed Gradle. The following command
prints this directory, such as `/Users/<name>/.local/share/mise/installs/gradle/9.8.2` on macOS:

```shell
mise where gradle
```

The Gradle home is the subdirectory `gradle-9.8.2` of this directory, as in
`/Users/<name>/.local/share/mise/installs/gradle/9.8.2/gradle-9.8.2`. Then reload the Gradle project and start the
application with the run icon next to the `main` function in `DemoApp.kt`.

## Building and running the application

In IntelliJ, you can run each command below with the run icon next to its `shell` block.

Compile the code and run the tests. Gradle prints `BUILD SUCCESSFUL` if the code compiles and all tests pass:

```shell
gradle build
```

Start the application, which listens on port 8080 and runs until you stop it with Ctrl+C:

```shell
gradle bootRun
```

While the application runs, send the following requests from IntelliJ or from a second terminal. `GET /hello` returns
the fixed text `Hello, Spring Boot!`:

```shell
curl http://localhost:8080/hello
```

`GET /greeting` returns `Hello, World!`:

```shell
curl http://localhost:8080/greeting
```

The query parameter `name` replaces the default name "World". This request returns `Hello, Kitty!`:

```shell
curl "http://localhost:8080/greeting?name=Kitty"
```

## Profiles

The dev profile in `application.yaml` replaces the salutation "Hello" with "Hi". Stop the application with Ctrl+C and
start it with the dev profile:

```shell
gradle bootRun --args='--spring.profiles.active=dev'
```

Now `GET /greeting` returns `Hi, World!`:

```shell
curl http://localhost:8080/greeting
```

Instead of the argument `--spring.profiles.active`, the environment variable `SPRING_PROFILES_ACTIVE` can activate the
profile. Stop the application with Ctrl+C and start it with this variable:

```shell
SPRING_PROFILES_ACTIVE=dev gradle bootRun
```

`gradle bootJar` builds an executable JAR in `build/libs/` that contains the application and its dependencies:

```shell
gradle bootJar
```

After you stop the application, the following command starts it from this JAR and passes the dev profile as an
application argument:

```shell
java -jar build/libs/ise26-27_spring-boot-intro-0.0.1.jar --spring.profiles.active=dev
```

## Files

The Kotlin files are in `src/main/kotlin/de/seuhd/teaching/springboot/` and the tests in
`src/test/kotlin/de/seuhd/teaching/springboot/`. The configuration is in `src/main/resources/application.yaml`.

| File | Content |
|---|---|
| `DemoApp.kt` | The application class with `@SpringBootApplication` and the `main` function |
| `HelloController.kt` | The controller from the slides, which handles `GET /hello` |
| `GreetingController.kt` | A controller that receives a `GreetingService` through its constructor and handles `GET /greeting` |
| `GreetingService.kt` | The interface of the service that builds the greeting |
| `GreetingServiceImpl.kt` | The `@Service` bean that implements `GreetingService` and receives `GreetingProperties` through its constructor |
| `GreetingProperties.kt` | The configuration properties under the key `greeting` in `application.yaml` |
| `HelloControllerTest.kt`, `GreetingControllerTest.kt` | Tests that start the application without an HTTP server and send requests through MockMvc |
| `GreetingControllerDevProfileTest.kt` | A test of `GET /greeting` with the dev profile |
| `GreetingServiceImplTest.kt` | A unit test without Spring that passes a `GreetingProperties` object to the constructor of `GreetingServiceImpl` |

Unlike the version on the slides, `DemoApp.kt` has the annotation `@ConfigurationPropertiesScan`, which creates the
`GreetingProperties` bean for the greeting example. The first commit of the repository contains only the
"Hello, World!" program.

## Differences from Spring Initializr

`build.gradle.kts` starts from the build file that [Spring Initializr](https://start.spring.io/) generates for the
project type "Gradle - Kotlin" with Kotlin, Spring Boot 4.1.2, Java 25, and the dependency Spring Web. To match the
setup of Coffee Kitty, the repository differs from the generated project in these points:

- mise provides Java and Gradle, and the repository has no Gradle wrapper (`gradlew`).
- `gradle/gradle-daemon-jvm.properties` requires JDK 25 for the Gradle daemon. When IntelliJ starts Gradle with another
  JDK and Gradle cannot find an installed JDK 25, the foojay toolchain resolver in `settings.gradle.kts` lets Gradle
  download a JDK 25.
- The project uses Kotlin 2.4.21 instead of 2.3.21.
- The configuration is in `application.yaml` instead of `application.properties`.
- `gradle.properties` enables the configuration cache.
- Spring Boot 4.1.2 manages Jackson 3.1.7, which has a vulnerability. `gradle.properties` overrides this version with
  3.1.8 until a Spring Boot release manages a fixed version.
- The tests run with the JVM option `-Xshare:off`, which removes a warning about class data sharing.
- `.editorconfig` defines an indentation of four spaces instead of tabs for `.kt` and `.kts` files.
- A GitHub Actions workflow runs `gradle build` for every push to `main` and every pull request against `main`.
