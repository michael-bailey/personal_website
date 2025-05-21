package io.github.michael_bailey.spring_blog.controller

import io.github.michael_bailey.spring_blog.service.BlogService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller()
@RequestMapping("/admin")
class AdminController(private val blogService: BlogService) {

	@GetMapping
	fun adminIndex(): String = "adminIndex"

	@PostMapping("/create-post")
	fun createPost(
	    @RequestParam name: String,
	    @RequestParam title: String,
	    @RequestParam content: String
	): String {
	    blogService.createPost(name, title, content)
	    return "redirect:/admin"
	}

}