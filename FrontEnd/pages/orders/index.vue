<template>
  <div class="min-w-[1269px] max-w-[1269px] h-full mx-auto whitespace-nowrap">
    <div class="container mx-auto px-6 py-8">
      <h2 class="text-4xl font-bold text-gray-700 mb-8">Thông tin cá nhân</h2>
      <div class="h-[700px] w-full">
        <div class="flex flex-wrap mx-6">
          <!-- Sidebar -->
          <div
            class="h-[700px] w-full lg:w-1/4 px-6 flex flex-col items-start justify-start border-r-2"
          >
            <ul class="space-y-4">
              <li>
                <NuxtLink class="text-xl hover:text-blue-500" to="/account"
                  >Thông tin cá nhân</NuxtLink
                >
              </li>
              <li>
                <NuxtLink class="text-xl hover:text-blue-500" to="/orders"
                  >Lịch sử đơn hàng</NuxtLink
                >
              </li>
              <li>
                <NuxtLink class="text-xl hover:text-blue-500" to="/password"
                  >Đổi mật khẩu</NuxtLink
                >
              </li>
              <li>
                <NuxtLink
                  class="text-xl hover:text-blue-500"
                  to="/admin"
                  v-show="store.authState.isStaff"
                  >Khu vực staff</NuxtLink
                >
              </li>
              <li>
                <button
                  class="text-xl hover:text-red-700"
                  @click="
                    () => {
                      store.logOut();
                    }
                  "
                >
                  Đăng xuất
                </button>
              </li>
            </ul>
          </div>
          <!-- Content -->
          <div class="w-full lg:w-3/4 px-6 py-2">
            <div>
              <div class="bg-white space-y-2">
                <div v-for="item in data">
                  <NuxtLink
                    :to="`/orders/${item.id}`"
                    class="flex items-center justify-between p-4 bg-white shadow-md rounded-md"
                  >
                    <div class="flex items-center">
                      <img
                        class="w-16 h-16 object-cover rounded-md"
                        src="/default-product-image.png"
                        alt="Product Image"
                      />
                      <div class="ml-4">
                        <h2 class="text-lg font-semibold text-gray-800">
                          Đơn hàng mã: #{{ item.id }}
                        </h2>
                      </div>
                    </div>
                    <div class="text-right">
                      <p
                        class="text-sm flex items-center"
                        :class="`text-[${item.status.color}]`"
                      >
                        {{ item.status.status }}
                      </p>

                      <p class="mt-2 text-lg font-semibold text-red-600">
                        {{ numbertoprice(item.total) }}
                      </p>
                    </div>
                  </NuxtLink>
                </div>
              </div>
            </div>
            <div class="mt-[400px]">
              <!-- Thanh phân trang -->
              <div class="flex justify-center space-x-2">
                <!-- Nút "Previous" -->
                <button
                  @click="prevPage"
                  :disabled="page <= 1"
                  class="px-4 py-2 text-white bg-blue-500 rounded hover:bg-blue-700"
                >
                  Previous
                </button>

                <!-- Hiển thị tất cả các trang -->
                <button
                  v-for="pageNumber in maxPage"
                  :key="pageNumber"
                  @click="goToPage(pageNumber)"
                  :class="`px-4 py-2 rounded ${
                    page === pageNumber
                      ? 'bg-blue-500 text-white'
                      : 'bg-white text-blue-500'
                  }`"
                >
                  {{ pageNumber }}
                </button>

                <!-- Nút "Next" -->
                <button
                  @click="nextPage"
                  :disabled="page >= maxPage"
                  class="px-4 py-2 text-white bg-blue-500 rounded hover:bg-blue-700"
                >
                  Next
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
// @ts-nocheck
definePageMeta({
  middleware: "check-login",
});
const store = useAuthStore();

const userOrders = ref();

const { data } = await useFetch("http://localhost:8080/api/v1/shop/orders", {
  method: "GET",
  headers: {
    Authorization: "Bearer " + store.token,
  },
});
userOrders.value = data.value;

//Phân trang
const number = [2, 4, 10];
const page = ref(1);
const pageCount = ref(number[1]);

const paginatedOrders = computed(() => {
  // Lấy danh sách userOrders đã được lọc
  const filtered = userOrders.value;

  // Tính chỉ số bắt đầu và kết thúc cho phân trang
  const start = (page.value - 1) * pageCount.value;
  const end = start + pageCount.value;

  // Trả về phần của danh sách userOrders cho trang hiện tại
  return filtered.slice(start, end);
});

const maxPage = computed(() => {
  return Math.ceil(paginatedOrders.value.length / pageCount.value);
});

const prevPage = () => {
  if (page.value > 1) {
    page.value--;
  }
};

const nextPage = () => {
  if (page.value < maxPage.value) {
    page.value++;
  }
};

const goToPage = (pageNumber: number) => {
  page.value = pageNumber;
};

function numbertoprice(number: number) {
  const priceafterconvert = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(number);
  return priceafterconvert;
}
</script>
