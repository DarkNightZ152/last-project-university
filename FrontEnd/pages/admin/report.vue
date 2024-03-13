<template>
  <div>
    <div class="p-6 bg-white rounded-xl shadow-md flex items-center space-x-4">
      <canvas id="myChart"></canvas>
    </div>
  </div>
</template>
<script setup lang="ts">
// @ts-nocheck
import { Chart, registerables } from "chart.js";
definePageMeta({
  layout: "admin",
  middleware: "check-staff",
});

Chart.register(...registerables);

onMounted(async () => {
  const response = await fetch("http://localhost:8080/api/v1/report/months");
  const data = await response.json();

  // Giới hạn dữ liệu chỉ lấy 5 tháng gần nhất
  const recentData = data.slice(-5);

  const labels = recentData.map((item) => item.month + "/" + item.year);
  const values = recentData.map((item) => item.total_income);

  const ctx = document.getElementById("myChart");
  new Chart(ctx, {
    type: "line",
    data: {
      labels: labels,
      datasets: [
        {
          label: "Tổng thu nhập tháng",
          data: values,
          backgroundColor: "rgba(75, 192, 192, 0.2)",
          borderColor: "rgba(75, 192, 192, 1)",
          borderWidth: 1,
        },
      ],
    },
    options: {
      scales: {
        y: {
          beginAtZero: true,
        },
      },
    },
  });
});
</script>
