<template>
  <div
    v-if="isModalOpen"
    class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50 z-50"
  >
    <div class="bg-white p-6 rounded shadow-md w-80">
      <p class="text-lg mb-4">{{ message }}</p>
      <input type="text" v-model="inputReason" />
      <div class="flex justify-end">
        <button
          @click="handleCancel"
          class="px-4 py-2 mr-2 bg-red-500 text-white rounded"
        >
          Không
        </button>
        <button
          @click="handleConfirm"
          class="px-4 py-2 bg-green-500 text-white rounded"
        >
          Có
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
let inputReason = ref("");

const props = defineProps({
  message: {
    type: String,
    default: "Nếu không có input sẽ mặc định như này!",
    required: false,
  },
  isModalOpen: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(["update:isModalOpen", "confirm", "update:reason"]);

watchEffect(() => {
  if (inputReason.value) {
    emit("update:reason", inputReason.value);
  }
});

const handleConfirm = () => {
  emit("confirm");
  emit("update:isModalOpen", false);
};

const handleCancel = () => {
  emit("update:isModalOpen", false);
};
</script>
