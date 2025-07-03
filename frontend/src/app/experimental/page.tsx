import {ReactElement} from "react";
import SectionedContent from "@/components/server/SectionedContent";
import Section from "@/components/server/Section";
import TestButton from "@/components/client/TestButton";


export default async function Page(): Promise<ReactElement> {

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
		</SectionedContent>
	)
}