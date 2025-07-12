package io.github.michael_bailey.spring_blog.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.*

@Entity
data class DomainNameAnalyticsModel(
	@Id val id: UUID = UUID.randomUUID(),

	val domainName: String? = null,
	val instant: Instant = Instant.EPOCH
)