package io.github.michael_bailey.spring_blog.privacy.rules

import io.github.michael_bailey.spring_blog.security.viewer.IViewerContext

interface PrivacyRule<TModel> {
	fun execute(vc: IViewerContext, model: TModel): PrivacyResult
}