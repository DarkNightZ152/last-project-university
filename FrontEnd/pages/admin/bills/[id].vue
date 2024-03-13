<template>
  <div>
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
            Tổng tiền sau giảm giá: {{ bill.totalAfterDiscount }} <br />
            Ngày tạo: {{ formatDate(bill.created_on) }} <br />
            Kết thúc vào: {{ formatDate(bill.end_on) }} <br />
            Trạng thái:
            <span class="rounded px-2 text-sm">{{ bill.status.status }}</span>
            <br />
            {{ bill.status.description }} {{ bill.reason }} <br />
          </p>
          <div class="mt-4">
            <h4 class="text-lg leading-6 font-medium text-gray-900 mb-2">
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
      </div>
    </div>
    <div class="flex space-x-12">
      <div v-for="item in status">
        <button
          class="mt-2 px-4 py-2 text-sm rounded-md text-white font-bold bg-slate-500"
          :disabled="checkDisabled(item.id)"
          @click="handleCancelClick(item.id, item.status)"
        >
          {{ item.status }}
        </button>
      </div>
    </div>
    <AcceptCancelBillModal
      :is-modal-open="isAcceptModelOpen"
      message="Bạn có chắc là muốn hủy?"
      @update:isModalOpen="isAcceptModelOpen, $event"
      @update:reason="updateReason"
      @confirm=""
    />
  </div>
</template>
<script setup>
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});

const billID = useRoute().params.id;

const bill = ref();

const isDisable = ref(false);

const { data } = await useFetch(
  `http://localhost:8080/api/v1/shop/orders/${billID}`
);

bill.value = data.value;

if (bill.value.status.id == 2) {
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

const goToPage = (pageNumber) => {
  page.value = pageNumber;
};

const isAcceptModelOpen = ref(false);

const { data: status } = await useFetch(
  "http://localhost:8080/api/v1/shop/bills/status"
);

function checkDisabled(id) {
  switch (bill.value.status.id) {
    case 1:
      return id !== 2 && id !== 3;
    case 2:
    case 7:
    case 8:
      return true;
    case 3:
      return id !== 4;
    case 4:
      return id !== 5 && id !== 6;
    case 5:
      return id !== 8;
    case 6:
      return id !== 7;
    default:
      return false;
  }
}
const statusName = ref();
const updateReason = ref();

function handleCancelClick(id, status) {
  statusName.value = status;
  //   if (id === 2) {
  //     isAcceptModelOpen.value = true;
  //   }
  updateBill();
}

async function updateBill() {
  await useFetch(`http://localhost:8080/api/v1/shop/bills/${billID}`, {
    method: "PUT",
    body: {
      status: statusName.value,
      reason: updateReason.value,
    },
  });
  location.reload();
}
</script>
