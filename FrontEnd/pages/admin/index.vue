<template>
  <div>
    <h1 class="text-xl font-semibold">Hello from Admin site</h1>
  </div>
</template>

<script setup>
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});
</script>
