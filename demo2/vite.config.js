import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'
import * as path from 'path'

export default defineConfig(({ mode }) => {
    const env = loadEnv(mode, process.cwd())
    return {
        plugins: [
            vue()
        ],
        base: env.VITE_APP_ENV === 'production' ? '/dev/' : './',
        envDir: path.resolve(__dirname, './env'),
        build: {
            publicPath: './',
            outputDir: 'dist',
            indexPath: 'index.html',
            lintOnSave: false,
            transpileDependencies: true
        },
        resolve: {
            // https://cn.vitejs.dev/config/#resolve-alias
            alias: {
                // 设置路径
                '~': path.resolve(__dirname, './'),
                // 设置别名
                '@': path.resolve(__dirname, './src')
            },
            // https://cn.vitejs.dev/config/#resolve-extensions
            extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
        },
        css: {
            preprocessorOptions: {
                scss: {
                    additionalData: '@import "./src/assets/styles/mixin.scss";' // 此处全局的scss文件
                }
            }
        },
        server: {
            // 是否主动唤醒浏览器
            open: true,
            // 占用端口
            port: 8080,
            hmr: true,
            // 是否使用https请求
            https: false,
            // 扩展访问端口
            // host: settings.host,
            proxy: {
                '/dev-api': {
                    target: 'https://api.gugudata.com',
                    changeOrigin: true,
                    rewrite: p => p.replace(/^\/dev-api/, '')
                }
            }
        }
    }
})