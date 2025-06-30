
import { configureStore } from '@reduxjs/toolkit'
import counterSlice from "@/lib/reducers/counterSlice";
import pageDisplayState from "@/lib/reducers/pageDisplayState";

export const makeStore = () => configureStore({
	reducer: {
		counter: counterSlice,
		pageDisplayState: pageDisplayState,
	}
})

export type AppStore = ReturnType<typeof makeStore>
export type RootState = ReturnType<AppStore['getState']>
export type AppDispatch = AppStore['dispatch']