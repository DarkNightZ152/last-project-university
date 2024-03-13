<template>
  <div>
    <div class="flex items-center p-4">
      <!-- Thanh tìm kiếm -->
      <div class="flex items-center">
        <input
          type="text"
          placeholder="Tìm kiếm..."
          class="px-4 py-2 rounded-lg border-2 border-gray-300 focus:outline-none focus:border-blue-500"
          v-model="search"
        />
      </div>
    </div>
    <div class="relative overflow-x-auto sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-900 rtl:text-right">
        <thead class="text-sm uppercase border-b">
          <tr>
            <th scope="col" class="px-2 py-3">ID</th>
            <th scope="col" class="px-2 py-3">Tài khoản</th>
            <th scope="col" class="px-6 py-3">Tên người dùng</th>
            <th scope="col" class="px-6 py-3">Email</th>
            <th scope="col" class="px-6 py-3">Số điện thoại</th>
            <th scope="col" class="px-6 py-3">Địa chỉ</th>
            <th scope="col" class="px-6 py-3">Ngày sinh</th>
            <th scope="col" class="px-6 py-3">Giới tính</th>
            <th scope="col" class="px-6 py-3">Vai trò</th>
            <th scope="col" class="px-6 py-3">Ngày tham gia</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="bg-white border-b hover:bg-gray-50"
            v-for="item in paginated"
          >
            <td class="px-2 py-4">{{ item.id }}</td>
            <td class="px-6 py-4">{{ item.username }}</td>
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap"
            >
              {{ item.name }}
            </th>
            <td class="px-6 py-4">{{ item.email }}</td>
            <td class="px-6 py-4">{{ item.phone }}</td>
            <td class="px-6 py-4">{{ item.address }}</td>
            <td class="px-6 py-4">{{ formatDate(item.birth) }}</td>
            <td class="px-6 py-4">{{ item.gender }}</td>
            <td class="px-6 py-4">{{ item.role }}</td>
            <td class="px-6 py-4">{{ formatDate(item.joinIn) }}</td>
          </tr>
        </tbody>
      </table>
      <nav
        class="flex items-center flex-column flex-wrap md:flex-row justify-between pt-4"
        aria-label="Table navigation"
      >
        <span
          class="text-sm font-normal text-gray-500 mb-4 md:mb-0 block w-full md:inline md:w-auto"
          >Đang hiển thị
          <span class="font-semibold text-gray-900">1-10</span>
          trong tổng
          <span class="font-semibold text-gray-900">{{
            filtered.length
          }}</span></span
        >
        <ul class="inline-flex -space-x-px rtl:space-x-reverse text-sm h-8">
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
        </ul>
      </nav>
    </div>
  </div>
</template>
<script lang="ts" setup>
// @ts-nocheck
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});
const test = useTest();

// const result = await test.getdata("shop/manufactory");

const { data: customers } = await useFetch(
  "http://localhost:8080/api/v1/shop/get-all-customer"
);

const search = ref(); // Giá trị tìm kiếm

//Phân trang
const number = [2, 5, 10];
const page = ref(1);
const pageCount = ref(number[2]);

// Danh sách  được lọc
const filtered = computed(() => {
  if (!search.value) {
    // Nếu không có giá trị tìm kiếm, trả về toàn bộ danh sách
    return customers.value;
  }

  // Chuyển giá trị tìm kiếm thành chữ thường
  const searchValue = search.value.toLowerCase();

  // Lọc  dựa trên giá trị tìm kiếm
  return customers.value.filter((item: any) =>
    item.name.toLowerCase().includes(searchValue)
  );
});

const paginated = computed(() => {
  // Lấy danh sách  đã được lọc
  const filter = filtered.value;

  // Tính chỉ số bắt đầu và kết thúc cho phân trang
  const start = (page.value - 1) * pageCount.value;
  const end = start + pageCount.value;

  // Trả về phần của danh sách  cho trang hiện tại
  return filter.slice(start, end);
});

const maxPage = computed(() => {
  return Math.ceil(filtered.value.length / pageCount.value);
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
</script>
