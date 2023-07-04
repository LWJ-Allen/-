<template>
    <el-container style="align-items: center" v-loading="loading" element-loading-text="注册中...">
        <el-header height="150px">
            <el-container style="margin-top: 50px">
                <el-image src="/icon.svg" style="width: 100px; height: 100px"></el-image>
                <el-text style="letter-spacing: 5px; font-size: 30px; margin-left: 20px">云食坊~</el-text>
            </el-container>
        </el-header>
        <el-main class="main">
            <el-form :model="form">
                <!-- 学号 -->
                <el-tooltip placement="right" :visible="idTipVisibility" :content="idTipContent">
                    <el-form-item class="text-item">
                        <el-input v-model="form.id" placeholder="学号"></el-input>
                    </el-form-item>
                </el-tooltip>
                <!-- 姓名 -->
                <el-tooltip placement="right" :visible="nameTipVisibility" :content="nameTipContent">
                    <el-form-item class="text-item">
                        <el-input v-model="form.name" placeholder="姓名"></el-input>
                    </el-form-item>
                </el-tooltip>
                <el-tooltip placement="right" :visible="accountTipVisibility" :content="accountTipContent">
                    <el-form-item class="text-item">
                        <el-input v-model="form.account" placeholder="账号"></el-input>
                    </el-form-item>
                </el-tooltip>
                <el-tooltip placement="right" :visible="passwordTipVisibility" :content="passwordTipContent">
                    <el-form-item class="text-item">
                        <el-input v-model="form.password" placeholder="密码" type="password"></el-input>
                    </el-form-item>
                </el-tooltip>
                <el-tooltip placement="right" :visible="phoneTipVisibility" :content="phoneTipContent">
                    <el-form-item class="text-item">
                        <el-input v-model="form.phone" placeholder="电话"></el-input>
                    </el-form-item>
                </el-tooltip>
                <el-form-item>
                    <el-link style="margin-left: 230px" @click="router.push({path: 'storeRegister'})">
                        商家注册
                        <el-icon size="20" color="#009999"><Right /></el-icon>
                    </el-link>
                </el-form-item>
                <el-form-item>
                    <el-button class="button" type="primary" style="margin: auto auto" @click="router.push('/login')">
                        <el-icon :size="20"><Back/></el-icon>
                        返回登录
                    </el-button>
                    <el-button class="button" color="#009999" style="margin: auto auto" @click="submitForm()">
                        <el-icon :size="20"><SuccessFilled /></el-icon>
                        注册
                    </el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script lang="js" setup>
import { reactive, ref } from "vue";
import {Back, Right, SuccessFilled} from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import {userRegister} from "@/api/register/index.js";
import {ElMessage} from "element-plus";

const form = reactive({
    id: "",
    name: "",
    account: "",
    password: "",
    phone: ""
})

const router = useRouter()
const loading = ref(false)

let idTipVisibility = ref(false);
let idTipContent = ref("");
let nameTipVisibility = ref(false);
let nameTipContent = ref("");
let accountTipVisibility = ref(false);
let accountTipContent = ref("");
let passwordTipVisibility = ref(false);
let passwordTipContent = ref("");
let phoneTipVisibility = ref(false);
let phoneTipContent = ref("");

function showTip(visibilityVar, contentVar, content) {
    contentVar.value = content;
    visibilityVar.value = true;

    setTimeout(() => {
        visibilityVar.value = false;
    }, 3000);

}
function idCheck() {
    if(!/^\d{8}$/.test(form.id)) {
        showTip(idTipVisibility, idTipContent, "请输入8位数字学号！")
        return true;
    }

    return false;
}

function nameCheck() {
    if(form.name.length === 0) {
        showTip(nameTipVisibility, nameTipContent, "姓名不能为空！")
        return true;
    }

    return false;
}

function accountCheck() {
    if(!/^[a-zA-Z][a-zA-Z0-9]{4,19}$/.test(form.account)) {
        showTip(accountTipVisibility, accountTipContent, "账号只能为5~20位的数字和字母的组合，且字母开头！")
        return true
    }
}

function passwordCheck() {
    if(!/^[a-zA-Z][a-zA-Z0-9]{7,19}$/.test(form.password)) {
        showTip(passwordTipVisibility, passwordTipContent, "密码只能为8~20位的数字和字母的组合，且字母开头！")
        return true
    }
    return false
}

function phoneCheck() {
    if(!/^1\d{10}$/.test(form.phone)) {
        showTip(phoneTipVisibility, phoneTipContent, "请输入正确的电话号码！")
        return true
    }
    return false
}
function submitForm() {
    if(idCheck()) return
    if(nameCheck()) return
    if(accountCheck()) return
    if(passwordCheck()) return
    if(phoneCheck()) return

    loading.value = true;
    userRegister(form).then(response => {
        loading.value = false;
        const code = response.data.code;
        if(code === "101") {
            ElMessage({
                message: "注册成功！欢迎您，" + form.name + "！",
                type: "success"
            })
        }
        else {
            showTip(idTipVisibility, idTipContent, response.data.message)
        }
    })
        .catch(err => {
            loading.value = false;
            ElMessage({
                dangerouslyUseHTMLString: true,
                message: "<strong>" + err.message + "</strong>",
                type: "error"
            })
        })


}
</script>
<style scoped>
.main {
    width: 350px;
    height: 450px;
    border: 1px solid rgba(0, 153, 153, 0.5);
    box-shadow: var(--el-box-shadow);
    border-radius: var(--el-border-radius-round);
    position: relative;

    .text-item {
        width: 300px;

        :deep(.el-input__wrapper) {
            background-color: transparent;
            padding: 2px 11px;
            box-shadow: none;
            border: 1px solid rgba(0, 153, 153, 0.5);
            border-radius: var(--el-border-radius-round);
        }

        :deep(.el-input__wrapper.is-focus) {
            border: 1px solid #009999;
            box-shadow: var(--el-box-shadow);
        }
    }
}
</style>