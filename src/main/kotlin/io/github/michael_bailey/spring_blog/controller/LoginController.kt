package io.github.michael_bailey.spring_blog.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginController {

	@GetMapping("/login")
	fun getLoginPage(): String = "login"

}