package io.github.michael_bailey.spring_blog.privacy

interface IPrivacyPreferences {
	val allowedDomainLogging: Boolean
	val allowedRequestLogging: Boolean
}
