package io.github.michael_bailey.spring_blog.inteceptor

import io.github.michael_bailey.spring_blog.service.AnalyticsService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor

@Component
class AnalyticsInterceptor(
	private val analyticsService: AnalyticsService,
) : HandlerInterceptor {

	val logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun preHandle(
		request: HttpServletRequest,
		response: HttpServletResponse,
		handler: Any,
	): Boolean {
		logger.info("Request received for ${request.method} ${request.requestURI}")
		logger.info("REQUEST_ID: ${request.requestId}")

		analyticsService.logRequestAddress(request.remoteAddr)
		analyticsService.logRequest(request.requestId, request.method, request.requestURI)

		return super.preHandle(request, response, handler)
	}

}