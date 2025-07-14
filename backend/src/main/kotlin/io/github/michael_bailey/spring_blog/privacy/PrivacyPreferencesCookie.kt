package io.github.michael_bailey.spring_blog.privacy

import kotlinx.serialization.Serializable

@Serializable
data class PrivacyPreferencesCookie(
	override val allowedDomainLogging: Boolean = false,
	override val allowedRequestLogging: Boolean = false,
) : IPrivacyPreferences {
	companion object {
		fun createNoAnalytics(): PrivacyPreferencesCookie {
			return PrivacyPreferencesCookie(
				allowedDomainLogging = false,
				allowedRequestLogging = false
			)
		}
	}
}