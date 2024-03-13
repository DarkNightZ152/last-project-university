<template>
  <div>
    <div class="flex min-w-[1269px] max-w-[1269px] h-[1100px] mx-auto py-8">
      <div class="w-[274.56px] h-full border-r-[3px]">
        <div class="mt-5 justify-center flex">
          <h1 class="text-lg font-bold">Loại sản phẩm</h1>
        </div>
        <div class="py-3 px-10">
          <div v-for="(item, index) in categories" :key="index">
            <div class="mb-[0.5rem] block min-h-[1.5rem] pl-[1.5rem]">
              <input
                class="mr-2"
                type="checkbox"
                :value="item.name"
                @change="updateSearch($event, item.name)"
              />
              <label class="inline-block pl-[0.15rem] hover:cursor-pointer">
                {{ item.name }}
              </label>
            </div>
          </div>
        </div>

        <div class="py-2 justify-center flex">
          <h1 class="text-lg font-bold">Theo giá tiền</h1>
        </div>
        <div class="px-4 py-3 flex justify-between">
          <input
            type="number"
            min="0"
            class="w-[85px] border rounded border-black"
            v-model.number="priceFrom"
          />
          <h1>Đến</h1>
          <input
            type="number"
            min="0"
            class="w-[85px] border rounded border-black"
            v-model.number="priceTo"
          />
        </div>
      </div>
      <div>
        <div class="h-[1000px]">
          <div class="w-[994.44px] h-full justify-center flex ml-5">
            <div class="py-4 grid grid-cols-4 gap-4">
              <!-- Sử dụng v-for để lặp qua danh sách sản phẩm -->
              <ShopProductCard
                v-for="product in paginatedProducts"
                :name="product.name"
                :price="product.price"
                :img="product.imagesurl"
                :seo="product.seo"
                @add-to-cart="test.addToCart(store.token, product.seo, 1)"
              />
            </div>
          </div>
          <div>
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
</template>
<script lang="ts" setup>
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
products.value = data.value;

const search = ref("");

function updateSearch(event: any, name: string) {
  if (event.target.checked) {
    search.value = name;
  } else {
    search.value = "";
  }
}

const priceFrom = ref<number>(0);
const priceTo = ref<number>(Infinity);

//Phân trang
const number = [2, 5, 12];
const page = ref(1);
const pageCount = ref(number[2]);

// Danh sách products được lọc
const filteredProducts = computed(() => {
  // Chuyển giá trị tìm kiếm thành chữ thường
  const searchValue = search.value.toLowerCase();

  // Lọc products dựa trên giá trị tìm kiếm và khoảng giá
  return products.value.filter((product: any) => {
    const isInPriceRange =
      product.price >= priceFrom.value && product.price <= priceTo.value;
    const isCategoryMatch = product.category
      .toLowerCase()
      .includes(searchValue);

    // Nếu có giá trị tìm kiếm và giá, áp dụng cả hai điều kiện
    if (search.value && (priceFrom.value || priceTo.value)) {
      return isCategoryMatch && isInPriceRange;
    }
    // Nếu chỉ có giá trị tìm kiếm, chỉ áp dụng điều kiện danh mục
    else if (search.value) {
      return isCategoryMatch;
    }
    // Nếu chỉ có giá, chỉ áp dụng điều kiện giá
    else if (priceFrom.value || priceTo.value) {
      return isInPriceRange;
    }
    // Nếu không có giá trị tìm kiếm hoặc giá, trả về tất cả sản phẩm
    else {
      return true;
    }
  });
});

const paginatedProducts = computed(() => {
  // Lấy danh sách manufactories đã được lọc
  const filtered = filteredProducts.value;

  // Tính chỉ số bắt đầu và kết thúc cho phân trang
  const start = (page.value - 1) * pageCount.value;
  const end = start + pageCount.value;

  // Trả về phần của danh sách manufactories cho trang hiện tại
  return filtered.slice(start, end);
});

const maxPage = computed(() => {
  return Math.ceil(products.value.length / pageCount.value);
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
