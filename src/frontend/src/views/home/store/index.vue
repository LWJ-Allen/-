<template>
    <el-dialog v-model="dialogVisibility" title="添加餐品" width="500" draggable>
        <el-form v-model="form" class="form">
            <el-tooltip placement="right" :visible="nameTipVisibility" :content="nameTipContent">
                <el-form-item class="text-item">
                    <el-input v-model="form.foodName" placeholder="名字"></el-input>
                </el-form-item>
            </el-tooltip>
            <el-tooltip placement="right" :visible="priceTipVisibility" :content="priceTipContent">
                <el-form-item class="text-item">
                    <el-input v-model="form.foodPrice" placeholder="价格/元"></el-input>
                </el-form-item>
            </el-tooltip>
            <el-tooltip placement="right" :visible="descriptionTipVisibility" :content="descriptionTipContent">
                <el-form-item class="textarea-item">
                    <el-input v-model="form.foodDescription" placeholder="描述" type="textarea" resize="none" rows="5"></el-input>
                </el-form-item>
            </el-tooltip>
            <el-form-item>
                <el-button color="#009999" style="margin-left: 120px" @click="submitForm()">
                    <el-icon size="20"><SuccessFilled /></el-icon>
                    添加
                </el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
    <el-container v-loading="loading" element-loading-text="请稍后...">
        <el-header height="50px" style="background-color: #009999">
            <el-container class="top-nav">
                <el-image src="/icon-white.svg" style="width:50px; height: 50px" class="header-icon"></el-image>
                <el-button class="top-button" @click="browseFood()">
                    浏览餐品
                </el-button>
                <el-button class="top-button" @click="browseOrder()">
                    查询订单
                </el-button>
                <el-button class="top-button" @click="dialogVisibility = true">
                    添加餐品
                </el-button>
                <el-dropdown>
                    <el-image class="header-icon avatar">
                        <template #error>
                            <div class="image-slot">
                                <el-icon :size="20"><Picture /></el-icon>
                            </div>
                        </template>
                    </el-image>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item @click="logout()">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-container>
        </el-header>
        <el-main style="width: 100%; height: 750px; overflow-y: auto">
            <template v-if="curPage === 0">
                <template v-if="empty">
                    <el-empty description="空空如也" style="width: 100%"></el-empty>
                </template>
                <template v-else>
                    <el-text tag="b" style="text-decoration: underline; font-size: 30px">{{storeData["store_name"]}}</el-text>
                    <el-container v-for="item in foodList" class="food-container" style="width: 400px" >
                        <el-header style="height: 200px;">
                            <div class="block">
                                <el-text class="demonstration" :truncated="true">{{item.food_name}}</el-text>
                                <el-image>
                                    <template #error>
                                        <div class="image-slot">
                                            <el-icon><Picture /></el-icon>
                                        </div>
                                    </template>
                                </el-image>
                            </div>
                        </el-header>
                        <el-main>
                            <el-text tag="b" style="display: block; margin-bottom: 5px;">描述:</el-text>
                            <el-input type="textarea" resize="none" rows="5" style="width: 90%; font-size: 12px; text-indent: 2em" :disabled="true" :model-value="item.food_description" />
                            <el-container style="margin-top: 5px">
                                <el-text tag="b" >价格: {{ item.food_price }}元</el-text>
                            </el-container>
                        </el-main>
                    </el-container>
                </template>
            </template>
            <template v-if="curPage === 1">
                <el-table class="order-table" :data="orderList">
                    <el-table-column prop="orderdate" label="下单时间" width="180" />
                    <el-table-column prop="foodname" label="餐品名称" width="180" />
                    <el-table-column prop="username" label="购买者" width="180" />
                    <el-table-column prop="orderprice" label="价格" width="180" />
                </el-table>
            </template>
        </el-main>
    </el-container>
</template>

<script lang="js" setup>
import {reactive, ref} from "vue";
import {useRouter} from "vue-router";
import {clearLocalData, getLocalData} from "@/utils/session.js";
import {Picture, SuccessFilled} from "@element-plus/icons-vue";
import {queryMenu, queryOrder, addFood} from "@/api/home/store/index.js";
import {ElMessage} from "element-plus";

const storeData = getLocalData("storeData");
const router = useRouter()
const form = reactive({
    account: storeData["store_account"],
    foodName: "",
    foodPrice: "",
    foodDescription: ""
})

let nameTipVisibility = ref(false)
let nameTipContent = ref("")
let priceTipVisibility = ref(false)
let priceTipContent = ref("")
let descriptionTipVisibility = ref(false)
let descriptionTipContent = ref("")

let loading = ref(false)
let foodList = reactive([])
let orderList = ref([])
let empty = ref(true)
let curPage = ref(0)
let dialogVisibility = ref(false)
function browseFood() {
    loading.value = true
    curPage.value = 0
    queryMenu({account: storeData["store_account"]}).then(response => {
        loading.value = false

        let code = response.data.code
        let message = response.data.message
        if(code === "101") {
            foodList = response.data.data["menu_list"]
            empty.value = foodList.length === 0
        }
        else {
            ElMessage(
                {
                    message: message,
                    type: "error"
                }
            )
        }
    })
        .catch(err => {
            loading.value = false
            empty.value = true

            ElMessage(
                {
                    message: err.message,
                    type: "error"
                }
            )
        })
}

function browseOrder() {
    loading.value = true

    queryOrder({storeId: storeData["store_id"]}).then(response => {
        loading.value = false

        let code = response.data.code
        let message = response.data.message
        if(code === "101") {
            orderList = response.data.data["store_order_list"]
        }
        else {
            ElMessage(
                {
                    message: message,
                    type: "error"
                }
            )
        }
        curPage.value = 1
    })
        .catch(err => {
            loading.value = false

            ElMessage(
                {
                    message: err.message,
                    type: "error"
                }
            )
    })
}

function showTip(vBool, vContent, content) {
    vContent.value = content
    vBool.value = true
    setTimeout(() => {
        vBool.value = false
    }, 3000)
}

function foodNameCheck() {
    if(form.foodName.length === 0) {
        showTip(nameTipVisibility, nameTipContent, "名字不能为空！")
        return true
    }
    return false
}

function foodPriceCheck() {
    if(form.foodPrice.length === 0) {
        showTip(priceTipVisibility, priceTipContent, "请输入价格！")
        return true
    }
    if(!/^\d+(\.\d+)?$/.test(form.foodPrice)) {
        showTip(priceTipVisibility, priceTipContent, "请输入正确的价格！")
        return true
    }
    return false
}
function foodDescriptionCheck() {
    if(form.foodDescription.length === 0) {
        showTip(descriptionTipVisibility, descriptionTipContent, "请输入描述！")
        return true
    }
    return false
}
function submitForm() {
    if(foodNameCheck()) return
    if(foodPriceCheck()) return
    if(foodDescriptionCheck()) return

    loading.value = true
    addFood(form).then(response => {
        loading.value = false
        if(response.data.code === "101") {
            ElMessage(
                {
                    message: "添加成功！",
                    type: "success"
                }
            )
        }
        else {
            ElMessage(
                {
                    message: "添加失败！" + response.data.message,
                    type: "error"
                }
            )
        }
    })
        .catch(err => {
            loading.value = false
            ElMessage(
                {
                    message: "添加失败！" + err.message,
                    type: "error"
                }
            )
        })
}

function logout() {
    clearLocalData("storeData")
    router.push('/login')
}

browseFood()
</script>

<style scoped>
.top-nav {
    background-color: transparent;
    width: 80%;
    margin: auto;

    .avatar {
        padding: 10px;
        background-color: #f3f4f7;
        width: 20px; height: 20px;
        margin-left: 900px;
        margin-top: 5px;
        border-radius: var(--el-border-radius-base);
    }

}

:deep(.top-button) {
    margin: 5px 10px 5px 10px;
    border: none;
    color: white;
    background-color: transparent;
    height: 40px;
}

:deep(.top-button:hover, .top-button:focus) {
    background-color: #4DB8B8;
}

.block {
    padding: 10px 2px;
    text-align: center;
    display: inline-block;
    width: 300px;
    height: 200px;
    box-sizing: border-box;
    vertical-align: top;
//border-right: solid 1px var(--el-border-color);
}

.demonstration {
    display: block;
    color: var(--el-text-color-regular);
    font-size: 15px;
    margin-bottom: 5px;
}
.el-image:not(.header-icon) {
    padding: 0 5px;
    width: 200px;
    height: 150px;
    border: 1px solid rgba(128, 128, 128, 0.5);
    border-radius: var(--el-border-radius-base);
}

.image-slot {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 100%;
    background: var(--el-fill-color-light);
    color: var(--el-text-color-secondary);
}

.image-slot .el-icon {
    font-size: 30px;
}

.food-container {
    width: 80%;
    height: 400px;
    margin: 20px 20px;
    border: 1px solid rgba(128, 128, 128, 0.5);
    border-radius: var(--el-border-radius-base);
}
:deep(.food-container:hover) {
    box-shadow: var(--el-box-shadow);
    border: 1px solid #009999;
}

:deep(.el-table--fit) {
    background-color: transparent;
    border: 1px solid rgba(0, 153, 153, 0.5);
    width: 721px;
    border-radius: var(--el-border-radius-base);
    padding: 0;
}

:deep(.el-table--fit:hover) {
    box-shadow: var(--el-box-shadow);
    border: 1px solid #009999;
}

form {
    width: 350px;
    border-radius: var(--el-border-radius-base);
    border: 1px solid #009999;
    margin: auto auto;

    .text-item {
        width: 300px;
        margin-left: 25px;
        margin-top: 20px;

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
        width: 300px;
        margin: auto auto;
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

    .el-button {
        margin-top: 20px;
    }
}

</style>