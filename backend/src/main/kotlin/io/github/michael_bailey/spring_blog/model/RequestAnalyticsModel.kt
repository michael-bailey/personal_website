package io.github.michael_bailey.spring_blog.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class RequestAnalyticsModel(
	@Id val id: UUID = UUID.randomUUID(),

	val requestId: String = "-1",
	val method: String = "GET",
	val requestURI: String = "/empty",
)
