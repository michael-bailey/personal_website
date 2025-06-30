import React, {ReactNode} from "react";
import {ReactChild} from "@/lib/ReactChild";
import SectionSpacer from "@/components/server/SectionSpacer";


interface SectionProps {
	children: ReactChild<ReactNode>;
}

export default function Section({children}: SectionProps) {
	return (
		<>
			<section className="text-justify p-4">
				{children}
			</section>
			<SectionSpacer className="h-8" />
		</>
	)
}