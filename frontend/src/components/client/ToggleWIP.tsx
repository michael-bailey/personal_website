"use client"

import {useAppDispatch} from "@/lib/reducers/hooks";
import {toggleWIP} from "@/lib/reducers/pageDisplayState";

export default function ToggleWIP() {

	const dispatch = useAppDispatch()

	return (
		<button
			onClick={() => dispatch(toggleWIP()) }>
			Show WIP
		</button>
	)
}