package io.github.michael_bailey.spring_blog.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.security.Principal

@Controller
class LoginController {

	@GetMapping("/login")
	fun getLoginPage(
		principal: Principal?,
		res: HttpServletResponse,
	): String {

		if (principal != null) {
			res.status = 302
			return "redirect:/admin"
		}

		return "login"
	}

}