import React, {ReactElement} from "react";
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