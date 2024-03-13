<template>
  <div class="min-w-[1269px] max-w-[1269px] h-full mx-auto">
    <div class="py-8">
      <div class="px-4">
        <div class="flex">
          <div class="px-4">
            <div class="h-[460px] w-[300px] rounded-lg bg-gray-300 mb-4">
              <img
                class="w-full h-full object-cover"
                :src="data.imagesurl"
                alt="Product Image"
              />
            </div>
            <div class="flex -mx-2 mb-4">
              <div class="w-1/2 px-2">
                <button
                  class="w-full bg-red-700 text-white py-2 px-4 rounded-full font-bold hover:bg-red-800"
                  @click="test.addToCart(store.token, seo, quantity)"
                >
                  Thêm giỏ
                </button>
              </div>
              <div class="w-1/2 px-2 py-[2px]">
                <input
                  type="number"
                  min="1"
                  max="10"
                  step="1"
                  class="border h-[37px] px-5 rounded-full border-red-700"
                  v-model="quantity"
                />
              </div>
            </div>
          </div>
          <div class="px-4 w-[890px]">
            <h2 class="text-2xl font-bold text-gray-800 mb-2">
              {{ data.name }}
            </h2>
            <div class="flex space-x-5 pb-1 border-b-2 border-gray-100">
              <h2 class="text-base text-gray-800 mb-2">
                Lượt xem: {{ data.viewed }}
              </h2>
              <h2 class="text-base text-gray-800 mb-2">
                Trong kho: {{ data.amount }}
              </h2>
            </div>
            <div class="flex mb-4">
              <div class="mr-4">
                <span class="font-bold text-xl text-gray-700">Giá: </span>
                <span class="text-gray-600 text-xl">
                  {{ numbertoprice(data.price) }}
                </span>
              </div>
            </div>
            <div class="mb-4 h-[200px] w-full border border-red-700 rounded-t">
              <div class="h-[50px] bg-red-700 p-2">
                <h1 class="font-extrabold text-xl text-white">
                  KHUYẾN MÃI ĐẶC BIỆT!
                </h1>
              </div>
            </div>

            <div>
              <span class="font-bold text-gray-700">Mô tả:</span>
              <p class="text-gray-600 text-base mt-2 w-[860px] break-words">
                {{ data.description }}
                sadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdfsadfsdf
              </p>
            </div>
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

const seo = ref(useRoute().params.seo);

onMounted(async () => {
  await useFetch(
    `http://localhost:8080/api/v1/product/${seo.value}/update-view`,
    {
      method: "POST",
    }
  );
});

const { data } = await useFetch(
  `http://localhost:8080/api/v1/product/${seo.value}`
);

function numbertoprice(number: number) {
  const priceafterconvert = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(number);
  return priceafterconvert;
}

const quantity = ref(1);
</script>
