<template>
  <div v-if="isOpen" class="modal">
    <!-- Hiển thị thông tin tại đây -->
    <div v-for="(value, key) in info" :key="key">
      <strong>{{ key }}:</strong>
      <div v-if="Array.isArray(value)">
        <div v-for="(item, index) in value" :key="index">
          <img :src="item" alt="" />
        </div>
      </div>
      <div v-else>
        {{ value }}
      </div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  isOpen: Boolean,
  info: Object,
});
</script>
