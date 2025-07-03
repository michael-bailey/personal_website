"use client"

import Link from "next/link";
import { usePathname } from "next/navigation";

export default function PageBreadCrumbs() {
	const pathname = usePathname();

	const pathSegments = pathname.split("/").filter(Boolean);

	const breadcrumbs = pathSegments.map((segment, index) => {
		const href = "/" + pathSegments.slice(0, index + 1).join("/");
		const label = decodeURIComponent(segment)
			.replace(/-/g, " ")
			.replace(/\b\w/g, (char) => char.toUpperCase());

		return (
			<span key={href} className="flex items-center">
				<span className="mx-1 text-gray-400">{">"}</span>
				<Link href={href} className="text-blue-600 hover:underline">
					{label}
				</Link>
			</span>
		);
	});

	return (
		<nav aria-label="Breadcrumb">
			<div className="flex items-center text-sm text-gray-700">
				<Link href="/" className="text-blue-600 hover:underline">
					Home
				</Link>
				{breadcrumbs}
			</div>
		</nav>
	);
}