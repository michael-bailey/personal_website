import {ReactElement} from "react";


interface FooterbarProps {
	className?: string
}

export default async function FooterBar({className}: FooterbarProps): Promise<ReactElement> {
	return (
		<footer className={"flex flex-row justify-between p-4 " + (className ?? "")}>
			<h2 className="place-self-start text-base">© 2025 Michael Bailey — All rights reserved.</h2>
			<span className="place-self-end">Created using open source libraries.</span>
		</footer>
	)
}