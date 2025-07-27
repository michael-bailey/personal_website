

import {ReactElement} from "react";
import {getApiBase} from "@/lib/environment";

export default async function PrivacyCookieToggles(): Promise<ReactElement> {

	const privacyPreferences = await fetch(`${getApiBase()}/api/analytics`).then(res => res.json())

	return <>
		<h2 className="text-xl">Request Logging</h2>
		<p>example: {privacyPreferences["request"]}</p>

		<h2 className="text-xl">Request Logging</h2>
		<p>example: {privacyPreferences["request"]}</p>
	</>;
}