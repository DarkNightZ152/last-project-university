<template>
  <div class="flex justify-between items-center p-4 border-b">
    <div class="flex-1">
      <h2 class="font-bold text-lg">{{ name }}</h2>
      <p class="text-red-500">{{ price }}</p>
    </div>
    <button class="bg-red-500 text-white px-4 py-2 rounded">Gỡ bỏ</button>
  </div>
</template>
<script lang="ts" setup>
defineProps({
  name: {
    type: String,
    required: true,
  },
  price: {
    type: Number,
    required: true,
  },
});
</script>
