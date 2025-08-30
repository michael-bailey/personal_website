package io.github.michael_bailey.spring_blog.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.random.Random
import kotlin.time.Clock
import kotlin.time.ExperimentalTime


@OptIn(ExperimentalTime::class)
@Configuration
class GlobalConfig {

	@Bean
	fun clock(): Clock {
		return Clock.System
	}

	@Bean
	fun random(): Random {
		return Random.Default
	}

}