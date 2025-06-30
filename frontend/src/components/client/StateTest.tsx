"use client"

import {useAppDispatch, useAppSelector} from "@/lib/reducers/hooks";
import {decrement, increment} from "@/lib/reducers/counterSlice";

export default function StateTest() {
	const count = useAppSelector(state =>  state.counter);
	const dispatch = useAppDispatch();

	return (
		<>
			<div>current count: {count}</div>
			<button className="block bg-amber-500 p-2 h-8" onClick={() => dispatch(increment())} >Increment</button>
			<button className="block bg-amber-500 p-2 h-8" onClick={() => dispatch(decrement())} >Decrement</button>
		</>
	)
}