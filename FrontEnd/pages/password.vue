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
          <!-- Form -->
          <div class="w-full lg:w-3/4 px-6 py-6">
            <div class="bg-white p-6"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
definePageMeta({
  middleware: "check-login",
});
const store = useAuthStore();
</script>
