<template>
  <div
    class="bg-gray-50 hover:bg-gray-100 rounded-lg shadow-md w-[230px] h-[290px]"
    @mouseenter="showButtons = true"
    @mouseleave="showButtons = false"
  >
    <!-- Phần ảnh sản phẩm (4/6 chiều cao) -->
    <div
      class="relative h-[184px] mb-5 p-2 overflow-hidden shadow-md rounded-t-lg bg-white"
    >
      <img
        :src="img"
        alt="Product"
        class="w-full h-full object-cover rounded-md"
      />
      <!-- Hiển thị button khi hover -->
      <div
        v-if="showButtons"
        class="absolute inset-0 flex items-center justify-center"
      >
        <div class="mt-[100px]">
          <button
            @click="handleAddToCart()"
            class="text-white px-4 py-2 rounded mr-2 bg-black bg-opacity-50 hover:bg-gray-800"
          >
            Đặt hàng
          </button>
          <NuxtLink
            :to="`/products/${seo}`"
            class="text-white px-4 py-2 rounded bg-black bg-opacity-50 hover:bg-gray-800"
          >
            Chi tiết
          </NuxtLink>
        </div>
      </div>
    </div>

    <!-- Phần thông tin sản phẩm -->
    <div class="h-[86px] flex-col justify-between rounded-b-lg">
      <!-- Phần tên sản phẩm (canh giữa dọc) -->
      <div class="text-center mb-2">
        <h2 class="text-base font-semibold line-clamp-1 px-3">{{ name }}</h2>
      </div>

      <!-- Phần giá sản phẩm (canh giữa dọc) -->
      <div class="text-center">
        <span class="text-gray-600 text-lg">{{ numbertoprice(price) }}</span>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";

const props = defineProps({
  name: { type: String, required: true },
  price: { type: Number, required: true },
  img: { type: String, default: "/default-product-image.png", required: false },
  seo: { type: String, required: false },
});

const emit = defineEmits(["addToCart"]);

const handleAddToCart = () => {
  emit("addToCart");
};

const showButtons = ref(false);

function numbertoprice(number: number) {
  const priceafterconvert = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(number);
  return priceafterconvert;
}
</script>
