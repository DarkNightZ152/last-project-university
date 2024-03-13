<template>
  <div>
    <!-- component -->
    <div class="transition-colors duration-300">
      <div class="container mx-auto p-4">
        <div class="bg-white shadow rounded-lg p-6">
          <form>
            <div class="flex mb-2">
              <h1 class="p-2 w-[200px] text-xl">Tên sản phẩm</h1>
              <input
                type="text"
                placeholder="Tên sản phẩm"
                class="border p-2 rounded w-[700px]"
                v-model="product.name"
              />
            </div>
            <div class="flex mb-2">
              <h1 class="p-2 w-[200px] text-xl">Phân loại</h1>
              <select
                id="countries"
                class="border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-[400px] p-2.5"
                v-model="product.category"
              >
                <option
                  v-for="item in category"
                  :key="item.id"
                  :value="item.name"
                >
                  {{ item.name }}
                </option>
              </select>
              <h1 class="p-2 w-[200px] text-xl ml-20">Hãng sản xuất</h1>
              <select
                id="countries"
                class="border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-[400px] p-2.5"
                v-model="product.manufactory"
              >
                <option
                  v-for="item in manufactory"
                  :key="item.id"
                  :value="item.name"
                >
                  {{ item.name }}
                </option>
              </select>
            </div>
            <div class="flex mb-4 mt-4">
              <h1 class="p-2 w-[200px] text-xl">Mô tả</h1>
              <textarea
                id="message"
                rows="4"
                class="block p-2.5 w-[700px] text-sm text-gray-900 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
                placeholder="Ghi mô tả vào đây..."
              ></textarea>
            </div>
            <div class="flex mb-2">
              <h1 class="p-2 w-[200px] text-xl">Mô tả chi tiết</h1>
              <textarea
                id="message"
                rows="4"
                class="block p-2.5 w-[700px] text-sm text-gray-900 rounded-lg border border-gray-300 focus:ring-blue-500 focus:border-blue-500"
                placeholder="Ghi mô tả chi tiết vào đây..."
              ></textarea>
            </div>
            <div class="flex mb-2">
              <h1 class="p-2 w-[200px] text-xl space-x-4">Giá tiền</h1>
              <input
                type="text"
                placeholder="Giá tiền"
                class="border p-2 rounded w-[200px]"
                v-model="product.price"
              />
              <h1 class="p-2 w-[200px] text-xl mb-2 mt-2 ml-4">
                Còn trong kho
              </h1>
              <input
                type="text"
                placeholder="Số lượng"
                class="border p-2 rounded w-[100px]"
                v-model="product.amount"
              />
              <h1 class="p-2 w-[150px] text-xl mb-2 mt-2 ml-10">
                Giảm giá (%)
              </h1>
              <input
                type="text"
                placeholder="Giảm giá"
                class="border p-2 rounded w-[100px]"
                v-model="product.discount"
              />
              <h1 class="p-2 w-[200px] text-xl mb-2 mt-2 ml-20">Bảo hành</h1>
              <input
                type="text"
                placeholder="Bảo hành"
                class="border p-2 rounded w-[100px]"
                v-model="product.guarantee"
              />
            </div>
            <div class="mb-4 mt-7">
              <label
                class="block text-sm font-medium text-black mb-2"
                for="file_input"
                >Đăng ảnh Product</label
              >
              <input
                class="block h-[35px] text-lg text-gray-900 cursor-pointer focus:outline-none"
                id="file"
                type="file"
                ref="file"
                multiple
                @change=""
              />
            </div>
            <div class="flex">
              <NuxtLink
                to="/admin/products"
                type="button"
                class="px-4 py-2 rounded bg-red-500 text-white hover:bg-red-600 focus:outline-none transition-colors ml-[1250px]"
              >
                Quay lại
              </NuxtLink>
              <button
                type="button"
                class="px-4 py-2 rounded bg-blue-500 text-white hover:bg-blue-600 focus:outline-none transition-colors ml-[25px]"
                @click="
                  () => {
                    addNew();
                  }
                "
              >
                Lưu lại
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
// @ts-nocheck
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});
const product = ref({
  name: "",
  category: "",
  manufactory: "",
  descriptrion: "",
  detail_des: "",
  amount: null,
  quality: "",
  price: null,
  discount: null,
  guarantee: null,
});

const { data: manufactory } = await useFetch(
  `http://localhost:8080/api/v1/shop/manufactory`
);

const { data: category } = await useFetch(
  `http://localhost:8080/api/v1/shop/category`
);

async function addNew() {
  await useFetch("http://localhost:8080/api/v1/shop/product/add", {
    method: "POST",
    body: JSON.stringify(product.value),
  });
  navigateTo("/admin/products");
}
</script>
