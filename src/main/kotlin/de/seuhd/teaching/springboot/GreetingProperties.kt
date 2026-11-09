package de.seuhd.teaching.springboot

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * The configuration of the greeting, bound from the keys under `greeting` in `application.yaml`. The
 * `@ConfigurationPropertiesScan` on [DemoApp] creates a bean of this class.
 *
 * @property salutation the word before the name, such as "Hello" in "Hello, World!".
 */
@ConfigurationProperties("greeting")
data class GreetingProperties(val salutation: String = "Hello")
