<template>
  <div>
    <div class="p-6">
      <h2 class="text-2xl font-bold text-gray-800 mb-4">
        Welcome to the Dashboard!
      </h2>
      <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        <!-- Card 1 -->
        <div class="bg-white shadow-lg rounded-lg p-5">
          <h3 class="text-gray-700 uppercase">Card 1</h3>
          <hr class="mt-2 mb-2" />
          <p class="text-gray-600">This is a simple card.</p>
        </div>
        <!-- Card 2 -->
        <div class="bg-white shadow-lg rounded-lg p-5">
          <h3 class="text-gray-700 uppercase">Card 2</h3>
          <hr class="mt-2 mb-2" />
          <p class="text-gray-600">This is another simple card.</p>
        </div>
        <!-- Card 3 -->
        <div class="bg-white shadow-lg rounded-lg p-5">
          <h3 class="text-gray-700 uppercase">Card 3</h3>
          <hr class="mt-2 mb-2" />
          <p class="text-gray-600">This is yet another simple card.</p>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});
</script>
