package de.seuhd.teaching.springboot

import org.springframework.stereotype.Service

/**
 * The implementation of [GreetingService]. The component scan creates a bean of this class because of `@Service`, and
 * Spring passes the [GreetingProperties] bean to the constructor (constructor injection).
 */
@Service
class GreetingServiceImpl(private val properties: GreetingProperties) : GreetingService {
    override fun greet(name: String) = "${properties.salutation}, $name!"
}
