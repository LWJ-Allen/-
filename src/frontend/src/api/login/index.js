import request from "@/utils/request.js";

export const login = (params) => {
    return request({
        url: "/login",
        method: "post",
        params
    })
}