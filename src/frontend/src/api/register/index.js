import request from '@/utils/request.js'

export const userRegister = (params) => {
    return request({
        url: "/register/user",
        method: "POST",
        params
    })
}

export const storeRegister = (params) => {
    return request({
        url: "/register/store",
        method: "POST",
        params
    })
}