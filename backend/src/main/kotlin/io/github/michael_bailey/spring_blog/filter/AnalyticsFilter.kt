package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.service.AnalyticsService
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(2)
class AnalyticsFilter(
	private val analyticsService: AnalyticsService,
): Filter {

	val logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun doFilter(
		request: ServletRequest?,
		response: ServletResponse?,
		chain: FilterChain?,
	) {

		request as HttpServletRequest

		logger.info("Request received for ${request.method} ${request.requestURI}")
		logger.info("REQUEST_ID: ${request.requestId}")

		analyticsService.logRequestAddress(request.remoteAddr)
		analyticsService.logRequest(request.requestId, request.method, request.requestURI)

		chain!!.doFilter(request, response)
	}
}