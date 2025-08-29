import {ReactElement} from "react";
import SectionedContent from "@/components/server/SectionedContent";
import Section from "@/components/server/Section";
import TestButton from "@/components/client/TestButton";

export default async function Page(): Promise<ReactElement> {

	return (
		<SectionedContent>
			<Section>
				Api request:
				<TestButton />
			</Section>
			<Section>
				<ol>
					Not sure what was here
				</ol>
			</Section>
		</SectionedContent>
	)
}