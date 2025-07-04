package io.github.michael_bailey.spring_blog.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody


/**
 * REST controller for testing API endpoints.
 * Provides test endpoints to verify some functionality.
 */
@RestController
@RequestMapping("/api")
class TestRESTController {

	/**
	 * Test endpoint to verify CORS configuration.
	 *
	 * @return A string message indicating if CORS is working
	 */
	@GetMapping("/test")
	@ResponseBody
	fun testCors(): String {
	    return "CORS is working!"
	}

}