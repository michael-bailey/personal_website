package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import io.github.michael_bailey.spring_blog.service.BlogService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view

@AutoConfigureMockMvc(addFilters = true)
@ActiveProfiles("test")
@Import(WebSecurityConfig::class)
@WebMvcTest(AdminController::class)
class AdminControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockitoBean
	lateinit var blogService: BlogService

	@Test
	fun `Attempt to load admin panel when logged out`() {
		mockMvc.get("/admin")
			.andExpect {
				status { is3xxRedirection() }
				view().name("login")
			}
	}

	@Test
	@WithMockUser(username = "TestUser", roles = ["USER", "ADMIN"])
	fun `Attempt to load admin panel when logged in`() {
		mockMvc.get("/admin")
			.andExpect {
				status { isOk() }
				view().name("adminIndex")
			}
	}

	@Test
	fun `Attempt to create post when logged out`() {
		mockMvc.post("/admin/create-post")
			.andExpect {
				status { is3xxRedirection() }
				view().name("/login")
			}
	}

	@Test
	@WithMockUser(username = "TestUser", roles = ["USER", "ADMIN"])
	fun `Creates new article`() {

		val name = "test post"
		val title = "test post title"
		val content = "# Test post"

		`when`(blogService.createPost(
			name = name,
			title = title,
			content = content
		)).thenReturn(null)
		
		mockMvc.post("/admin/create-post") {
			formField("name", name)
			formField("title", title)
			formField("content", content)
		}.andExpect {
			status { redirectedUrl("/") }
			view().name("/")
		}
	}


}