package io.github.michael_bailey.spring_blog.config

import com.apollographql.apollo.ApolloClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLConfig {

	@Value("\${github.access-token}")
	lateinit var accessToken: String
	
	val graphQLClient: ApolloClient by lazy {
		ApolloClient.Builder()
			.serverUrl("https://api.github.com/graphql")
			.addHttpHeader("Authorization", "bearer $accessToken")
			.build()
	}
}