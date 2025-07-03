"use client"

import Section from "@/components/server/Section";
import WorkInProgressWarning from "@/components/server/WorkInProgressWarning";
import SelfHostingSection from "@/components/server/SelfHostingSection";
import StateTest from "@/components/client/StateTest";
import {useAppSelector} from "@/lib/reducers/hooks";

export default function WorkInProgressStuff() {
	const isShown = useAppSelector((state) => state.pageDisplayState.isWIPShown)

	return isShown === undefined || isShown === false ? null : (
		<>

		</>
	)
}