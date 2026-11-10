package de.seuhd.teaching.springboot

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * The configuration of the greeting. Spring Boot binds the keys under `greeting` in `application.yaml` to the
 * properties of this class, such as `greeting.salutation` to [salutation]. `@ConfigurationPropertiesScan` on [DemoApp]
 * creates a bean of this class.
 *
 * @property salutation the word before the name, such as "Hello" in "Hello, World!".
 */
@ConfigurationProperties("greeting")
data class GreetingProperties(val salutation: String = "Hello")
