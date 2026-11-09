package de.seuhd.teaching.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * The Spring Boot application. `@SpringBootApplication` turns on the auto-configuration and the component scan, which
 * creates a bean for each annotated class in this package and its subpackages, such as [HelloController].
 */
@SpringBootApplication
class DemoApp

fun main(args: Array<String>) {
    runApplication<DemoApp>(*args)
}
