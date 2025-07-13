package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextFactory
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Component
@Order(3)
@OptIn(ExperimentalTime::class)
class ViewerContextFilter(
	private val viewerContextFactory: ViewerContextFactory,
	private val viewerContextHolder: ViewerContextHolder,
	private val clock: Clock,
	private val applicationContext: ApplicationContext,
) : OncePerRequestFilter() {

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain,
	) {
		val authentication = SecurityContextHolder.getContext().authentication
		val viewerContext = viewerContextFactory.fromAuthentication(
			authentication,
			request,
			clock,
			applicationContext
		)
		viewerContextHolder.context = viewerContext
		filterChain.doFilter(request, response)
	}
}