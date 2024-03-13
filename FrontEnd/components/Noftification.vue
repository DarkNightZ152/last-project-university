<template>
  <div
    v-if="isModalOpen"
    class="fixed top-0 left-0 w-full h-full flex items-center justify-center bg-black bg-opacity-50 z-50"
  >
    <div class="bg-white p-6 rounded shadow-md w-80">
      <p class="text-lg mb-4">{{ message }}</p>
      <div class="flex justify-end">
        <button
          @click="handleConfirm"
          class="px-4 py-2 bg-green-500 text-white rounded"
        >
          OK!
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
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
</script>
