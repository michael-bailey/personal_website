package io.github.michael_bailey.spring_blog.repository

import io.github.michael_bailey.spring_blog.model.RequestAnalyticsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RequestAnalyticsRepository: JpaRepository<RequestAnalyticsModel, String>
