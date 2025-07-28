'use client'

import {ReactElement, useCallback, useEffect, useMemo, useState} from "react";
import {gql, useLazyQuery} from "@apollo/client";
import {PrivacyPreferencesQuery} from "@/__generated__/graphql";

const privacyPreferencesQuery = gql`
    query PrivacyPreferences {
        viewer {
            privacyPreferences {
                allowedDomainLogging
                allowedRequestLogging
            }
        }
    }
`

export default function PrivacyCookieToggles(): ReactElement {

	const [launchQuery, { called, loading, data: queryData }] = useLazyQuery<PrivacyPreferencesQuery>(privacyPreferencesQuery)

	const [analytics, setAnalytics] = useState({
		domain: null,
		request: null,
	})

	const fetchCookies = useCallback(async () => {
		const res = await fetch(`/api/analytics`)
			.then(res => res.json())

		setAnalytics({
			domain: res["domain"],
			request: res["request"]
		})

	}, [setAnalytics])


	const requestEnabled = useMemo(() => analytics.request !== "null", [analytics.request])

	const toggleDomain = useCallback(async () => {
		const url = `/api/analytics/preferences?allowDomainLogging=${!queryData?.viewer?.privacyPreferences?.allowedDomainLogging}`
		const res = await fetch(url, {method: "POST"})
		const data = await res.json()
		if (data == true) {
			await fetchCookies()
			await launchQuery()
		}

	}, [queryData?.viewer?.privacyPreferences?.allowedDomainLogging, fetchCookies, launchQuery])

	const toggleRequest = useCallback(async () => {
		const url = `/api/analytics/preferences?allowRequestLogging=${!requestEnabled}`
		const res = await fetch(url, {method: "POST"})
		const data = await res.json()
		if (data == true) {
			await fetchCookies()
			await launchQuery()
		}

	}, [fetchCookies, launchQuery, requestEnabled])

	useEffect(() => {
		launchQuery()
		fetchCookies()
	}, [fetchCookies, launchQuery])

	return <>
		<h2 className="text-xl">Domain Logging</h2>
		<p>example: {analytics.domain}</p>
		<button onClick={toggleDomain}>Toggle Domain</button>
		<br />
		<h2 className="text-xl">Request Logging</h2>
		<p>example: {analytics.request}</p>
		<button onClick={toggleRequest}>Toggle Request</button>
	</>;
}