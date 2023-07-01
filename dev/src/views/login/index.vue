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
                    <el-form :model="form" id="form" ref="formRef" :rules="rules">
                        <!-- 商家checkbox -->
                        <el-form-item style="margin: 20px auto auto 25px;">
                            <el-checkbox class="checkbox" v-model="form.window" label="商家" border></el-checkbox>
                        </el-form-item>
                        <!-- 账号输入 -->
                        <el-form-item prop="account" class="text_item">
                            <el-input v-model="form.account" placeholder="账号" class="text">
                                <template #prefix>
                                    <el-icon size="30" color="#009999">
                                        <User/>
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <!-- 密码输入 -->
                        <el-form-item prop="password" class="text_item">
                            <el-input v-model="form.password" placeholder="密码" type="password" class="text" show-password>
                                <template #prefix>
                                    <el-icon size="30" color="#009999">
                                        <Lock />
                                    </el-icon>
                                </template>
                            </el-input>
                        </el-form-item>
                        <!-- 注册链接 -->
                        <el-form-item style="margin-left: 170px;">
                            <el-link>
                                还没有账号？去注册！
                                <el-icon size="20" color="#009999"><Right /></el-icon>
                            </el-link>
                        </el-form-item>
                        <!-- 登录按钮 -->
                        <el-form-item>
                            <el-button class="button" color="#009999" style="margin: auto auto;" @click="submitForm(formRef)">
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

<script lang="ts" setup>
import { Lock, User, Right, SuccessFilled } from "@element-plus/icons-vue";
import { ref, reactive } from 'vue';
import type { FormInstance, FormRules } from 'element-plus'

const formRef = ref<FormInstance>()

const checkAccount = (rule: any, value: string, callback: any) => {
    if(value === null || value === '') {
        return callback(new Error('请输入账号'))
    }
    else {
        callback();
    }
}

const checkPassword = (rule: any, value: string, callback: any) => {
    if(value === null || value === '') {
        return callback(new Error('请输入密码'))
    }
    else {
        callback();
    }
}

const form = reactive({
    window: false,
    account: '',
    password: ''
})

const rules = reactive<FormRules<typeof form>>( {
    account: [{validator: checkAccount, trigger: 'blur'}],
    password: [{validator: checkPassword, trigger: 'blur'}]
})

const submitForm = (form: FormInstance | undefined) => {
    if(!form) return;
    form.validate((valid) => {
        if(valid) {
            console.log('submit!')
        }
        else {
            console.log('error submit!')
        }
    })
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

    .button:hover {
        background-color: #4d84e2;
    }

    .checkbox:hover {
        box-shadow: var(--el-box-shadow);
    }


}
</style>