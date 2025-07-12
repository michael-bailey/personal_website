package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import io.github.michael_bailey.spring_blog.repository.DomainNameAnalyticsRepository
import io.github.michael_bailey.spring_blog.repository.RequestAnalyticsRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.InetAddress
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.toJavaInstant

@OptIn(ExperimentalTime::class)
@Service
class AnalyticsService(
	private val clock: Clock,
	private val domainNameAnalyticsRepository: DomainNameAnalyticsRepository,
	private val requestAnalyticsRepository: RequestAnalyticsRepository
) {

	val logger: Logger = LoggerFactory.getLogger(this::class.java)

	fun logRequestAddress(remoteAddr: String) {
		val requestInstant = clock.now()

		logger.info("Fetching domain name")

		val domainName = getDomainNameFromAddress(remoteAddr)

		if (domainName == null) {
			logger.info("No domain name found")
			return
		}

		domainNameAnalyticsRepository.save(
			DomainNameAnalyticsModel(
				domainName = domainName,
				instant = requestInstant.toJavaInstant()
			)
		)

		logger.info("Logged domain address")

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

	private fun getDomainNameFromAddress(remoteAddr: String): String? {
		val domainName =  InetAddress.getByName(remoteAddr).hostName
		return if (domainName != remoteAddr) { domainName } else { null }
	}
}