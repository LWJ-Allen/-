<template>
  <div class="flex">
    <h1>{{ userInfo }}</h1>
    <el-button type="primary" :icon="Edit" @click="Jump" />
  </div>
</template>
<script setup lang="ts">
import { Edit } from "@element-plus/icons-vue";
import { useRouter } from "vue-router";
import { ref, onMounted } from "vue";
import { getData } from "../../utils/session";
const router = useRouter();
const userInfo = ref("登录 | 注册");
function Jump() {
  router.push({ path: "/loginRegister" });
}
// 初始化的一些操作 比如读取token
function init() {
  // 如果读到了token 证明用户已经登陆 那么应该显示用户的登录信息 并且开放例如我的订单等路由 这个逻辑没有写 和登陆注册类似 点击我的订单 如果带token 根据用户名去查数据库 返回数据 前端渲染 如果不带token 拦下来 并且路由跳转到注册登录页
  if (getData("userData")?.token) {
    userInfo.value = getData("userData")?.name;
  }
}
// 页面挂载时调用初始化函数
onMounted(() => {
  init();
});
</script>
