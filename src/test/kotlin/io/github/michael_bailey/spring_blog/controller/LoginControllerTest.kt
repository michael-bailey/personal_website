package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@AutoConfigureMockMvc(addFilters = true)
@ActiveProfiles("test")
@Import(WebSecurityConfig::class)
@WebMvcTest(LoginController::class)
class LoginControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun `unauthenticated user should see login page`() {
		mockMvc.get("/login")
			.andExpect {
				status { isOk() }
				view().name("login")
			}
	}

	@Test
	@WithMockUser(username = "testuser")
	fun `authenticated user should be redirected to admin`() {
		mockMvc.get("/login")
			.andExpect {
				status { is3xxRedirection() }
				redirectedUrl("/admin")
			}
	}
}