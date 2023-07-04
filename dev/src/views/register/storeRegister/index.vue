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

                <template v-if="step === 0">
                    <el-form-item class="text-item">
                        <el-input v-model="form.account" placeholder="账号"></el-input>
                    </el-form-item>
                    <el-form-item class="text-item">
                        <el-input v-model="form.password" type="password" placeholder="密码" show-password></el-input>
                    </el-form-item>
                    <el-form-item class="text-item">
                        <el-input v-model="form.name" placeholder="店名"></el-input>
                    </el-form-item>
                    <el-form-item class="textarea-item">
                        <el-input v-model="form.description" type="textarea" placeholder="店铺描述" resize="none" rows="5"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-link @click="router.push({path: '/userRegister'})">
                            <el-icon size="20" color="#009999"><Back/></el-icon>
                            学生注册
                        </el-link>
                        <el-button @click="nextStep()" color="#009999" style="margin-left: 120px">
                            <el-icon size="20"><Right /></el-icon>
                            下一步
                        </el-button>
                    </el-form-item>
                </template>
                <template v-if="step !== 0">
                    <el-form-item>
                        <el-button @click="step--" color="#009999">
                            <el-icon size="20"><ArrowLeftBold /></el-icon>
                            上一步
                        </el-button>
                    </el-form-item>
                    <el-form-item class="text-item">
                        <el-input v-model="form.directorName" placeholder="负责人姓名"></el-input>
                    </el-form-item>
                    <el-form-item class="text-item">
                        <el-input v-model="form.directorId" placeholder="负责人身份证号" type="password" show-password></el-input>
                    </el-form-item>
                    <el-form-item class="text-item">
                        <el-input v-model="form.directorPhone" placeholder="负责人手机号码"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-link @click="router.push({path: '/userRegister'})">
                            <el-icon size="20" color="#009999"><Back/></el-icon>
                            学生注册
                        </el-link>
                        <el-button color="#009999" style="margin-left: 120px" @click="submitForm()">
                        <el-icon size="20"><SuccessFilled /></el-icon>
                        注册
                    </el-button>
                    </el-form-item>
                </template>
                <el-form-item>
                    <el-button @click="router.push('/login')" type="primary" style="margin: auto auto">
                        <el-icon size="20"><Back/></el-icon>
                        返回登录
                    </el-button>
                </el-form-item>
            </el-form>
        </el-main>
    </el-container>
</template>

<script lang="js" setup>
import { reactive, ref } from "vue";
import {ArrowLeftBold, Back, Right, SuccessFilled} from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import {ElMessage} from "element-plus";
import {storeRegister} from "@/api/register/index.js";

let step = ref(0);
const form = reactive({
    name: "",
    account: "",
    password: "",
    description: "",
    directorName: "",
    directorId: "",
    directorPhone: ""
})

const router = useRouter()
const loading = ref(false)

function showElMessage(message, type) {
    ElMessage(
        {
            message: message,
            type: type
        }
    )
}

function emptyCheck(variable, tip) {
    if(variable.length === 0) {
        showElMessage(tip + "不能为空！", "error")
        return true
    }
    return false
}
function accountCheck() {
    if(emptyCheck(form.account, "账号")) return true

    if(!/^[a-zA-Z][a-zA-Z0-9]{4,19}$/.test(form.account)) {
        showElMessage("账号只能为5~20位的数字和字母的组合，且字母开头！", "warning")
        return true
    }
    return false
}

function passwordCheck() {
    if(emptyCheck(form.password, "密码")) return true
    if(!/^[a-zA-Z][a-zA-Z0-9]{7,19}$/.test(form.password)) {
        showElMessage("密码只能为8~20位的数字和字母的组合，且字母开头！", "warning")
        return true
    }
    return false
}
function nameCheck() {
    return emptyCheck(form.name, "店名");
}

function descriptionCheck() {
    return emptyCheck(form.description, "本店描述")
}

function directorNameCheck() {
    return emptyCheck(form.directorName, "负责人姓名")
}

function directorIdCheck() {
    if(emptyCheck(form.directorId, "负责人身份证号")) return true
    /*if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(form.directorId)) {
        showElMessage("请输入正确的身份证号！", "warning")
        return true
    }*/
    return false
}

function directorPhoneCheck() {
    if(emptyCheck(form.directorPhone, "负责人手机号码")) return true
    if(!/^1\d{10}$/.test(form.directorPhone)) {
        showElMessage("请输入正确格式的手机号码！", "warning")
        return true
    }
    return false
}
function nextStep() {
    if(accountCheck()) return
    if(passwordCheck()) return
    if(nameCheck()) return
    if(descriptionCheck()) return

    step.value ++;
}

function submitForm() {
    if(directorNameCheck()) return
    if(directorIdCheck()) return
    if(directorPhoneCheck()) return

    loading.value = true

    storeRegister(form).then(response => {
        loading.value = false

        let code = response.data.code;
        let message = response.data.msg;
        if(code === "101") {
            showElMessage("注册成功！", "success")
        }
        else {
            showElMessage(message, "error")
        }
    })
        .catch(err => {
            loading.value = false
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

    .textarea-item {
        border: none;

        :deep(.el-textarea__inner) {
            text-decoration: underline;
            text-underline-offset: 0.2em;
            background-color: transparent;
            border: 1px solid rgba(0, 153, 153, 0.5);
            border-radius: var(--el-border-radius-base);
            box-shadow: none;
        }

        :deep(.el-textarea__inner:focus) {
            border-color: #009999;
            box-shadow: var(--el-box-shadow);
        }
    }
}
</style>