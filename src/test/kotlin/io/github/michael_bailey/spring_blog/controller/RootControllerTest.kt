package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.config.WebSecurityConfig
import io.github.michael_bailey.spring_blog.model.BlogPostModel
import io.github.michael_bailey.spring_blog.service.BlogService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@AutoConfigureMockMvc(addFilters = true)
@Import(WebSecurityConfig::class)
@ActiveProfiles("test")
@WebMvcTest(RootController::class)
class RootControllerTest {

	@Autowired
	lateinit var mockMvc: MockMvc

	@MockitoBean
	lateinit var blogService: BlogService

	@Test
	fun `index page is accessable`() {
		val post = BlogPostModel(
			name = "sample-post",
			title = "Sample Post",
			content = "Markdown **content**"
		)
		`when`(blogService.getByName("sample-post")).thenReturn(post)
		`when`(blogService.getAllBlogPosts()).thenReturn(listOf(post))

		mockMvc.get("/")
			.andExpect {
				status { isOk() }
				view { name("index") }
				model { attributeExists("blogPosts") }
			}

	}

}