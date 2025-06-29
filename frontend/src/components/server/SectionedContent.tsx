import React, {ReactElement} from "react";
import SectionSpacer from "@/components/server/SectionSpacer";
import Section from "@/components/server/Section";


interface SectionedContentProps {
	children: ReactElement<typeof Section>[];
}

export default function SectionedContent({children}: SectionedContentProps) {
	return (
		<>
			{children?.map((child, index) => <div  key={index}>
				{child}
				{index != (children.length-1) ? (<SectionSpacer className="h-8" />) : null}
			</div>)}
		</>
	)
}