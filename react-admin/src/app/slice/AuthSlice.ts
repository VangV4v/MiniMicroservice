import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import LoginModel from "../../models/LoginModel";
import authAdminApi from "../../api/AuthAdminApi";
import { RootState } from "../store";

export const authAdmin = createAsyncThunk(
    'auth/loginAdmin',
    async (model: LoginModel) => {
        const response = await authAdminApi.login(model);
        localStorage.setItem('jwt', response.data);
        return { jwt: response.data };
    }
)

interface AuthSliceModel {
    jwt: string,
    authentication: boolean
}
const initialState: AuthSliceModel = {
    jwt: localStorage.getItem('jwt') || '',
    authentication: false
}

const authAdminSlice = createSlice({
    name: 'auth',
    initialState,
    reducers: {

    },
    extraReducers: (builder) => {
        builder.addCase(authAdmin.fulfilled, (state, action) => {
            state.jwt = action.payload.jwt;
            state.authentication = true;
        })
        // ,
        //     builder.addCase(authAdmin.rejected, (state, action) => {
        //         console.log(state);
        //         console.log(action);
        //     })
    }
});

const { reducer } = authAdminSlice;
export const selectJwt = (state: RootState) => state.auth.jwt
export const selectAuthentication = (state: RootState) => state.auth.authentication
export default reducer;