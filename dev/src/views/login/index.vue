<template>
    <el-container>
        <el-header height="100px"></el-header>
        <el-main>
            <el-container style="align-items: center">
                <el-header height="120px">
                    <el-container>
                        <el-image src="/icon.svg" style="width: 100px; height: 100px"></el-image>
                        <el-text style="letter-spacing: 5px; font-size: 30px; margin-left: 20px">云食坊~</el-text>
                    </el-container>
                </el-header>
                <el-main>
                    <el-form :model="form" id="form">
                        <!-- 商家checkbox -->
                        <el-form-item style="margin: 20px auto auto 25px;">
                            <el-checkbox class="checkbox" v-model="form.isStore" label="商家" border></el-checkbox>
                        </el-form-item>
                        <!-- 账号输入 -->
                        <el-tooltip :visible="accTipVisibility" placement="right" :content="accTipContent">
                            <el-form-item class="text_item">
                                <el-input v-model="form.account" placeholder="账号" class="text">
                                    <template #prefix>
                                        <el-icon size="30" color="#009999">
                                            <User/>
                                        </el-icon>
                                    </template>
                                </el-input>
                            </el-form-item>
                        </el-tooltip>
                        <!-- 密码输入 -->
                        <el-tooltip :visible="psdTipVisibility" placement="right" :content="psdTipContent">
                            <el-form-item  class="text_item">
                                <el-input v-model="form.password" placeholder="密码" type="password" class="text" show-password>
                                    <template #prefix>
                                        <el-icon size="30" color="#009999">
                                            <Lock />
                                        </el-icon>
                                    </template>
                                </el-input>
                            </el-form-item>
                        </el-tooltip>
                        <!-- 注册链接 -->
                        <el-form-item style="margin-left: 170px;">
                            <el-link @click="jumpToRegister()">
                                还没有账号？去注册！
                                <el-icon size="20" color="#009999"><Right /></el-icon>
                            </el-link>
                        </el-form-item>
                        <!-- 登录按钮 -->
                        <el-form-item>
                            <el-button class="button" color="#009999" style="margin: auto auto;" @click="submitForm()">
                                <el-icon :size="20"><SuccessFilled /></el-icon>
                                &nbsp;登录
                            </el-button>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>
        </el-main>
    </el-container>
</template>

<script lang="js" setup>
import { Lock, User, Right, SuccessFilled } from "@element-plus/icons-vue";
import { ref, reactive } from 'vue';
import { useRouter } from "vue-router";
import { login } from "@/api/login/index.js";

const form = reactive({
    isStore: false,
    account: '',
    password: ''
})
const router = useRouter();

let accTipVisibility = ref(false);
let accTipContent = ref("");
let psdTipVisibility = ref(false);
let psdTipContent = ref("");

function jumpToRegister() {
    if(form.isStore) {
        router.push({
            path: '/storeRegister'
        })
    }
    else {
        router.push({
            path: '/userRegister'
        })
    }
}
function submitForm() {
    if(form.account.length === 0) {
        accTipContent.value = "账号不能为空！"
        accTipVisibility.value = true;

        setTimeout( () => {
            accTipVisibility.value = false;
        }, 3000)
        return
    }

    if(form.password.length === 0) {
        psdTipContent.value = "密码不能为空！"
        psdTipVisibility.value = true;

        setTimeout(() => {
            psdTipVisibility.value = false;
        }, 3000)
        return
    }

    console.log(login(form));
}

</script>

<style scoped>
form {
    width: 350px;
    height: 300px;
    border: 1px solid rgba(0, 153, 153, 0.5);
    box-shadow: var(--el-box-shadow);
    border-radius: var(--el-border-radius-round);
    position: relative;

    .text_item {
        width: 300px;
        margin: 25px auto;

        :deep(.el-input__wrapper) {
            background-color: transparent;
            padding: 2px 11px;
            box-shadow: none;
            border: 1px solid rgba(0, 153, 153, 0.5);
        }

        :deep(.el-input__wrapper.is-focus) {
            border: 1px solid #009999;
            box-shadow: var(--el-box-shadow);
        }
    }

    .checkbox:hover {
        box-shadow: var(--el-box-shadow);
    }


}
</style>