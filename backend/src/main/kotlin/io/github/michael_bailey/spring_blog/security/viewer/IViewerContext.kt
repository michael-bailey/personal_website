package io.github.michael_bailey.spring_blog.security.viewer

import io.github.michael_bailey.spring_blog.privacy.PrivacyPreferences
import io.github.michael_bailey.spring_blog.security.principal.IPrincipal
import java.util.*
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
interface IViewerContext {
	val viewer: IPrincipal
	val locale: Locale
	val requestId: String
	val requestTime: Instant
	val privacyPreferences: PrivacyPreferences
}