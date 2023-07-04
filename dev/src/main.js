/* 导入App组件和createApp */
import { createApp } from 'vue'
import App from './App.vue'

/* 导入路由对象 */
import router from './router'

/* 导入element-plus */
import element from 'element-plus'
import 'element-plus/dist/index.css'

/* 导入全局css */
import '@/assets/styles/index.scss'
import {getLocalData} from "@/utils/session.js";

/* 创建app实例 */
const app = createApp(App)

app.use(router)
app.use(element)
/* 挂载 */
app.mount('#app')


/* 路由守卫 */
/*
router.beforeEach((to, from, next) => {
    const isLogin = getLocalData("userData") !== null || getLocalData("storeData") !== null;
    if(to.path === '/home' && !isLogin) {
        next({"path": "/login"})
    }
    else {
        next()
    }
})*/
