import {ReactElement} from "react";
import {SideBarHeader} from "@/components/server/typography/SideBarHeader";
import SideBarLink from "@/components/server/interactable/SideBarLink";
import {headers} from "next/headers";

interface SideBarProps {
	className?: string
}

export default async function StaticSideBar({className}: SideBarProps): Promise<ReactElement> {

	const nextHeaders = await headers()

	const path = nextHeaders.get("x-pathname")

	return (
		<aside
			className={"grid grid-rows-[] w-full h-full " + className}>

			<div
				className="grid grid-cols-1 gap-0.25 rounded-md bg-gray-200 auto-rows-auto ">

				<SideBarHeader text="Pages" />
				<SideBarLink isActive={path === "/"} href="/" text="About Me" />
				<SideBarLink
					isActive={path === "/wip"} href="/wip" text="Work In Progress" />
				<SideBarLink
					isActive={path === "/experimental"} href="/experimental"
					text="Experiments" />
			</div>


		</aside>
	);
}