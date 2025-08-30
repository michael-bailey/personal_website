package io.github.michael_bailey.spring_blog.security.principal

data class UserPrincipal(
	override val username: String,
	override val roles: List<String>
) : IPrincipal