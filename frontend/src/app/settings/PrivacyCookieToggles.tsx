'use client'

import {ReactElement, useCallback, useEffect, useMemo, useState} from "react";

export default function PrivacyCookieToggles(): ReactElement {

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

	const domainEnabled = useMemo(() => analytics.domain !== "null", [analytics.domain])
	const requestEnabled = useMemo(() => analytics.request !== "null", [analytics.request])

	const toggleDomain = useCallback(async () => {
		const url = `/api/analytics/preferences?allowDomainLogging=${!domainEnabled}`
		const res = await fetch(url, {method: "POST"})
		const data = await res.json()
		if (data == true) await fetchCookies()
	}, [domainEnabled, fetchCookies])

	const toggleRequest = useCallback(async () => {
		const url = `/api/analytics/preferences?allowRequestLogging=${!requestEnabled}`
		const res = await fetch(url, {method: "POST"})
		const data = await res.json()
		if (data == true) await fetchCookies()
	}, [fetchCookies, requestEnabled])

	useEffect(() => {
		fetchCookies()
	}, [fetchCookies])

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