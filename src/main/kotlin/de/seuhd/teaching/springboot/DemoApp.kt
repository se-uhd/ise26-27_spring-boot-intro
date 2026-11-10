package de.seuhd.teaching.springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * The application class that [main] passes to `runApplication`. `@SpringBootApplication` turns on auto-configuration
 * and the component scan. The component scan creates a bean for each class in this package and its subpackages that is
 * annotated with `@Component` or with an annotation based on `@Component`, such as `@RestController` on
 * [HelloController]. `@ConfigurationPropertiesScan` creates a bean for each class annotated with
 * `@ConfigurationProperties`, such as [GreetingProperties].
 */
@SpringBootApplication
@ConfigurationPropertiesScan
class DemoApp

fun main(args: Array<String>) {
    runApplication<DemoApp>(*args) // vararg, * passes elements of args
}
