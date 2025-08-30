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
			className={"grid w-full h-full bg-none " + className}>

			<div
				className="grid grid-cols-1 gap-0.25 rounded-md bg-none auto-rows-auto ">

				<SideBarHeader text="Pages" />
				<SideBarLink isActive={path === "/"} href="/" text="About Me" />
				<SideBarLink
					isActive={path === "/settings"} href="/settings"
					text="Settings" />
				<SideBarLink
					isActive={path === "/wip"} href="/wip" text="Work In Progress" />
				<SideBarLink
					isActive={path === "/experimental"} href="/experimental"
					text="Experiments" />
			</div>


		</aside>
	);
}