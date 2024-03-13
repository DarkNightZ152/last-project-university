<template>
  <div>
    <div class="min-w-[1269px] max-w-[1269px] h-full mx-auto whitespace-nowrap">
      <div class="flex">
        <div class="w-[274.56px]">
          <div v-for="(item, index) in categories" :key="index">
            <NuxtLink
              :to="`/category/${item.seo}`"
              class="flex justify-left border-b border-l border-r hover:text-red-700 hover:border-red-700 hover:border-t cursor-pointer"
              ><div class="h-[40px] text-lg ml-10 items-center">
                <!-- <h1>{{ item.name }}</h1> -->
                {{ item.name }}
              </div>
            </NuxtLink>
          </div>
        </div>
        <img
          src="/637124481852258045_NF,NE,RC@2x.png"
          class="w-[990px] h-[451px]"
        />
      </div>
      <div class="mt-3 mb-1">
        <div class="w-full h-[50px] bg-gray-100 rounded-xl">
          <div class="h-full w-[250px] bg-red-700 p-3 rounded-xl">
            <h1 class="font-extrabold text-white text-xl ml-2">
              CÁC SẢN PHẨM MỚI
            </h1>
          </div>
        </div>
        <div class="bg-slate-200 h-[300px] mt-1 rounded-xl pt-1 pb-1 px-3 flex">
          <div class="space-x-6 flex">
            <ShopProductCard
              v-for="product in products"
              :name="product.name"
              :price="product.price"
              :img="product.imagesurl"
              :seo="product.seo"
              @add-to-cart="test.addToCart(store.token, product.seo, 1)"
            />
          </div>
        </div>
      </div>
      <div class="mt-3 mb-1">
        <div class="w-full h-[50px] bg-gray-100 rounded-xl">
          <div class="h-full w-[250px] bg-red-700 p-3 rounded-xl">
            <h1 class="font-extrabold text-white text-xl ml-2">
              NHIỀU LƯỢT XEM
            </h1>
          </div>
        </div>
        <div class="bg-slate-200 h-[300px] mt-1 rounded-xl pt-1 pb-1 px-3 flex">
          <div class="space-x-6 flex">
            <ShopProductCard
              v-for="product in products"
              :name="product.name"
              :price="product.price"
              :img="product.imagesurl"
              :seo="product.seo"
              @add-to-cart="test.addToCart(store.token, product.seo, 1)"
            />
          </div>
        </div>
      </div>
      <div class="mt-3 mb-3">
        <div class="w-full h-[50px] bg-gray-100 rounded-xl">
          <div class="h-full w-[250px] bg-red-700 p-3 rounded-xl">
            <h1 class="font-extrabold text-white text-xl ml-2">
              SẢN PHẨM NỔI BẬT
            </h1>
          </div>
        </div>
        <div class="bg-slate-200 h-[300px] mt-1 rounded-xl pt-1 pb-1 px-3 flex">
          <div class="space-x-6 flex">
            <ShopProductCard
              v-for="product in products"
              :name="product.name"
              :price="product.price"
              :img="product.imagesurl"
              :seo="product.seo"
              @add-to-cart="test.addToCart(store.token, product.seo, 1)"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
// @ts-nocheck
const test = useTest();
const store = useAuthStore();

const categories = ref();
//Lấy data từ bảng category absolute
const { data: category } = await useAsyncData("categories", () =>
  $fetch("http://localhost:8080/api/v1/shop/category")
);
categories.value = category.value;

const products = ref();
const { data } = await useFetch(
  "http://localhost:8080/api/v1/shop/home-product"
);
products.value = data.value.slice(0, 5);
</script>
