package io.github.michael_bailey.spring_blog.repository

import io.github.michael_bailey.spring_blog.model.DomainNameAnalyticsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DomainNameAnalyticsRepository : JpaRepository<DomainNameAnalyticsModel, String> {
	fun findByRequestId(requestId: UUID): DomainNameAnalyticsModel?
}
