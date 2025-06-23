import {ReactElement} from "react";
import Link from "next/link";


interface HeaderBarProps {
	className?: string
}

export default async function HeaderBar({className}: HeaderBarProps): Promise<ReactElement> {
	return (
		<header className={"grid grid-cols-1 grid-rows-3 sm:grid-cols-3 sm:grid-rows-1 h-min md:h-16 gap-2 sm:gap-8 px-4 " + (className ?? "")}>
			<div className="flex-none content-center justify-self-center sm:justify-self-start text-2xl">Michael Bailey</div>
			<div className="flex-1 content-center justify-self-center max-w-min">

				<nav className="flex flex-row gap-4 border-1 rounded-3xl border-gray-300 px-4 py-2">
					<Link href="/">About</Link>
					<Link href="/projects">Projects</Link>
					<Link href="/BIT">BIT</Link>
				</nav>
			</div>

			<div className="flex-none content-center justify-self-center md:justify-self-end">Login</div>
		</header>
	)
}