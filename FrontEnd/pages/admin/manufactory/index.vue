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

      <!-- Nút thêm mới và xóa -->
      <div class="flex items-center">
        <NuxtLink
          to="/admin/manufactory/add"
          class="ml-2 bg-green-500 text-white px-4 py-2 rounded-lg hover:bg-green-600"
        >
          Thêm mới
        </NuxtLink>
        <button
          class="ml-2 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600"
        >
          Xóa
        </button>
      </div>
    </div>
    <div class="relative overflow-x-auto sm:rounded-lg">
      <table class="w-full text-sm text-left text-gray-900 rtl:text-right">
        <thead class="text-sm uppercase border-b">
          <tr>
            <th scope="col" class="p-4">
              <div class="flex items-center">
                <input
                  id="checkbox-all-search"
                  type="checkbox"
                  class="w-4 h-4 rounded text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500"
                  @click="toggleAllCheckboxes"
                />
                <label for="checkbox-all-search" class="sr-only"
                  >checkbox</label
                >
              </div>
            </th>
            <th scope="col" class="px-2 py-3">ID</th>
            <th scope="col" class="px-6 py-3">Hãng sản xuất</th>
            <th scope="col" class="px-6 py-3">SEO</th>
            <th scope="col" class="px-6 py-3">Hành động</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="bg-white border-b hover:bg-gray-50"
            v-for="manufactory in paginatedManufactories"
            :key="manufactory.id"
          >
            <td class="w-4 p-4">
              <div class="flex items-center">
                <input
                  id="checkbox-table-search-1"
                  type="checkbox"
                  class="w-4 h-4 rounded text-blue-600 bg-gray-100 border-gray-300 focus:ring-blue-500"
                  :checked="isAllChecked"
                  :value="manufactory.id"
                  v-model="checkedManufactories"
                />
                <label for="checkbox-table-search-1" class="sr-only"
                  >checkbox</label
                >
              </div>
            </td>
            <td class="px-2 py-4">{{ manufactory.id }}</td>
            <th
              scope="row"
              class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap"
            >
              {{ manufactory.name }}
            </th>
            <td class="px-6 py-4">{{ manufactory.seo }}</td>
            <td class="px-6 py-4 space-x-10">
              <button class="font-medium text-blue-600 hover:underline">
                Sửa
              </button>
              <button class="font-medium text-red-600 hover:underline">
                Xóa
              </button>
            </td>
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
            filteredManufactories.length
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

const { data: manufactories } = await useFetch(
  "http://localhost:8080/api/v1/shop/manufactory"
);

const checkedManufactories = ref([]);

const search = ref(""); // Giá trị tìm kiếm

//Phân trang
const number = [2, 5, 10];
const page = ref(1);
const pageCount = ref(number[2]);

// Danh sách manufactories được lọc
const filteredManufactories = computed(() => {
  if (!search.value) {
    // Nếu không có giá trị tìm kiếm, trả về toàn bộ danh sách
    return manufactories.value;
  }

  // Chuyển giá trị tìm kiếm thành chữ thường
  const searchValue = search.value.toLowerCase();

  // Lọc manufactories dựa trên giá trị tìm kiếm
  return manufactories.value.filter((manufactory: any) =>
    manufactory.name.toLowerCase().includes(searchValue)
  );
});

const paginatedManufactories = computed(() => {
  // Lấy danh sách manufactories đã được lọc
  const filtered = filteredManufactories.value;

  // Tính chỉ số bắt đầu và kết thúc cho phân trang
  const start = (page.value - 1) * pageCount.value;
  const end = start + pageCount.value;

  // Trả về phần của danh sách manufactories cho trang hiện tại
  return filtered.slice(start, end);
});

const maxPage = computed(() => {
  return Math.ceil(filteredManufactories.value.length / pageCount.value);
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

// Tạo một biến reactive để lưu trạng thái của checkbox ở thead
const isAllChecked = ref(false);

// Hàm để xử lý sự kiện khi checkbox ở thead được tick hoặc untick
const toggleAllCheckboxes = () => {
  isAllChecked.value = !isAllChecked.value;
  const tbodyCheckboxes = document.querySelectorAll<HTMLInputElement>(
    "#checkbox-table-search"
  );

  // Duyệt qua tất cả checkbox ở tbody và cập nhật trạng thái dựa trên checkbox ở thead
  tbodyCheckboxes.forEach((checkbox) => {
    checkbox.checked = isAllChecked.value;
  });
};
</script>
