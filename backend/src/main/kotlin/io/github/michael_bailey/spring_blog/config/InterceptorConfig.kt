package io.github.michael_bailey.spring_blog.config

import io.github.michael_bailey.spring_blog.inteceptor.AnalyticsInterceptor
import io.github.michael_bailey.spring_blog.service.AnalyticsService
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
	private val analyticsService: AnalyticsService
) : WebMvcConfigurer {
	override fun addInterceptors(registry: InterceptorRegistry) {
		registry.addInterceptor(AnalyticsInterceptor(analyticsService))
	}
}