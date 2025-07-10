package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.model.AddressAnalyticsModel
import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import io.github.michael_bailey.spring_blog.repository.AddressAnalyticsRepository
import io.github.michael_bailey.spring_blog.repository.RequestAnalyticsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant

@OptIn(ExperimentalTime::class)
@Service
class AnalyticsService(
	private val clock: Clock,
	private val addressAnalyticsRepository: AddressAnalyticsRepository,
	private val requestAnalyticsRepository: RequestAnalyticsRepository
) {

	val logger: Logger = LoggerFactory.getLogger(this::class.java)

	fun logRequestAddress(remoteAddr: String) {
		val requestInstant = clock.now()

		logger.info("Logging request from $remoteAddr at $requestInstant")

		addressAnalyticsRepository.save(
			AddressAnalyticsModel(
				address = remoteAddr,
				instant = requestInstant.toJavaInstant()
			)
		)

		logger.info("Logged request address")

	}

	fun logRequest(requestId: String, method: String, requestURI: String) {
		logger.info("Logging request $requestId with method $method and URI $requestURI")

		requestAnalyticsRepository.save(
			RequestAnalyticsModel(
				requestId = requestId,
				method = method,
				requestURI = requestURI
			)
		)

		logger.info("Logged request")

	}
}