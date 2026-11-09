package de.seuhd.teaching.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * The Spring Boot application. `@SpringBootApplication` turns on the auto-configuration and the component scan, which
 * creates a bean for each annotated class in this package and its subpackages, such as [HelloController].
 * `@ConfigurationPropertiesScan` does the same for the classes annotated with `@ConfigurationProperties`, such as
 * [GreetingProperties].
 */
@SpringBootApplication
@ConfigurationPropertiesScan
class DemoApp

fun main(args: Array<String>) {
    runApplication<DemoApp>(*args)
}
