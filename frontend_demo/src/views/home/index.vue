<template>
  <el-row class="mb-4">
    <el-button @click="jump">跳转</el-button>
    <el-button type="primary">home</el-button>
    <el-button type="success">detail</el-button>
    <el-button type="info">register</el-button>
    <el-button type="warning">login</el-button>
    <el-button type="danger">Dialog</el-button>
  </el-row>
</template>
<script setup>
import { useRoute, useRouter } from "vue-router";

const route = useRoute();
const router = useRouter();

function jump() {
  router.push({ name: "login", params: { id: 123, name: 'sgj' } });
}
</script>
<style scoped lang="scss"></style>
