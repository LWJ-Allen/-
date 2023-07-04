import request from "@/utils/request.js";

export const queryStore = () => {
    return request(
        {
            url: '/query/store',
            method: 'get'
        }
    )
}

export const queryMenu = (params) => {
    return request(
        {
            url: "/query/menu",
            method: 'get',
            params
        }
    )
}

export const order = (params) => {
    return request(
        {
            url: "order/add",
            method: "post",
            params
        }
    )
}

