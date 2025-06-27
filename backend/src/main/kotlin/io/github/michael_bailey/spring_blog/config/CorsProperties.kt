package io.github.michael_bailey.spring_blog.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "cors")
data class CorsProperties(
	var allowedOrigins: List<String> = listOf()
)