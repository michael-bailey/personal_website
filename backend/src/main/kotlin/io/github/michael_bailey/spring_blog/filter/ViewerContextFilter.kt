package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.security.PrivacyPreferencesCookie
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextFactory
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import jakarta.servlet.FilterChain
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
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

	private val COOKIE_NAME = "privacy_preferences"

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain,
	) {

		request as CustomHttpRequest

		var cookie = request.cookies.find { it.name == COOKIE_NAME }?.let {
			Json.decodeFromString<PrivacyPreferencesCookie>(it.value)
		}

		if (cookie == null) {
			cookie = PrivacyPreferencesCookie.createNoAnalytics()
			response.addCookie(
				Cookie(
					COOKIE_NAME,
					Json.encodeToString<PrivacyPreferencesCookie>(cookie)
				)
			)
		}

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