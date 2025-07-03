"use client"

import {useCallback, useState} from "react";

export default function TestButton() {

	const [text, setText] = useState("Not loaded")

	const call = useCallback(() => {
		fetch("/api/test").then(res => res.text()).then(setText)
	}, [])

	return (
		<button className="bg-cyan-200 rounded-2xl mx-4 p-2" onClick={call}>{text}</button>
	)
}