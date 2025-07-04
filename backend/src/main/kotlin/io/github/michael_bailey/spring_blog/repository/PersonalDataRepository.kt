package io.github.michael_bailey.spring_blog.repository

import com.github.client.UserAvatarUrlQuery
import io.github.michael_bailey.spring_blog.config.GraphQLConfig
import org.springframework.stereotype.Repository
import org.springframework.web.context.annotation.RequestScope

@Repository
@RequestScope
class PersonalDataRepository(
	private val graphQLConfig: GraphQLConfig,
) {

	suspend fun getUserAvatar(): String = graphQLConfig.graphQLClient.query(
		UserAvatarUrlQuery()
	).execute().data?.viewer?.avatarUrl as String


}