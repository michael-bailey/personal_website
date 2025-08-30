package io.github.michael_bailey.spring_blog.security.principal

import java.security.Principal

sealed interface IPrincipal : Principal {
	val username: String
	val roles: List<String>

	override fun getName(): String = username
}