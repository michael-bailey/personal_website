package io.github.michael_bailey.spring_blog.security.viewer

import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferences
import io.github.michael_bailey.spring_blog.security.principal.AnonymousPrincipal
import io.github.michael_bailey.spring_blog.security.principal.IPrincipal
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.ApplicationContext

import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Component
@OptIn(ExperimentalTime::class)
class ViewerContextFactory {

	fun fromAuthentication(
		authentication: Authentication?,
		request: HttpServletRequest,
		clock: Clock,
		applicationContext: ApplicationContext
	): IViewerContext {
		request as CustomHttpRequest
		val principal = authentication?.principal

		val viewer = when (principal) {
			is IPrincipal -> principal
			else -> AnonymousPrincipal
		}

		val privacyCookie = request.cookies.find { it.name == "privacy_preferences" }

		val privacyPreferences = privacyCookie?.value?.let {
			// add json serialisation
			PrivacyPreferences()
		} ?: PrivacyPreferences()

		return ViewerContext(
			applicationContext = applicationContext,

			viewer = viewer,
			locale = request.locale,
			requestId = request.requestId,
			requestTime = clock.now(),
			privacyPreferences = privacyPreferences
		)
	}
}