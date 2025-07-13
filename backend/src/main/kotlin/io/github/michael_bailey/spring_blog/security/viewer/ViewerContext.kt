package io.github.michael_bailey.spring_blog.security.viewer

import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferences
import io.github.michael_bailey.spring_blog.security.principal.IPrincipal
import org.springframework.context.ApplicationContext
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
data class ViewerContext(
	private val applicationContext: ApplicationContext,


	override val viewer: IPrincipal,
	override val locale: Locale,
	override val requestId: String,
	override val requestTime: Instant,
	override val privacyPreferences: PrivacyPreferences
): IViewerContext
