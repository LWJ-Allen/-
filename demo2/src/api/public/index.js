import request from "../../utils/request.js";

export const getJoke = (params) => {
  return request({
    url: "/news/joke/demo",
    method:'get',
    params // 等于params:params es6 key value 一致省略value
  });
};
