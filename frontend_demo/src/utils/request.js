import axios from "axios";

const BASEURL = import.meta.env.MODE === "production" ? "/version" : "/dev-api";

const service = axios.create({
  baseURL: BASEURL,
  timeout: 5000,
});

// 请求拦截器
service.interceptors.request.use(
  (config) => {
    return getSign(config);
  },
  (error) => Promise.reject(error)
);

// 响应拦截器
service.interceptors.response.use(
  (config) => config,
  (error) => Promise.reject(error)
);

// 加密函数
function getSign(config) {
  return config;
}

export default service;
