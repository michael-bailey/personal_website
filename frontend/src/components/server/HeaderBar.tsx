import {ReactElement} from "react";
import PageBreadCrumbs from "@/components/client/PageBreadCrumbs";

interface HeaderBarProps {
	className?: string
}

export default async function HeaderBar({className}: HeaderBarProps): Promise<ReactElement> {
	return (
		<header className={"flex flex-row place-items-center justify-between p-4 " + (className ?? "")}>
			<PageBreadCrumbs />
		</header>
	)
}