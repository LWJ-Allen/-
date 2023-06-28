import { createRouter, createWebHistory } from "vue-router";

/* 配置路由，切换页面 */
const routes = [
    {
        path: '/',
        redirect: '/login',
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/index.vue'),
    }
];

/* 创建路由实例 */
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;