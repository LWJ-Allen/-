// 初始化vue示例
import { createApp } from 'vue'
import App from './App.vue'

// 导入全局css
import '@/assets/styles/index.scss'

// 导入路由对象
import router from './router'

// 导入element-plus 及 所有的icon图标
import element from 'element-plus';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import 'element-plus/dist/index.css';

const app = createApp(App);
app.use(router);
app.use(element);
app.mount('#app');

// 导入路由配置
router.beforeEach((to, from, next) => {
    if (to.path === '/home') {
        window.parent.postMessage({ ifJump: false }, '*')
        next()
    } else {
        window.parent.postMessage({ ifJump: true }, '*')
        next()
    }
})
