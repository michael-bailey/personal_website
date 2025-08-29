'use client'

import {ReactElement, useCallback} from "react";
import {gql, useMutation, useQuery} from "@apollo/client";
import Link from "next/link";
import {
	CookieShownQuery,
	DisableCookiePopupMutation, SelectCookieResult
} from "@/__generated__/graphql";

const cookiePopupShownQuery = gql`
    query CookieShown {
        viewer {
            id
            privacyPreferences {
                id
                cookiePromptDismissed
                allowedDomainLogging
                allowedRequestLogging
            }
        }
    }
`

const disableCookiePopupMutation = gql`
    mutation DisableCookiePopup($cookiesEnabled: SelectCookieResult!) {
        selectCookiePreferences(result: $cookiesEnabled) {
            id
            cookiePromptDismissed
            allowedDomainLogging
            allowedRequestLogging
        }
    }
`

export default function CookiePopup(): ReactElement | null {

	const {loading, data} = useQuery<CookieShownQuery>(cookiePopupShownQuery);
	const [disablePopup] = useMutation<DisableCookiePopupMutation>(disableCookiePopupMutation, {
		refetchQueries: [
			cookiePopupShownQuery
		]
	});
	
	const enableCookies = useCallback(async () => {
		console.log("enabling cookies")
		const a = await disablePopup({
			variables: {cookiesEnabled: SelectCookieResult.Accept},
		})
		console.log(a)
	}, [disablePopup])

	const disableCookies = useCallback(async () => {
		console.log("disabling cookies")
		const a = await disablePopup({
			variables: {cookiesEnabled: SelectCookieResult.Deny},
		})
		console.log(a)
	}, [disablePopup])

	if (loading || data === undefined || data.viewer?.privacyPreferences?.cookiePromptDismissed) {
		return null
	}

	return <div className="fixed bottom-4 right-4 p-4">
		<div
			className="block border border-gray-300 bg-white rounded-2xl shadow-lg p-4 max-w-sm">
			<h1 className="text-lg font-semibold mb-2">Help Improve My Site</h1>
			<p className="text-sm mb-2">
				This website only uses two cookies:
			</p>
			<ul className="list-disc list-inside text-sm mb-3">
				<li>
					<strong>Session ID cookie</strong> – used only for my admin login.
				</li>
				<li>
					<strong>Privacy preference cookie</strong> – stores whether you allow
					analytics (disabled by default).
				</li>
			</ul>
			<p className="text-sm mb-4">
				Would you mind enabling these cookies? They really help me debug and
				improve the site.
			</p>
			<div className="flex gap-2">
				<button
					className="px-3 py-1 bg-blue-600 text-white rounded hover:bg-blue-700"
					onClick={enableCookies}
				>
					Enable Cookies
				</button>
				<Link
					href="/settings"
					className="text-center px-3 py-1 bg-gray-100 text-gray-600 rounded hover:bg-gray-200"
				>
					More Options
				</Link>
				<button
					className="px-3 py-1 bg-gray-200 text-gray-700 rounded hover:bg-gray-300"
					onClick={disableCookies}
				>
					Keep Disabled
				</button>
			</div>
		</div>
	</div>
}