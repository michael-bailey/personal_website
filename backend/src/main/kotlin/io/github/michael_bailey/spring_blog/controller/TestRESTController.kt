package io.github.michael_bailey.spring_blog.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@RestController
@RequestMapping("/api")
class TestRESTController {

	@GetMapping("/test")
	@ResponseBody
	fun testCors(): String {
	    return "CORS is working!"
	}

}