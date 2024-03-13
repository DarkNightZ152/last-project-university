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
            <div class="text-center sm:text-left">
              <h3 class="text-lg leading-6 font-medium text-gray-900">
                Đơn hàng mã: #{{ bill.id }}
              </h3>
              <div class="mt-2">
                <p class="text-sm text-gray-500">
                  Email: {{ bill.email }} <br />
                  Số điện thoại: {{ bill.phone }} <br />
                  Địa chỉ: {{ bill.address }} <br />
                  Tổng tiền: {{ bill.total }} <br />
                  Tổng tiền sau giảm giá: {{ bill.totalAfterDiscount }}
                  <br />
                  Trạng thái: {{ bill.status.status }} <br />
                  {{ bill.status.description }} {{ bill.reason }} <br />
                </p>
                <div class="mt-4">
                  <h4 class="text-lg leading-6 font-medium text-gray-900">
                    Sản phẩm:
                  </h4>
                  <ul class="space-y-3">
                    <li
                      v-for="(item, index) in paginatedBillProduct"
                      :key="index"
                      class="bg-slate-300 rounded"
                    >
                      Tên sản phẩm: {{ item.productname }} <br />
                      Số lượng: {{ item.quantity }} <br />
                      Giá: {{ item.price }} <br />
                      Tổng: {{ item.total }} <br />
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <div class="mt-[130px] justify-center flex space-x-4">
              <!-- Thanh phân trang -->
              <div class="flex justify-center space-x-2">
                <!-- Nút "Previous" -->
                <button
                  @click="prevPage"
                  :disabled="page <= 1"
                  class="px-4 py-2 text-white bg-blue-500 rounded hover:bg-blue-700"
                >
                  Trước
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
                  Sau
                </button>
              </div>
              <button
                class="mt-2 px-4 py-2 bg-red-600 text-white text-sm rounded-md hover:bg-red-700"
                @click="isAcceptModelOpen = true"
                :disabled="isDisable"
              >
                Hủy đơn hàng
              </button>
              <AcceptModel
                :is-modal-open="isAcceptModelOpen"
                message="Bạn có chắc là muốn hủy?"
                @update:isModalOpen="isAcceptModelOpen, $event"
                @confirm="cancelOrder()"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// @ts-nocheck
definePageMeta({
  middleware: "check-login",
});
const store = useAuthStore();

const billID = useRoute().params.id;

// const bill = ref();

const isDisable = ref(false);

const { data:bill } = await useFetch(
  `http://localhost:8080/api/v1/shop/orders/${billID}`
);

// bill.value = data.value;

if (bill.value.status.id >= 2) {
  isDisable.value = true;
}

//Phân trang
const number = [2, 4, 10];
const page = ref(1);
const pageCount = ref(number[1]);

const paginatedBillProduct = computed(() => {
  // Lấy danh sách userOrders đã được lọc
  const filtered = bill.value.billProduct;

  // Tính chỉ số bắt đầu và kết thúc cho phân trang
  const start = (page.value - 1) * pageCount.value;
  const end = start + pageCount.value;

  // Trả về phần của danh sách userOrders cho trang hiện tại
  return filtered.slice(start, end);
});

console.log(paginatedBillProduct.value);

const maxPage = computed(() => {
  return Math.ceil(paginatedBillProduct.value.length / pageCount.value);
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

const isAcceptModelOpen = ref(false);

async function cancelOrder() {
  await useFetch(`http://localhost:8080/api/v1/shop/orders/${billID}`, {
    method: "PUT",
  });
  navigateTo("/orders");
}
</script>
