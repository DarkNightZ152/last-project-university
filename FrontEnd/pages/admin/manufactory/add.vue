<template>
  <div>
    <!-- component -->
    <div class="transition-colors duration-300">
      <div class="container mx-auto p-4">
        <div class="bg-white shadow rounded-lg p-6">
          <form>
            <div class="flex mb-2">
              <h1 class="p-2 w-[200px] text-xl">Tên hãng sản xuất</h1>
              <input
                type="text"
                placeholder="Tên hãng sản xuất"
                class="border p-2 rounded w-[700px]"
                v-model="manufactory.name"
              />
            </div>

            <div class="flex">
              <NuxtLink
                to="/admin/manyfactory"
                type="button"
                class="px-4 py-2 rounded bg-red-500 text-white hover:bg-red-600 focus:outline-none transition-colors ml-[1250px]"
              >
                Quay lại
              </NuxtLink>
              <button
                type="button"
                class="px-4 py-2 rounded bg-blue-500 text-white hover:bg-blue-600 focus:outline-none transition-colors ml-[25px]"
                @click="
                  () => {
                    addNew();
                  }
                "
              >
                Lưu lại
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
// @ts-nocheck
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});
const manufactory = ref({
  name: "",
});

async function addNew() {
  await useFetch("http://localhost:8080/api/v1/shop/manufactory/add", {
    method: "POST",
    body: JSON.stringify(manufactory.value),
  });
  navigateTo("/admin/manufactory");
}
</script>
