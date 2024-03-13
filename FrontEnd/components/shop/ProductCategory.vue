<template>
  <div
    class="w-[274.56px] bg-white"
    @mouseenter="CategoryStore.categoryState = true"
    @mouseleave="CategoryStore.categoryState = false"
  >
    <div v-for="(item, index) in categories" :key="index">
      <NuxtLink
        :to="`/category/${item.seo}`"
        class="flex justify-left border-b border-l border-r hover:text-red-700 hover:border-red-700 hover:border-t cursor-pointer"
        ><div class="h-[40px] text-lg ml-10 items-center flex">
          <!-- <h1>{{ item.name }}</h1> -->
          {{ item.name }}
        </div></NuxtLink
      >
    </div>
  </div>
</template>
<script setup>
const CategoryStore = useStateStore();
const categorySEO = ref(null);
//Lấy data từ bảng category absolute
const { data: categories } = await useAsyncData("categories", () =>
  $fetch("http://localhost:8080/api/v1/shop/category")
);
</script>
