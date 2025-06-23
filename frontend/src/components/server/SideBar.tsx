import {ReactElement} from "react";
import Link from "next/link";

export default function SideBar(): ReactElement {
	return (
		<aside className="bg-gray-900 text-white w-64 p-4 space-y-4 hidden md:block fixed inset-y-0 left-0 z-40 md:relative">
			<h2 className="text-xl font-bold mb-4">My App</h2>
			<nav className="space-y-2">
				<Link href="/" className="block px-3 py-2 rounded hover:bg-gray-800">Dashboard</Link>
				<Link href="/settings" className="block px-3 py-2 rounded hover:bg-gray-800">Settings</Link>
				<Link href="/profile" className="block px-3 py-2 rounded hover:bg-gray-800">Profile</Link>
				<Link href="/logout" className="block px-3 py-2 rounded hover:bg-gray-800">Logout</Link>
			</nav>
		</aside>
	);
}