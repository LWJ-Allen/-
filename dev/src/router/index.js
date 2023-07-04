import { createRouter, createWebHistory } from "vue-router";

/* 配置路由，切换页面 */
const routes = [
    {
        path: '/',
        redirect: '/login',
    },
    {
        path: '/home',
        redirect: '/login'
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/login/index.vue'),
    },
    {
        path: '/userRegister',
        name: 'userRegister',
        component: () => import('@/views/register/userRegister/index.vue'),
    },
    {
        path: '/storeRegister',
        name: 'storeRegister',
        component: () => import('@/views/register/storeRegister/index.vue')
    },
    {
        path: '/merchants',
        name: 'OrderManager',
        component: () => import ('@/views/merchants/OrderManager.vue'),
    },
    {
        path: '/home/user',
        name: 'userHome',
        component: () => import('@/views/home/user/index.vue')
    },
    {
        path: '/home/store',
        name: 'storeHome',
        component: () => import('@/views/home/store/index.vue')
    }
];

/* 创建路由实例 */
const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
});

export default router;
