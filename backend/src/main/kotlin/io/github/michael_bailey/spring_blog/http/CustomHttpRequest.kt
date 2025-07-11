package io.github.michael_bailey.spring_blog.http

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import org.springframework.http.HttpRequest
import org.springframework.http.client.support.HttpRequestWrapper

class CustomHttpRequest(
	request: HttpServletRequest,
): HttpServletRequestWrapper(request) {

	companion object {
		const val REQUEST_ID = "requestId"
	}

	override fun getRequestId(): String = (request).getAttribute(REQUEST_ID) as String


}