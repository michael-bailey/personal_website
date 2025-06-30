import {createSlice} from "@reduxjs/toolkit";
import {RootState} from "@/lib/reducers/store";

interface PageDisplayState {
	isSidebarShown: boolean;
	isWIPShown: boolean
}

const initialState: PageDisplayState = {
	isSidebarShown: false,
	isWIPShown: false,
}

const pageDisplayStateSlice = createSlice({
	name: 'pageDisplayState',
	initialState,
	reducers: {
		toggleSidebar: (state) => {state.isSidebarShown = !state.isSidebarShown;},
		toggleWIP: (state) => {state.isWIPShown = !state.isWIPShown;},
	}
})

export const { toggleSidebar, toggleWIP } = pageDisplayStateSlice.actions;
export const selectPageDisplayState = (state: RootState) => state.pageDisplayState;
export default pageDisplayStateSlice.reducer;