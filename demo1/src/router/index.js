// 使用历史路由
import { createRouter, createWebHistory } from "vue-router";

// 配置路由
const routes = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/detail",
    name: "detail",
    component: () => import("@/views/detail/index.vue"),
  },
  {
    path: "/home",
    name: "home",
    component: () => import("@/views/home/index.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("@/views/login/index.vue"),
  },
  {
    path: "/register",
    name: "register",
    component: () => import("@/views/register/index.vue"),
  },
];

// 创建路由实例
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
});

export default router;
