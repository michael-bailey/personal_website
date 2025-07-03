import {ReactElement} from "react";


interface FooterbarProps {
	className?: string
}

export default async function FooterBar({className}: FooterbarProps): Promise<ReactElement> {
	return (
		<footer className={"flex flex-row place-items-center justify-between p-4 min-h-16 " + (className ?? "")}>
			<h2 className="block">© 2025 Michael Bailey — All rights reserved.</h2>
			<span className="block">Created using open source libraries.</span>
		</footer>
	)
}