"use client"

import {useAppSelector} from "@/lib/reducers/hooks";

export default function WorkInProgressStuff() {
	const isShown = useAppSelector((state) => state.pageDisplayState.isWIPShown)

	return isShown === undefined || isShown === false ? null : (
		<>

		</>
	)
}