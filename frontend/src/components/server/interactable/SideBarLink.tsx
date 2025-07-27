"use client"

import Link from "next/link";
import {usePathname} from "next/navigation";

interface SideBarLinkProps {
	href: string,
	text: string,
	isActive: boolean,
}

export default function SideBarLink({
	text,
	href,
}: SideBarLinkProps) {

	const path = usePathname()

	const selectedClasses = path === href ? "light:bg-gray-200 dark:bg-gray-800" : "bg-none"

	return <Link prefetch={false}
		className={` ${selectedClasses} p-2 block w-full`}
		href={href}>{text}</Link>;
}