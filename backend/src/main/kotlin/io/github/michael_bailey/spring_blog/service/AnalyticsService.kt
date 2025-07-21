package io.github.michael_bailey.spring_blog.service

import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import io.github.michael_bailey.spring_blog.privacy.policy.DomainNameAnalyticsPolicy
import io.github.michael_bailey.spring_blog.privacy.policy.RequestAnalyticsPolicy
import io.github.michael_bailey.spring_blog.repository.DomainNameAnalyticsRepository
import io.github.michael_bailey.spring_blog.repository.RequestAnalyticsRepository
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.InetAddress
import java.util.*
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

	fun logRequestDomain(requestId: UUID, remoteAddr: String): DomainNameAnalyticsModel? {

		val requestInstant = clock.now()

		logger.info("Attempting to fetching domain name")

		val domainName = getDomainNameFromAddress(remoteAddr)

		if (domainName == null) {
			logger.info("No domain name found")
			return null
		}

		logger.info("saving domain name")
		val data = domainNameAnalyticsRepository.save(
			DomainNameAnalyticsModel(
				requestId = requestId,
				domainName = domainName,
				instant = requestInstant.toJavaInstant()
			)
		)

		return data
	}

	fun logRequest(requestId: UUID, method: String, requestURI: String) {
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

	fun getViewersAnalytics(vc: IViewerContext): Map<String, String> {
		val domainNamePolicy = DomainNameAnalyticsPolicy(vc)
		val requestPolicy = RequestAnalyticsPolicy(vc)

		val domainAnalytics = domainNameAnalyticsRepository.findByRequestId(vc.requestId)?.let {
			domainNamePolicy.execute(it)
		}

		val requestAnalytics = requestAnalyticsRepository.findByRequestId(vc.requestId)?.let {
			requestPolicy.execute(it)
		}

		return mapOf<String, String>(
			"domain" to (domainAnalytics?.let{ "${it.id}:${it.domainName}" } ?: "null"),
			"request" to (requestAnalytics?.let { "${it.id}:${it.method}:${it.requestURI}" } ?: "null")
		)
	}
}