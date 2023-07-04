import request from "@/utils/request.js";

export const queryStore = () => {
    return request(
        {
            url: '/query/store',
            method: 'get'
        }
    )
}

