package io.github.michael_bailey.spring_blog.config

import io.github.michael_bailey.spring_blog.security.principal.AnonymousPrincipal
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext
import io.github.michael_bailey.spring_blog.security.viewer.ViewerContextHolder
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint
import org.springframework.web.context.WebApplicationContext
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CorsProperties::class)
class WebSecurityConfig(
	private val corsProperties: CorsProperties,
) {

	val ANON_AUTHORITY = "NONE"
	val ADMIN_AUTHORITY = "ADMIN"

	private val logger: Logger =
		LoggerFactory.getLogger(WebSecurityConfig::class.java.getName())

	@Value("\${blog.username}")
	val username: String? = null

	@Value("\${blog.password}")
	val password: String? = null

	@Bean
	@Throws(Exception::class)
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
		logger.info("got allowed origins for CORS: ${corsProperties.allowedOrigins}")
		http {
			authorizeHttpRequests {
				authorize("/admin/**", hasAuthority(ADMIN_AUTHORITY))
				authorize(anyRequest, hasAuthority(ANON_AUTHORITY))
			}
			formLogin {
				loginPage = "/login"
				defaultSuccessUrl("/admin", true)
				permitAll()
			}
			exceptionHandling {
				authenticationEntryPoint = Http403ForbiddenEntryPoint();
			}
			cors { }
			csrf {
				disable()
			}
			securityContext {
				requireExplicitSave = false
			}
			anonymous {
				principal = AnonymousPrincipal
				authorities = listOf(SimpleGrantedAuthority(ANON_AUTHORITY))
			}
		}

		return http.build()
	}

	@Bean
	fun corsConfigurationSource(): CorsConfigurationSource {
		val configuration = CorsConfiguration()
		configuration.allowedOrigins = listOf(
			"https://michael-bailey.net",
			"https://new.michael-bailey.net"
		) + corsProperties.allowedOrigins
		configuration.allowedMethods =
			listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
		configuration.allowedHeaders = listOf("*")
		configuration.allowCredentials = true

		val source = UrlBasedCorsConfigurationSource()
		source.registerCorsConfiguration("/**", configuration)
		return source
	}

	@Bean
	@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	fun viewerContext(
		viewerContextHolder: ViewerContextHolder
	): IViewerContext = viewerContextHolder.context

	@Bean
	fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}