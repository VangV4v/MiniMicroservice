import { createSlice } from "@reduxjs/toolkit";
import { RootState } from "../store";

interface CounterModel {
    value: number
}

const initialState: CounterModel = {
    value: 0
};

const counterSlice = createSlice({
    name: 'counter',
    initialState,
    reducers: {
        increment: (action) => {
            action.value += 1;
        }
    }
});

const { actions, reducer } = counterSlice;
export const { increment } = actions;
export const selectCount = (state: RootState) => state.counter.value
export default reducer;