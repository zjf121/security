package org.example

import org.example.jwt.JwtProperties


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["org.example"])
@EnableConfigurationProperties(JwtProperties::class)
open class Main

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
