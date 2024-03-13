<template>
  <div
    class="min-w-[1269px] max-w-[1269px] h-full mx-auto whitespace-nowrap p-6"
  >
    <div class="flex flex-wrap">
      <div class="grid grid-cols-5 gap-4">
        <ShopProductCard
          v-for="(product, index) in products"
          :key="index"
          :name="product.name"
          :price="product.price"
          :img="product.productImages?.[0]?.imageurl"
          :seo="product.seo"
        />
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
// @ts-nocheck
import { useRoute } from "vue-router";

const route = useRoute();

const routeparam = route.params.namecontain;

const { data: products, error } = await useFetch(
  `http://localhost:8080/api/v1/shop/products/contain?namecontain=${routeparam}`
);

if (error.value) {
  console.error(error.value);
}
</script>
