import LoginModel from "../models/LoginModel";
import axiosClient from "./AxiosClient";

const authAdminApi = {
    login(model: LoginModel) {
        return axiosClient.post('/api/v1/auth/admin', model);
    }
};

export default authAdminApi;