import {ReactElement} from "react";
import SectionedContent from "@/components/server/SectionedContent";
import Section from "@/components/server/Section";
import TestButton from "@/components/client/TestButton";
import {query} from "@/lib/graphql/serverClient";
import {gql} from "@apollo/client";
import Image from "next/image";

const a = gql`
    query GraphQLQuery {
        getUserAvatar
    }
`


export default async function Page(): Promise<ReactElement> {

	const testData = await query({ query: a })
		.then(res => res.data.getUserAvatar)
		.then(res => <Image src={res} alt="profile image" width={512} height={512} />)

	return (
		<SectionedContent>
			<Section>
				<div>
					WIP - page about British-Information-Technologies.
				</div>
			</Section>
			<Section>
				Api request:
				<TestButton />
			</Section>
			<Section>
				<ol>
					{testData}
				</ol>
			</Section>
		</SectionedContent>
	)
}