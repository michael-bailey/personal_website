"use client"

import React, {ReactElement, useState} from "react";

type SideBarToggleProps = {
	children: React.ReactNode
}

export default function SideBarToggle({ children }: SideBarToggleProps): ReactElement {
	const [isOpen, setIsOpen] = useState(false);

	return (
		<>
			<button
				className="md:hidden p-4 text-gray-800"
				onClick={() => setIsOpen(true)}
			>
				Open
			</button>

			{/* Overlay */}
			{isOpen && (
				<div className="fixed inset-0 bg-black bg-opacity-40 z-40" onClick={() => setIsOpen(false)} />
			)}

			{/* Mobile Sidebar */}
			<aside
				className={`bg-gray-900 text-white w-64 p-4 space-y-4 fixed inset-y-0 left-0 z-50 transform transition-transform duration-300 md:hidden ${
					isOpen ? "translate-x-0" : "-translate-x-full"
				}`}
			>
				<div className="flex justify-between items-center mb-4">
					<h2 className="text-xl font-bold">My App</h2>
					<button onClick={() => setIsOpen(false)}>
						Close
					</button>
				</div>
				{children}
			</aside>
		</>
	);
}