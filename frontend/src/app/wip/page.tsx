import {ReactElement} from "react";
import SectionedContent from "@/components/server/SectionedContent";
import Section from "@/components/server/Section";
import WorkInProgressWarning from "@/components/server/WorkInProgressWarning";
import SelfHostingSection from "@/components/server/SelfHostingSection";
import StateTest from "@/components/client/StateTest";


export default async function Page(): Promise<ReactElement> {
	return (
		<SectionedContent>
			<Section>
				<WorkInProgressWarning />
			</Section>

			<Section>
				<div className="h-fit inline-block">
					<h2 className="text-3xl pb-3">Self hosting</h2>
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
					<h2 className="text-3xl pb-3">State Testing</h2>
					<p className="text-justify">
						Here’s a selection for state testing
					</p>
				</div>

				<StateTest />

			</Section>

		</SectionedContent>
	)
}