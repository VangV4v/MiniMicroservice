import HeaderRequest from "../models/HeaderRequest";
import axiosClient from "./AxiosClient";

const customerApi = {
    getAlls(param: HeaderRequest) {
        return axiosClient.get('/api/v1/customers/', {
            headers: {
                Authorization: 'Bearer ' + param.authorization
            }
        })
    }
};

export default customerApi;