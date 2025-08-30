interface SideBarHeaderProps {
	text?: string
}

export const SideBarHeader = ({text}: SideBarHeaderProps) => <h1
	className="bg-none text-lg p-2 dark:border-b-1  ">{text}</h1>