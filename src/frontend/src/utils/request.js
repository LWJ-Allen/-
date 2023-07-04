import axios from 'axios'

const BASEURL = import.meta.env.MODE === "production" ? "/version" : "/dev-api"

const service = axios.create({
    baseURL: BASEURL,
    timeout: 5000,
});

service.interceptors.request.use(
    config => {
        return getSign(config)
    },
    error => Promise.reject(error)
)

service.interceptors.response.use(
    config => config,
    error => Promise.reject(error)
)

/* 加密函数 */
const getSign = config => config

export default service


