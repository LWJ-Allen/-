<template>
    <el-container v-loading="loading" element-loading-text="请稍后...">
        <el-header height="50px" style="background-color: #009999">
            <el-container class="top-nav">
                <el-image src="/icon-white.svg" style="width:50px; height: 50px"></el-image>
                <el-button class="top-button" @click="browseStoreClick()">
                    浏览商家
                </el-button>
                <el-button class="top-button" @click="browseHistoryOrder()">
                    查询历史订单
                </el-button>
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
                        <h1 style="font-size: 30px; font-weight: bold; color: var(--el-text-color-regular); text-decoration: underline; margin-bottom: 40px">{{curName}}</h1>
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
    </el-container>
</template>

<script lang="js" setup>
import {clearLocalData, getLocalData, setLocalData} from "@/utils/session.js";
import {reactive, ref} from "vue";
import {queryStore, queryMenu, order} from "@/api/home/user/index.js";
import {Picture, SuccessFilled} from "@element-plus/icons-vue";
import {ElMessage} from "element-plus";

clearLocalData("userData")
setLocalData("userData", {"id": "20337235", "account": "zhouszy123", "password": "1733122410", "name": "孙高锦"})

const curUserId = getLocalData("userData").id;

let curAccount = ref("")
let curStoreId = ref("")
let curName = ref("")
let curFoodId = ref("")
let curFoodPrice = ref("")

let curMenu = reactive([])
let loading = ref(false)

let storeList = null
let response = JSON.parse('{"code":"101","message":"success","data":{"store_list":[{"store_name":"adasdadad","store_account":"adasda","store_password":"asdasdsa","store_des":"asdasdasdasdas","store_d_name":"asdasdasd","store_d_id":"adasdasdasd","store_d_phone":"17331224100","store_id":"10000001"},{"store_name":"dddddd","store_account":"aaaaaa","store_password":"a123456789","store_des":"dddddd","store_d_name":"ddddd","store_d_id":"dddddd","store_d_phone":"17331224101","store_id":"10000000"}, {"store_name":"adasdadad","store_account":"adasda","store_password":"asdasdsa","store_des":"asdasdasdasdas","store_d_name":"asdasdasd","store_d_id":"adasdasdasd","store_d_phone":"17331224100","store_id":"10000001"}, {"store_name":"adasdadad","store_account":"adasda","store_password":"asdasdsa","store_des":"asdasdasdasdas","store_d_name":"asdasdasd","store_d_id":"adasdasdasd","store_d_phone":"17331224100","store_id":"10000001"}, {"store_name":"adasdadad","store_account":"adasda","store_password":"asdasdsa","store_des":"asdasdasdasdas","store_d_name":"asdasdasd","store_d_id":"adasdasdasd","store_d_phone":"17331224100","store_id":"10000001"}, {"store_name":"adasdadad","store_account":"adasda","store_password":"asdasdsa","store_des":"asdasdasdasdas","store_d_name":"asdasdasd","store_d_id":"adasdasdasd","store_d_phone":"17331224100","store_id":"10000001"}]}}')

storeList = response["data"]["store_list"];
/*queryStore().then(reponse => {
    let code = reponse.data["code"]
    let message = reponse.data["message"]


})*/


let curPage = ref(0);
function browseStoreClick() {
    curPage.value = 0;
}

function browseHistoryOrder() {
    curPage.value = 1;
}

function clickStore(storeAccount, storeName, storeId) {
    curAccount.value = storeAccount;
    curName.value = storeName;
    curStoreId.value = storeId;

    loading.value = true
    curMenu = [{"food_id":"1000000010000","food_name":"宫保鸡丁","food_price":1.2,"food_description":"好吃"},{"food_id":"1000000010001","food_name":"xiapmizzhou","food_price":1.2,"food_description":"好吃"}]
    loading.value = false
    /*queryMenu({account: curAccount}).then(response => {
        let code = response.data.code;
        let message = response.data.message;
        let data = response.data.data;
        loading.value = false
        if(code === '101') {

        }
        else {
            ElMessage({
                message: message,
                type: "error"
            })
            curAccount.value = ""
        }
    })
        .cache(err => {
            loading.value = false
            ElMessage(
                {
                    message: err.message,
                    type: "error"
                }
            )
        })*/

}

function clickFood(foodId, foodPrice) {
    curFoodPrice = foodPrice
    curFoodId = foodId
    const now = new Date();
    const currentTimeString = now.toISOString().substring(0, 19);
    const params = {
        userId: curUserId,
        storeId: curStoreId.value,
        foodId: curFoodId,
        orderDate: currentTimeString,
        orderPrice: curFoodPrice
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
.el-image {
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
    font-size: 30px;
}
.image-slot .el-icon {
    font-size: 30px;
}
</style>