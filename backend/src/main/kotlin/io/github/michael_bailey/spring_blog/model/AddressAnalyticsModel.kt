package io.github.michael_bailey.spring_blog.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.Instant
import java.util.*

@Entity
data class AddressAnalyticsModel(
	@Id val id: UUID = UUID.randomUUID(),

	val address: String = "0.0.0.0",
	val instant: Instant = Instant.EPOCH
)