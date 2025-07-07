import SectionedContent from "@/components/server/SectionedContent";
import Section from "@/components/server/Section";
import ProjectSection from "@/components/server/ProjectSection";
import {getImageUrl} from "@/lib/api/staticContent";
import Image from "next/image";
import {query} from "@/lib/graphql/serverClient";
import { gql } from "@apollo/client";


const avatarUrlQuery = gql`
    query GraphQLQuery {
        getUserAvatar
    }
`

export default async function Home() {

	const avatarUrl = await query({ query: avatarUrlQuery })
		.then(res => res.data.getUserAvatar as string)

	return (
		<div className="h-full grid pt-2">
			<SectionedContent>
				<Section>
					<Image className="float-right mx-8 rounded-2xl shadow-lg max-w-44" src={avatarUrl} alt="profile image" width={512} height={512}  />
					<div className="p-0">
						<h1 className="text-6xl">Welcome</h1>
						<hr className="py-2" />

						<p className="text-justify">Hi there, I&#39;m Michael Bailey.
							I&#39;m a software engineer with a deep interest in user-focused,
							technology and engineering.
							I enjoy building tools to assist myself, and others, with my other
							hobbies and interests.</p>
						<p className="text-justify">This site is a place for me to share
							projects I&#39;m working on,
							write
							about technologies I&#39;m learning, and put into practice my
							learnings.</p>
					</div>
				</Section>
				<Section>

					<div className="h-fit inline-block">
						<h2 className="text-3xl pb-3">Projects</h2>
						<p className="text-justify">
							Here’s a selection of personal projects I’ve built or continue to
							work on. They aren’t meant to be a complete list, but each one
							solves
							a problem I’ve personally encountered or adds functionality I
							found
							missing on my own devices. I&#39;ll be writing up stories about
							some of the
							projects, detailing what I find to be the best things I learnt
							during the
							creation of the projects
						</p>
					</div>

					<ProjectSection
						projectName="Gym Log Book"
						githubUrl="https://github.com/michael-bailey/gym-log-book">
						<Image
							className="float-right mx-8 rounded-2xl shadow-lg max-w-44"
							src={getImageUrl("gym_list.png")} alt="Gym Log Book app screenshot"
							width={512} height={512}
						/>
						<p>
							Gym Log Book is a full native Android app I built to help log
							my
							strength training progress. It features structured navigation,
							local data persistence, with plans to display graphs and
							support
							more exercise types.
						</p>
						<br />
						<p>
							When I started at the gym, I wanted a way of logging my
							progress.
							Whilst I found lots of apps that could help with this, I found
							most
							of them to have complex functionality, or had lacking UX.
							So I decided to create my own app.
						</p>
						<br />
						<p>
							After starting with some Apple shortcuts, I got the basic idea
							of what
							I wanted to include.
							Settings for free weights, or machines; Auto complete for
							sets; a list
							and possibly graphs for progress.
							All of which expanded my knowledge of Android app development.
						</p>
					</ProjectSection>

					<ProjectSection
						projectName="Compass"
						githubUrl={`https://github.com/michael-bailey/Compass}`}>
						<Image
							className="block float-right mx-8 rounded-2xl shadow-lg max-w-44"
							src={getImageUrl("Compass_Android.png")}
							alt="compass app screenshot"
							width={512} height={512}
						/>
						<p>Compass is a simple Android app that shows the user&#39;s
							direction
							using the device&#39;s built-in sensors. It follows the Material 3
							design system for a clean and modern look.</p>
						<br />
						<p>I made this app because I wanted a quick and easy way to access
							a
							compass without opening Google Maps. It’s meant to be
							lightweight
							and simple, with a clear interface that does one thing well.</p>
						<br />
						<p>This project allowed me to explore lower-level drawing APIs in
							Jetpack Compose, handle real-time sensor data from the
							magnetometer
							and accelerometer, and apply linear algebra concepts to
							determine
							accurate device orientation.</p>
						<br />
						<p>Overall, building a compass app was a great project. It allowed
							me to
							use low level
							Android features, like the accelerometer, and magnetometer. How
							they can
							link in with more UI oriented constructs</p>
					</ProjectSection>
				</Section>
			</SectionedContent>
		</div>
	);
}
