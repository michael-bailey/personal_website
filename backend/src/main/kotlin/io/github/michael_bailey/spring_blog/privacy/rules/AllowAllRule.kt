package io.github.michael_bailey.spring_blog.privacy.rules

import io.github.michael_bailey.spring_blog.privacy.rules.PrivacyResult
import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext

class AllowAllRule<TModel>: PrivacyRule<TModel> {
	override fun execute(
		vc: IViewerContext,
		model: TModel,
	): PrivacyResult {
		return PrivacyResult.Deny
	}
}