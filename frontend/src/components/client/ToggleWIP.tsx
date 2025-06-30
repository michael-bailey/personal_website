"use client"

import {useAppDispatch} from "@/lib/reducers/hooks";
import {toggleWIP} from "@/lib/reducers/pageDisplayState";

export default function ToggleWIP() {

	const dispatch = useAppDispatch()

	return (
		<button
			className="flex-none content-center justify-self-center md:justify-self-end"
			onClick={() => dispatch(toggleWIP()) }>
			Show WIP
		</button>
	)
}