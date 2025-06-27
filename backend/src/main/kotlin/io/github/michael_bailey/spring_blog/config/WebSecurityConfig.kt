package io.github.michael_bailey.spring_blog.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import java.util.logging.Level
import kotlin.jvm.java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(CorsProperties::class)
class WebSecurityConfig(
	private val corsProperties: CorsProperties,
) {

	private val logger: Logger = LoggerFactory.getLogger(WebSecurityConfig::class.java.getName())

	@Value("\${blog.username}")
	private val username: String? = null

	@Value("\${blog.password}")
	private val password: String? = null

	@Bean
	@Throws(Exception::class)
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {
		logger.info("got allowed origins for CORS: ${corsProperties.allowedOrigins.toString()}")
		http {
			authorizeHttpRequests {
				authorize("/", permitAll)
				authorize("/img/**", permitAll)
				authorize("/js/**", permitAll)
				authorize("/css/**", permitAll)
				authorize("/blog/**", permitAll)
				authorize("/project/**", permitAll)
				authorize("/admin/**", hasRole("ADMIN"))
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
		}

		return http.build()
	}

	@Bean
	fun corsConfigurationSource(): CorsConfigurationSource {
		val configuration = CorsConfiguration()
		configuration.allowedOrigins = corsProperties.allowedOrigins
		configuration.allowedMethods =
			listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
		configuration.allowedHeaders = listOf("*")
		configuration.allowCredentials = true

		val source = UrlBasedCorsConfigurationSource()
		source.registerCorsConfiguration("/**", configuration)
		return source
	}

	@Bean
	fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
		val user: UserDetails =
			User.builder()
				.username(username)
				.password(passwordEncoder.encode(password))
				.roles("USER", "ADMIN")
				.build()

		return InMemoryUserDetailsManager(user)
	}

	@Bean
	fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

}