import {ReactChild} from "@/lib/ReactChild";
import {ReactElement} from "react";

interface ProjectSectionProps {
	title?: string,
	children: ReactChild<ReactElement>
}

export default function SelfHostingSection({
	title,
	children
}: ProjectSectionProps) {
	const childrenArr = children instanceof Array ? children : [children];

	return (
		<div
			className="h-fit w-full inline-block pt-4 border-t-1 border-t-gray-300">
			<h3 className="text-2xl pb-4">{title}</h3>
			{childrenArr.map((item, index) => {
				return (<div key={index}>
					{item}
					{index != (childrenArr.length - 1) ? <br></br> : null}
				</div>)
			})}
		</div>
	)
}