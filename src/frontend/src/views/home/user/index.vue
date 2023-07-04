<template>
    <el-container v-loading="loading" element-loading-text="请稍后...">
        <el-header height="50px" style="background-color: #009999">
            <el-container class="top-nav">
                <el-image src="/icon-white.svg" style="width:50px; height: 50px" class="header-icon"></el-image>
                <el-button class="top-button" @click="browseStore()">
                    浏览商家
                </el-button>
                <el-button class="top-button" @click="browseHistoryOrder()">
                    查询历史订单
                </el-button>
                <el-dropdown>
                    <el-image class="header-icon" style="padding: 10px; background-color: #f3f4f7;; width: 20px; height: 20px; margin-left: 1000px; margin-top: 5px">
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
        <template v-if="curPage === 0">
            <el-container>
                <el-aside width="60%" style="overflow-y: auto; height: 750px">
                    <el-container v-for="item in storeList" class="store-container" @click="clickStore(item.store_account, item.store_name, item.store_id)">
                        <el-aside width="30%">
                            <div class="block">
                                <el-text class="demonstration" :truncated="true">{{item.store_name}}</el-text>
                                <el-image>
                                    <template #error>
                                        <div class="image-slot">
                                            <el-icon><Picture /></el-icon>
                                        </div>
                                    </template>
                                </el-image>
                            </div>
                        </el-aside>
                        <el-main>
                            <el-text tag="b" style="display: block; margin-bottom: 5px">店家描述:</el-text>
                            <el-input type="textarea" resize="none" rows="5" style="width: 70%; font-size: 15px; text-indent: 2em" :disabled="true" :model-value="item.store_des" />
                        </el-main>
                    </el-container>
                </el-aside>
                <el-main style="margin-left: 40px; overflow-y: auto; height: 750px">
                    <template v-if="curAccount.length === 0">
                        <el-empty description="..." />
                    </template>
                    <template v-if="curAccount.length !== 0">
                        <h1 style="font-size: 30px; font-weight: bold; color: var(--el-text-color-regular); text-decoration: underline; margin-bottom: 40px">{{curStoreName}}</h1>
                        <el-container v-for="item in curMenu" class="menu-container">
                            <el-header style="height: 200px;">
                                <div class="block" style="width: 100%">
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
                                <el-text tag="b" style="display: block; margin-bottom: 5px">描述:</el-text>
                                <el-input type="textarea" resize="none" rows="5" style="width: 90%; font-size: 12px; text-indent: 2em" :disabled="true" :model-value="item.food_description" />
                                <el-container style="margin-top: 5px">
                                    <el-text tag="b" >价格: {{ item.food_price }}元</el-text>
                                    <el-button color="#009999" style="margin-left: 270px" @click="clickFood(item.food_id, item.food_price)">
                                        <el-icon size="20"><SuccessFilled /></el-icon>
                                        下单
                                    </el-button>
                                </el-container>
                            </el-main>
                        </el-container>
                    </template>
                </el-main>
            </el-container>
        </template>
        <template v-else>
            <el-main class="history-order" style="overflow-y: auto; height: 750px">
                <div v-for="item in historyOrder" class='history-order-container'>
                   <div style="display: inline-block; height: 140px">
                       <div class="order">
                           <el-text tag="b" >店名: {{item.storename}}</el-text>
                       </div>
                       <div class="order">
                           <el-text tag="b" >餐名: {{item.foodname}}</el-text>
                       </div>
                       <div class="order">
                           <el-text tag="b" >下单时间: {{item.orderdate}}</el-text>
                       </div>
                       <div class="order">
                           <el-text tag="b" >价格: {{item.orderprice}}元</el-text>
                       </div>
                   </div>
                    <div style="display: inline-block; height: 140px; text-align: center">
                        <el-text tag="b" type="danger">未完成！</el-text>
                    </div>
                </div>
            </el-main>
        </template>
    </el-container>
</template>

<script lang="js" setup>
import {clearLocalData, getLocalData} from "@/utils/session.js";
import {onMounted, reactive, ref} from "vue";
import {queryStore, queryMenu, order, browseOrder} from "@/api/home/user/index.js";
import {Picture, SuccessFilled} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";
import {useRouter} from "vue-router";

const router = useRouter()

const userData = getLocalData("userData")

const userId = userData.user_id

let curAccount = ref("")
let curStoreId = ref("")
let curStoreName = ref("")
let curFoodId = ref("")
let curFoodPrice = ref("")
let curMenu = reactive([])
let loading = ref(false)
let storeList = null

let curPage = ref(0)
function browseStore() {
    curPage.value = -1;
    loading.value = true

    queryStore().then(response => {
        loading.value = false
        if(response.data.code === "101") {
            storeList = response.data.data["store_list"]
            curPage.value = 0;
        }
        else {
            ElMessage(
                {
                    message: response.data.message,
                    type: 'error'
                }
            )
        }
    })
        .catch(err => {
            loading.value = true
            ElMessage(
                {
                    message: err.message,
                    type: 'error'
                }
            )
        })

}

let historyOrder = reactive([])
function browseHistoryOrder() {
    loading.value = true

    browseOrder({userId: userId}).then(response => {
        loading.value = false;
        let code = response.data.code
        let message = response.data.message
        if(code === "101") {
            historyOrder = response.data.data["user_order_list"]
        }
        else {
            ElMessage(
                {
                    message: message,
                    type: "error"
                }
            )
        }
        curPage.value = 1;
    })
        .catch(err => {
            loading.value = false
            ElMessage(
                {
                    message: err.message,
                    type: "error"
                }
            )
            curPage.value = 1;
        })
}

function clickStore(storeAccount, storeName, storeId) {
    loading.value = true

    queryMenu({account: storeAccount}).then(response => {
        let code = response.data.code;
        let message = response.data.message;
        let data = response.data.data;
        loading.value = false
        if(code === '101') {
            curMenu = data["menu_list"]
            curAccount.value = storeAccount;
            curStoreName.value = storeName;
            curStoreId.value = storeId;
        }
        else {
            ElMessage({
                message: message,
                type: "error"
            })
            curAccount.value = ""
        }
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

function clickFood(foodId, foodPrice) {
    curFoodPrice.value = foodPrice
    curFoodId.value = foodId
    const now = new Date();
    const currentTimeString = now.toGMTString();
    const params = {
        userId: userId,
        storeId: curStoreId.value,
        foodId: curFoodId.value,
        orderDate: currentTimeString,
        orderPrice: curFoodPrice.value
    }
    loading.value = true
    order(params).then(response => {
        let code = response.data.code
        let message = response.data.message
        loading.value = false
        if(code === "101") {
            ElMessage(
                {
                    message: "下单成功！",
                    type: "success"
                }
            )
        }
        else {
            ElMessage(
                {
                    message: "下单失败！" + message,
                    type: "error"
                }
            )
        }
    }).catch(err => {
        loading.value = false
        ElMessage(
            {
                message: err.message,
                type: 'error'
            }
        )
    })
}


function logout() {
    clearLocalData("userData")
    router.push("/login")
}

</script>

<style scoped>

.top-nav {
    background-color: transparent;
    width: 80%;
    margin: auto;

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

.store-container {
    width: 80%;
    height: 200px;
    margin: 20px 20px;
    border: 1px solid rgba(128, 128, 128, 0.5);
    border-radius: var(--el-border-radius-base);
}
:deep(.store-container:hover) {
    box-shadow: var(--el-box-shadow);
    border: 1px solid #009999;
}

.menu-container {
    width: 80%;
    height: 400px;
    margin: 20px 20px;
    border: 1px solid rgba(128, 128, 128, 0.5);
    border-radius: var(--el-border-radius-base);
}
:deep(.menu-container:hover) {
    box-shadow: var(--el-box-shadow);
    border: 1px solid #009999;
}

.block {
    padding: 10px 2px;
    text-align: center;
    display: inline-block;
    width: 100%;
    height: 100%;
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

.history-order-container {
    height: 150px;
    margin: 20px 20px;
    border: 1px solid rgba(128, 128, 128, 0.5);
    border-radius: var(--el-border-radius-base);
}
:deep(.history-order-container:hover) {
    box-shadow: var(--el-box-shadow);
    border: 1px solid #009999;
}

.order {
    margin: 20px 20px;
}
</style>