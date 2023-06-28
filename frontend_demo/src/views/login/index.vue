<template>
  <div>{{ params }}</div>
</template>
<script setup>
import { useRoute, useRouter } from "vue-router";
import { reactive, onMounted } from "vue";

const route = useRoute();
const router = useRouter();

</script>
<style scoped lang="scss"></style>
