package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import io.github.michael_bailey.spring_blog.service.AnalyticsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(3)
class AnalyticsFilter(
	private val analyticsService: AnalyticsService,
	private val applicationContext: ApplicationContext
) : OncePerRequestFilter() {

	private val logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain
	) {

		val vc = applicationContext.getBean(ViewerContextHolder::class.java).context

		logger.info("Logging request for ${request.method} ${request.requestURI} for user ${vc.viewer.username}")

		logger.info("Request received for ${request.method} ${request.requestURI}")
		logger.info("REQUEST_ID: ${request.requestId}")

		analyticsService.logRequestAddress(request.remoteAddr)
		analyticsService.logRequest(request.requestId, request.method, request.requestURI)

		filterChain.doFilter(request, response)
	}
}