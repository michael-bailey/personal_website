package io.github.michael_bailey.spring_blog.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import io.github.michael_bailey.spring_blog.repository.PersonalGithubDataRepository


/**
 * GraphQL data fetcher component for retrieving personal GitHub data.
 * Handles queries related to user's GitHub profile information.
 */
@DgsComponent
class PersonalGithubDataFetcher(
	private val personalGithubDataRepository: PersonalGithubDataRepository,
) {

	/**
	 * Fetches the user's GitHub avatar URL.
	 *
	 * @return The URL string of the user's GitHub avatar
	 */
	@DgsQuery
	suspend fun getUserAvatar(): String {
		val res = personalGithubDataRepository.getUserAvatar()

		return res
	}
}