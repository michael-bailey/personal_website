package io.github.michael_bailey.spring_blog.privacy

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.util.*
import java.io.Serializable as JavaSerializable


@Serializable
data class PrivacyPreferencesCookie(
	override val allowedDomainLogging: Boolean = false,
	override val allowedRequestLogging: Boolean = false,
) : IPrivacyPreferences, JavaSerializable {

	companion object {
		fun decode(encodedCookie: String): PrivacyPreferencesCookie = Json.decodeFromString(
			serializer(),
			String(Base64.getUrlDecoder().decode(encodedCookie.toByteArray(Charsets.UTF_8)), Charsets.UTF_8)
		)
	}

	fun encode(): String = this.run {
		Base64.getUrlEncoder().encode(
			Json.encodeToString(serializer(), this).toByteArray(Charsets.UTF_8)
		).toString(Charsets.UTF_8)
	}
}

