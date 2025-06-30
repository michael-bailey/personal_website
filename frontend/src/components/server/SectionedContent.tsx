import React, {ReactElement} from "react";
import SectionSpacer from "@/components/server/SectionSpacer";
import Section from "@/components/server/Section";


interface SectionedContentProps {
	children: ReactElement<typeof Section>[];
}

export default function SectionedContent({children}: SectionedContentProps) {
	return (
		<>
			{children}
		</>
	)
}