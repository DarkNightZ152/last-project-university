<template>
  <div>
    <aside class="min-w-64 max-w-64 bg-white">
      <!-- Logo and Shop Name -->
      <NuxtLink to="/">
        <div class="flex items-center justify-center py-4">
          <!-- Your shop logo -->
          <img src="/favicon.ico" alt="Shop Logo" class="h-8" />
          <!-- Your shop name -->
          <h1 class="ml-2 text-lg font-semibold">NONPStore</h1>
        </div>
      </NuxtLink>
      <!-- Menu -->
      <div class="py-4 px-4">
        <NuxtLink to="/admin/dashboard">
          <li
            class="py-2 px-4 mb-4 text-gray-700 hover:bg-gray-200 flex items-center"
          >
            <img
              src="https://png.pngtree.com/png-vector/20230302/ourmid/pngtree-dashboard-line-icon-vector-png-image_6626604.png"
              alt="Dashboard"
              class="h-6 w-6 mr-2"
            />
            <span>Bảng điều khiển</span>
          </li>
        </NuxtLink>
        <div class="flex items-center">
          <div class="w-full border-b border-gray-300"></div>
          <div class="px-4 text-gray-600">Chức năng</div>
          <div class="w-full border-b border-gray-300"></div>
        </div>
        <div class="mb-2"></div>
        <!-- Menu items -->
        <nav>
          <ul>
            <NuxtLink to="/admin/users">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://cdn-icons-png.flaticon.com/512/3126/3126647.png"
                  alt="Customer"
                  class="h-6 w-6 mr-2"
                />
                <span>Khách hàng</span>
              </li>
            </NuxtLink>
            <NuxtLink to="/admin/staff" v-show="store.authState.isAdmin">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://cdn-icons-png.flaticon.com/512/251/251073.png"
                  alt="Employee"
                  class="h-6 w-6 mr-2"
                />
                <span>Nhân viên</span>
              </li>
            </NuxtLink>
            <NuxtLink to="/admin/category">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://cdn-icons-png.flaticon.com/512/5665/5665189.png"
                  alt="Category"
                  class="h-6 w-6 mr-2"
                />
                <span>Phân loại</span>
              </li>
            </NuxtLink>
            <NuxtLink to="/admin/manufactory">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://www.iconpacks.net/icons/1/free-factory-icon-1067-thumb.png"
                  alt="Manufacturer"
                  class="h-6 w-6 mr-2"
                />
                <span>Hãng sản xuất</span>
              </li>
            </NuxtLink>
            <NuxtLink to="/admin/products">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://uxwing.com/wp-content/themes/uxwing/download/business-professional-services/product-icon.png"
                  alt="Product"
                  class="h-6 w-6 mr-2"
                />
                <span>Sản phẩm</span>
              </li>
            </NuxtLink>
            <NuxtLink to="/admin/bills">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://uxwing.com/wp-content/themes/uxwing/download/e-commerce-currency-shopping/orders-icon.png"
                  alt="Product"
                  class="h-6 w-6 mr-2"
                />
                <span>Đơn hàng</span>
              </li>
            </NuxtLink>
            <NuxtLink to="/admin/report">
              <li
                class="py-2 px-4 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <img
                  src="https://cdn-icons-png.flaticon.com/512/87/87578.png"
                  alt="Manufacturer"
                  class="h-6 w-6 mr-2"
                />
                <span>Thống kê</span>
              </li>
            </NuxtLink>
            <div class="mb-2"></div>
            <div class="flex items-center">
              <div class="w-full border-b border-gray-300"></div>
              <div class="px-4 text-gray-600">Khác</div>
              <div class="w-full border-b border-gray-300"></div>
            </div>
            <div class="mb-2"></div>
            <NuxtLink to="/admin/test">
              <li
                class="py-2 px-3 text-gray-700 hover:bg-gray-200 flex items-center"
              >
                <svg
                  xmlns="http://www.w3.org/2000/svg"
                  width="32"
                  height="32"
                  viewBox="0 0 24 24"
                  class="mr-1"
                >
                  <path
                    fill="currentColor"
                    d="M8.808 6.692v4.058q0 .192.125.317t.316.125t.317-.125q.126-.125.126-.317v-4.5q0-.192-.125-.317t-.317-.125h-1.5q-.192 0-.317.125t-.125.316t.125.317q.125.126.317.126h1.058Zm4.384 3.616V8.885h2.231q.327 0 .548-.222q.221-.22.221-.548V6.577q0-.327-.22-.548q-.222-.221-.549-.221H12.75q-.192 0-.317.125t-.125.316t.125.317q.125.126.317.126h2.558v1.423h-2.231q-.327 0-.548.222q-.221.22-.221.548v1.865q0 .192.125.317t.317.125h3q.192 0 .317-.125t.125-.316t-.125-.317q-.125-.126-.317-.126h-2.558ZM5.615 20q-.69 0-1.152-.462Q4 19.075 4 18.385V5.615q0-.69.463-1.152Q4.925 4 5.615 4h12.77q.69 0 1.152.463q.463.462.463 1.152v12.77q0 .69-.462 1.152q-.463.463-1.153.463H5.615Zm0-1h12.77q.23 0 .423-.192q.192-.193.192-.423V5.615q0-.23-.192-.423Q18.615 5 18.385 5H5.615q-.23 0-.423.192Q5 5.385 5 5.615v12.77q0 .23.192.423q.193.192.423.192ZM5 5v14V5Zm2.192 8.692h1.616v2.558q0 .192.125.317t.316.125t.317-.125q.126-.125.126-.317v-2.558h1.616v4.058q0 .192.125.317t.316.125t.317-.125q.126-.125.126-.317V13.5q0-.31-.191-.501t-.501-.191H7q-.31 0-.501.191t-.191.501v4.25q0 .192.125.317t.316.125t.317-.125q.126-.125.126-.317v-4.058Zm7.5 3H17q.31 0 .501-.191t.191-.501v-2.5q0-.31-.191-.501T17 12.808h-2.75q-.192 0-.317.125t-.125.317v4.5q0 .192.125.317t.316.125t.317-.125q.126-.125.126-.317v-1.058Zm0-.884v-2.116h2.116v2.116h-2.116Z"
                  />
                </svg>
                <span>Testing</span>
              </li>
            </NuxtLink>
            <!-- Add other menu items here -->
          </ul>
        </nav>
      </div>
    </aside>
  </div>
</template>

<script lang="ts" setup>
const store = useAuthStore();
</script>
