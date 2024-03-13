<template>
  <div class="flex h-screen whitespace-nowrap min-h-[500px]">
    <!-- Sidebar -->
    <AdminSidebar />

    <!-- Main Content -->
    <main class="flex-1 p-6 bg-gray-50 whitespace-normal">
      <!-- Top Bar -->
      <div class="flex justify-end items-center mb-6">
        <!-- User info -->
      </div>

      <!-- Right Panel - Content -->
      <div class="flex-1 overflow-y-auto overflow-x-auto">
        <div class="bg-white p-6 rounded shadow-md h-full">
          <div class="mb-4">
            <NuxtPage />
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
// @ts-nocheck
checkLogin();
</script>
