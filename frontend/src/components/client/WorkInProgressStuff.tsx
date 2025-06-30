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
			<Section>
				<WorkInProgressWarning />
			</Section>

			<Section>
				<div className="h-fit inline-block">
					<h2 className="text-3xl mb-3">Self hosting</h2>
					<p className="text-justify">
						Here’s a selection of how I self host my projects. This includes
						hardware, operating systems, architecture, software and
						management.
						Whilst I am new to self hosting,
					</p>
				</div>

				<SelfHostingSection title="Raspberry Pi">
					<p>Raspberry Pis are excellent little single board computers.</p>

				</SelfHostingSection>

				<SelfHostingSection title="Provisiond">
					<p>Provisiond is a service provisioning and management tool. it is a
						custom built tool, to pair with GitHub Workflows. Providing ways
						to remotely update services over SSH</p>

				</SelfHostingSection>

			</Section>

			<Section>
				<div className="h-fit inline-block">
					<h2 className="text-3xl mb-3">State Testing</h2>
					<p className="text-justify">
						Here’s a selection for state testing
					</p>
				</div>

				<StateTest />

			</Section>
		</>
	)
}