package com.example.jwt_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtDemoApplication

fun main(args: Array<String>) {
	runApplication<JwtDemoApplication>(*args)
}
