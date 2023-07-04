<template>
    <el-container>
        <el-header height="50px" style="background-color: #009999">
            <el-menu default-active="1" mode="horizontal" :ellipsis="false" style="background-color: transparent">
                <el-image src="/icon.svg" style="width: 50px; height: 50px"></el-image>
            </el-menu>

        </el-header>
    </el-container>
</template>

<script lang="js" setup>
import {clearLocalData, setLocalData} from "@/utils/session.js";
clearLocalData("userData")
setLocalData("userData", {"id": "20337235", "account": "zhouszy123", "password": "1733122410", "name": "孙高锦"})
</script>

<style scoped>

</style>