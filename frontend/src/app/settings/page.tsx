import SectionedContent from "@/components/server/SectionedContent";
import Section from "@/components/server/Section";
import PrivacyCookieToggles from "@/app/settings/PrivacyCookieToggles";



export default async function SettingsPage() {

	return <SectionedContent>
		<Section>
			<h1 className="text-4xl">Settings</h1>
			<p>Here are a bunch of site settings, that are available for you to
				change.</p>
		</Section>
		<Section>
			<h1 className="text-2xl">Privacy</h1>
			<p>Here are settings to adjust your analytics sharing preferences.</p>
			<p>I would appreciate you have these on, but fully understand why you
				wouldn't want to.</p>
			<br />

			<PrivacyCookieToggles />

		</Section>
	</SectionedContent>
}