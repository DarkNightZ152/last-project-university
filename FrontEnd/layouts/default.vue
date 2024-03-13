<template>
  <div>
    <ShopHeader />
    <NuxtPage />
    <ShopFooter />
  </div>
</template>

<script lang="ts" setup>
// @ts-nocheck
checkLogin();

const route = useRoute();

watchEffect(() => {
  if (route.path === "/") {
    reloadNuxtApp({ path: "/" });
  }
});
</script>
