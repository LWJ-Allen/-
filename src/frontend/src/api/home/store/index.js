import request from "@/utils/request.js";

export const queryMenu = (params) => {
    return request(
        {
            url: "/query/menu",
            method: 'get',
            params
        }
    )
}

export const queryOrder = (params) => {
    return request(
        {
            url: "/order/storequery",
            method: 'post',
            params
        }
    )
}

export const addFood = (params) => {
    return request(
        {
            url: '/food/add',
            method: 'post',
            params
        }
    )
}