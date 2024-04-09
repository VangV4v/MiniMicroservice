import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import LoginModel from "../../models/LoginModel";
import authAdminApi from "../../api/AuthAdminApi";
import { RootState } from "../store";

export const authAdmin = createAsyncThunk(
    'auth/loginAdmin',
    async (model: LoginModel) => {
        const response = await authAdminApi.login(model);
        return { jwt: response.data };
    }
)

interface AuthSliceModel {
    jwt: string
}

const initialState: AuthSliceModel = {
    jwt: localStorage.getItem('jwt') || ''
}

const authAdminSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(authAdmin.fulfilled, (state, action) => {
            state.jwt = action.payload.jwt;
        })
        // ,
        //     builder.addCase(authAdmin.rejected, (state, action) => {
        //         console.log(state);
        //         console.log(action);
        //     })
    }
});

const { reducer } = authAdminSlice;
export const selectCount = (state: RootState) => state.auth.jwt
export default reducer;