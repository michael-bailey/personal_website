import {ReactElement} from "react";

interface SideBarProps {
	className?: string
}

export default function SideBar({className}: SideBarProps): ReactElement {
	return (
		<aside
			className={"bg-gray-900 text-white w-64 p-4 space-y-4 hidden md:block fixed inset-y-0 left-0 z-40 md:relative " + className}>
			<h2>Michael Bailey</h2>
		</aside>
	);
}