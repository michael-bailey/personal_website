import {ReactElement} from "react";
import {ReactChild} from "@/lib/ReactChild";

interface ProjectSectionProps {
	projectName?: string,
	githubUrl?: string,
	children: ReactChild<ReactElement<HTMLImageElement | HTMLParagraphElement | HTMLBRElement>>
}

export default function ProjectSection({
	projectName,
	githubUrl,
	children
}: ProjectSectionProps) {
	return (
		<div className="h-fit inline-block mt-4 pt-4 border-t-1 border-t-gray-300">
			<h3 className="text-2xl">{projectName}</h3>
			<h4 className="h6 mt-1 mb-4">
				<a
					href={githubUrl}
					className="decoration-0 text-gray-500">
					{githubUrl}
				</a>
			</h4>
			{children}
		</div>
	)
}