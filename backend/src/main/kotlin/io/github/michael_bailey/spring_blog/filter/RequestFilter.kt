package io.github.michael_bailey.spring_blog.filter

import io.github.michael_bailey.spring_blog.http.CustomHttpRequest
import jakarta.servlet.Filter
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@Component
@Order(0)
class RequestFilter: Filter {


	override fun doFilter(
		request: ServletRequest?,
		response: ServletResponse?,
		chain: FilterChain?,
	) {
		val customRequest = CustomHttpRequest(request as HttpServletRequest)
		chain!!.doFilter(customRequest, response)
	}


}