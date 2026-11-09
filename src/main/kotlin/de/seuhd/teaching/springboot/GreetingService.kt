package de.seuhd.teaching.springboot

/** Builds the greeting that [GreetingController] returns. */
interface GreetingService {
    /** Returns the greeting for [name], such as "Hello, World!". */
    fun greet(name: String): String
}
