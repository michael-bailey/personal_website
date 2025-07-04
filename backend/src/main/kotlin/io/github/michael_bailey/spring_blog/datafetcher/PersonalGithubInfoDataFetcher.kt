package io.github.michael_bailey.spring_blog.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.github.michael_bailey.spring_blog.repository.PersonalDataRepository

@DgsComponent
class PersonalGithubInfoDataFetcher(
	private val personalDataRepository: PersonalDataRepository,
) {

	@DgsQuery
	suspend fun getUserAvatar(): String {
		val res = personalDataRepository.getUserAvatar()

		return res
	}
}