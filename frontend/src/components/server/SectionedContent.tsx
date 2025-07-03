import React, {ReactElement} from "react";
import Section from "@/components/server/Section";
import SectionSpacer from "@/components/server/SectionSpacer";


interface SectionedContentProps {
	children: ReactElement<typeof Section>[];
}

export default function SectionedContent({children}: SectionedContentProps) {
	return (
		<>
			{children.map((child, index) => (
				<React.Fragment key={index}>
					{child}
					{index < children.length - 1 && <SectionSpacer className="h-8" />}
				</React.Fragment>
			))}
		</>
	)
}