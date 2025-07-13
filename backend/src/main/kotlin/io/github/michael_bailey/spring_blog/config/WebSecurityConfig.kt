package io.github.michael_bailey.spring_blog.config

import io.github.michael_bailey.spring_blog.security.principal.AnonymousPrincipal
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CorsProperties::class)
class WebSecurityConfig(
	private val corsProperties: CorsProperties,
) {

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
				authorize("/", permitAll)
				authorize("/img/**", permitAll)
				authorize("/js/**", permitAll)
				authorize("/css/**", permitAll)
				authorize("/blog/**", permitAll)
				authorize("/project/**", permitAll)
				authorize("/login", permitAll)
				authorize("/api/**", permitAll)
				authorize("/graphql", permitAll)
				authorize("/admin/**", hasAuthority("ADMIN"))
				authorize(anyRequest, authenticated)
			}
			formLogin {
				loginPage = "/login"
				defaultSuccessUrl("/admin", true)
				permitAll()
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
				authorities = listOf(SimpleGrantedAuthority("NONE"))
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
	fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}