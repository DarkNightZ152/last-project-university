<template>
  <div
    class="fixed z-10 inset-0 overflow-y-auto"
    aria-labelledby="modal-title"
    role="dialog"
    aria-modal="true"
    v-show="isModalOpen"
  >
    <div
      class="flex items-end justify-center min-h-screen pt-4 px-4 pb-20 text-center sm:block sm:p-0"
    >
      <div
        class="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"
        aria-hidden="true"
      ></div>
      <span
        class="hidden sm:inline-block sm:align-middle sm:h-screen"
        aria-hidden="true"
        >&#8203;</span
      >
      <div
        class="inline-block align-bottom bg-white rounded-lg text-left overflow-hidden shadow-xl transform transition-all sm:my-8 sm:align-middle sm:max-w-lg sm:w-full"
      >
        <div class="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
          <div class="sm:flex sm:items-start">
            <div class="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
              <h3
                class="text-lg leading-6 font-medium text-gray-900"
                id="modal-title"
              >
                Đơn hàng của {{ customer_name }}
              </h3>
              <div class="mt-2">
                <p class="text-sm text-gray-500">
                  Email: {{ email }} <br />
                  Số điện thoại: {{ phone }} <br />
                  Địa chỉ: {{ address }} <br />
                  Tổng tiền: {{ total }} <br />
                  Tổng tiền sau giảm giá: {{ totalAfterDiscount }} <br />
                  Trạng thái: {{ status.status }} <br />
                  Mô tả trạng thái: {{ status.description }} <br />
                </p>
                <div class="mt-4">
                  <h4 class="text-lg leading-6 font-medium text-gray-900">
                    Sản phẩm:
                  </h4>
                  <ul>
                    <li v-for="(item, index) in billProduct" :key="index">
                      Tên sản phẩm: {{ item.product.name }} <br />
                      Số lượng: {{ item.quantity }} <br />
                      Giá: {{ item.price }} <br />
                      Tổng: {{ item.total }} <br />
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
          <button
            type="button"
            class="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500 sm:ml-3 sm:w-auto sm:text-sm"
          >
            Đóng
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
// @ts-nocheck
const props = defineProps({
  isModalOpen: Boolean,
  customer_name: String,
  email: String,
  phone: String,
  address: String,
  billProduct: Array,
  total: Number,
  totalAfterDiscount: Number,
  status: Object,
});
</script>
