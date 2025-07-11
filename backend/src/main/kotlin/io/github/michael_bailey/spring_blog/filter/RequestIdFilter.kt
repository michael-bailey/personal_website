package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.service.AnalyticsService
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import java.util.UUID

@Component
@Order(1)
class RequestIdFilter(
): Filter {

	companion object{
		private const val REQUEST_ID_HEADER: String = "X-Request-ID";
	}

	val logger: Logger = LoggerFactory.getLogger(this::class.java)

	override fun doFilter(
		request: ServletRequest?,
		response: ServletResponse?,
		chain: FilterChain?,
	) {

		request as CustomHttpRequest
		response as HttpServletResponse

		logger.info("Creating request ID")

		val requestId: String = UUID.randomUUID().toString() // generate new UUID
		logger.debug("Request ID: $requestId")

		logger.info("Setting request ID header and attribute")

		request.setAttribute(CustomHttpRequest.REQUEST_ID, requestId)
		response.setHeader(REQUEST_ID_HEADER, requestId)


		chain!!.doFilter(request, response)
	}
}