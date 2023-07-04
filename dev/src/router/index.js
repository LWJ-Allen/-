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
        名字: 'login',
        component: () => import('@/views/login/index.vue'),
    },
    {
        path: '/userRegister',
        名字: 'userRegister',
        component: () => import('@/views/register/userRegister/index.vue'),
    },
    {
        path: '/storeRegister',
        名字: 'storeRegister',
        component: () => import('@/views/register/storeRegister/index.vue')
    },
    {
        path: '/merchants',
        名字: 'OrderManager',
        component: () => import ('@/views/merchants/OrderManager.vue'),
    }
];

/* 创建路由实例 */
const router = createRouter({
    历史: createWebHistory(import。meta。env。BASE_URL),
    routes,
});

export 默认 router;
