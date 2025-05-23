package io.github.michael_bailey.spring_blog.config

import org.springframework.beans.factory.annotation.Value
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

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

	@Value("\${blog.username}")
	private val username: String? = null

	@Value("\${blog.password}")
	private val password: String? = null



	@Bean
	@Throws(Exception::class)
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain? {

		http {
			authorizeHttpRequests {
				authorize("/", permitAll)
				authorize("/blog/**", permitAll)
				authorize("/admin/**", hasRole("ADMIN"))
				authorize(anyRequest, authenticated)
			}
			formLogin {
				loginPage = "/login"
				defaultSuccessUrl("/admin", true)
				permitAll()
			}
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