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
                <a
                  href="/"
                  class="text-xl hover:text-red-700"
                  @click="
                    () => {
                      store.logOut();
                    }
                  "
                >
                  Đăng xuất
                </a>
              </li>
            </ul>
          </div>
          <!-- Form -->
          <div class="w-full lg:w-3/4 px-6 py-6">
            <div class="bg-white p-6">
              <form>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold">Tên</label>
                  <input
                    type="text"
                    class="form-input mt-1 block w-full border-gray-200 border rounded"
                    v-model="infor.name"
                  />
                </div>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold">Ngày sinh</label>
                  <input
                    type="date"
                    placeholder="dd/MM/yyyy"
                    class="form-input mt-1 block w-full border-gray-200 border rounded"
                    v-model="infor.birth"
                  />
                </div>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold">Giới tính</label>
                  <select
                    class="form-input mt-1 block w-full border-gray-200 border rounded"
                    v-model="infor.gender"
                  >
                    <option>Nam</option>
                    <option>Nữ</option>
                  </select>
                </div>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold">Email</label>
                  <input
                    type="email"
                    class="form-input mt-1 block w-full border-gray-200 border rounded"
                    v-model="infor.email"
                  />
                </div>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold">Địa chỉ</label>
                  <input
                    type="text"
                    class="form-input mt-1 block w-full border-gray-200 border rounded"
                    v-model="infor.address"
                  />
                </div>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold"
                    >Số điện thoại</label
                  >
                  <input
                    type="tel"
                    class="form-input mt-1 block w-full border-gray-200 border rounded"
                    v-model="infor.phone"
                  />
                </div>
                <div class="mb-4">
                  <label class="text-gray-700 font-semibold"
                    >Chọn ảnh đại diện</label
                  >
                  <input
                    id="file"
                    type="file"
                    ref="file"
                    class="form-input mt-1 block w-full"
                    @change="handleFileUpload()"
                  />
                </div>
                <button
                  type="button"
                  class="px-6 py-2 bg-red-700 text-white rounded-md hover:bg-red-800"
                  @click="
                    () => {
                      submitFile();
                      updateInfor();
                    }
                  "
                >
                  Cập nhật
                </button>
              </form>
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

const { data: infor } = await useFetch(
  "http://localhost:8080/api/v1/shop/get-infor",
  {
    method: "POST",
    headers: {
      Authorization: "Bearer " + store.token,
    },
  }
);

const inforupdate = ref({
  name: infor.value.name,
  birth: infor.value.birth,
  gender: infor.value.gender,
  address: infor.value.address,
  email: infor.value.email,
  phone: infor.value.phone,
});

let oldInfor = ref({ ...infor.value });

watch(
  infor,
  (newInfor) => {
    if (JSON.stringify(newInfor) !== JSON.stringify(oldInfor.value)) {
      inforupdate.value = {
        name: newInfor.name,
        birth: newInfor.birth,
        gender: newInfor.gender,
        address: newInfor.address,
        email: newInfor.email,
        phone: newInfor.phone,
      };
      oldInfor.value = { ...newInfor };
    }
  },
  { deep: true }
);

const file = ref([]);

function handleFileUpload() {
  file.value = file.value.files;
}

async function submitFile() {
  let formData = new FormData();

  if (file.value.length > 0) {
    for (var i = 0; i < file.value.length; i++) {
      let fileItem = file.value[i];
      formData.append("image", fileItem);
    }
  }

  await useFetch(`http://localhost:8080/api/v1/shop/upload-avatar`, {
    method: "POST",
    headers: {
      Authorization: "Bearer " + store.token,
    },
    body: formData,
  });
}

async function updateInfor() {
  const response = await useFetch(
    "http://localhost:8080/api/v1/shop/change-infor",
    {
      method: "POST",
      headers: {
        Authorization: "Bearer " + store.token,
      },
      body: inforupdate.value,
    }
  );
  if (response.error) location.reload();
}
</script>
