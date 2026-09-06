package net.michael_bailey.home.repository

import net.michael_bailey.home.model.Article
import net.michael_bailey.home.model.ArticleContent
import net.michael_bailey.home.model.ContentSection
import net.michael_bailey.home.model.MultiMediaArticle
import org.koin.core.annotation.Single

@Single
class HobbyContentRepository {
	fun getContentSections(): List<ContentSection> = listOf(
		ContentSection(
			header = "Hobbies",
			description = """"This is work in progress, expect stuff to do with swimming, 
				electronics, gym, and robotics""".trimIndent(),
			articles = listOf(
				get3DPrintingArticle(),
				getScoutingArticle(),
				getSportArticle(),
			)
		),
	)

	private fun getScoutingArticle(): Article = MultiMediaArticle(
		header = "Scouting", content = listOf(
			ArticleContent.Paragraph(
				"""
				I've been a part of the scouts since being a beaver. During This i have 
				had loads of different experiences through our weekly meetings, camps, and
				meeting others.
			""".trimIndent()
			),
			ArticleContent.Paragraph(
				"""
				A lot of the skills revolve around team work, as well as more practical
				skills such as fire lighting, shelter making, pioneering, archery, and rifling.
			""".trimIndent()
			),
			ArticleContent.Paragraph(
				"""
				I am now a leader of my group, planning sessions, and being tech support.
				Along with this i am the chairman of the group, ensuring everything is run 
				smoothly behind the scenes.
			""".trimIndent()
			),
		)
	)

	private fun getSportArticle(): Article = MultiMediaArticle(
		header = "Sports", content = listOf(
			ArticleContent.Paragraph(
				"""
				I'm an avid swimmer, boulderer, cyclist, and gym goer. I find each of these serve
				a different purpose. Swimming is a mix of speed and stamina training, gym 
				I do both cardio and weight training. And bouldering is body weight exercises,
				and dexterity.
			""".trimIndent()
			),
		)
	)

	private fun get3DPrintingArticle(): Article = MultiMediaArticle(
		header = "3D Printing", content = listOf(
			ArticleContent.Paragraph(
				"""
					I've been using 3d printing to learn how to use cad software, taking measurements,
					and properly setup a 3d printer to make clean prints.
				""".trimIndent()
			), ArticleContent.Paragraph(
				"""
					I've designed some of my own prints to help out with family and friends.
					these have been designed and printed to fix broken parts for tools. For
					instance, a new clip for some secateurs, new ends for tent poles, and  
					stops for assistance wheels for my dog. I have a couple projects i'm looking 
					into designing.
				""".trimIndent()
			), ArticleContent.Paragraph(
				"""
				 First is a 'small' pipe organ. I have created a tuned percussion instrument 
				 before and have understanding how wind instruments work. Using this i should 
				 be able to design the pipes and internal tubing to make a small working 
				 pipe organ. If this were to succeed, then mi could make multiple units 
				 with different voices, to create a slightly larger one, maybe even with 
				 stops.
			""".trimIndent()
			), ArticleContent.Paragraph(
				"""
				Second project is an enclosure for my Bramble Pi project. The raspberry pis
				i'm currently using, standing on edge, with no support. Plus they do have
				a screen connected to the master control plane pi. So having an enclosure
				that can contain all of them in one place, alongside the screen would be
				nice and more reassuring.
			""".trimIndent()
			)
		)
	)
}