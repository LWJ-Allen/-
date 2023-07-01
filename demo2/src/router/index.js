import { createRouter, createWebHistory } from "vue-router";

/* 配置路由，切换页面 */
const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/home",
    name: "home",
    component: () => import("@/views/home/index.vue"),
  },
  {
    path: "/loginRegister",
    name: "loginRegister",
    component: () => import("@/views/loginRegister/index.vue"),
  },
];

/* 创建路由实例 */
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
