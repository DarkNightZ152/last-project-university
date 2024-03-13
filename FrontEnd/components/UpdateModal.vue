<template>
  <div
    v-if="isModalOpen"
    class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50 z-50"
  >
    <div
      class="bg-white p-6 rounded shadow-md min-w-[500px] max-w-[1269px] relative"
    >
      <button
        @click="handleCancel"
        class="absolute top-2 right-4 text-black text-xl font-semibold"
      >
        x
      </button>
      <div v-for="(value, key) in data" :key="key">
        <div>
          <h1 class="text-xl font-normal">{{ key }}:</h1>
          <input
            type="text"
            :placeholder="value"
            class="p-2 rounded w-full"
            v-model="data[value]"
          />
        </div>
      </div>
      <div class="flex justify-end">
        <button
          @click="handleCancel"
          class="px-4 py-2 mr-2 bg-red-500 text-white rounded"
        >
          Thoát
        </button>
        <button
          @click="handleConfirm"
          class="px-4 py-2 bg-green-500 text-white rounded"
        >
          Lưu
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  data: {
    type: Object,
    required: false,
  },
  isModalOpen: {
    type: Boolean,
    default: false,
    required: true,
  },
  message: {
    type: String,
    default: "Nếu không có input sẽ mặc định như này!",
    required: false,
  },
});

const emit = defineEmits(["update:isModalOpen", "confirm"]);

const handleConfirm = () => {
  emit("confirm");
  emit("update:isModalOpen", false);
};

const handleCancel = () => {
  emit("update:isModalOpen", false);
};
</script>
