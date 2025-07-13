package io.github.michael_bailey.spring_blog.security.principal

data object AnonymousPrincipal : IPrincipal {
	override val username: String = "anonymousUser"
	override val roles: List<String> = emptyList()

}