import axios from "axios";

const axiosClient = axios.create({
    baseURL: 'http://localhost:9000/'
});

export default axiosClient;