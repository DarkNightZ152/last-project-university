<template>
  <div class="min-w-[1269px] max-w-[1269px] h-full mx-auto">
    <div class="h-screen py-8">
      <div class="container mx-auto px-4">
        <h1 class="text-2xl font-semibold mb-4">Giỏ hàng</h1>
        <div class="flex flex-col md:flex-row gap-4">
          <div class="md:w-3/4">
            <div class="bg-white rounded-lg shadow-md p-6 mb-4">
              <table class="w-full">
                <thead>
                  <tr>
                    <th class="text-left font-semibold">Sản phẩm</th>
                    <th class="text-left font-semibold">Giá tiền</th>
                    <th class="text-left font-semibold px-8">Số lượng</th>
                    <th class="text-left font-semibold">Tổng giá</th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item in cartDetails">
                    <NuxtLink :to="`/products/${item.seo}`">
                      <td class="py-4">
                        <div class="flex items-center">
                          <img
                            class="h-16 w-16 mr-4"
                            :src="item.imageUrl"
                            alt="Product image"
                          />
                          <span class="font-semibold">{{ item.name }}</span>
                        </div>
                      </td>
                    </NuxtLink>
                    <td class="py-4">{{ numbertoprice(item.price) }}</td>
                    <td class="py-4">
                      <div class="flex items-center">
                        <button
                          class="border rounded-md py-2 px-4 mr-2"
                          @click="decreaseQuantity(item)"
                        >
                          -
                        </button>
                        <span class="text-center w-8">{{ item.quantity }}</span>
                        <button
                          class="border rounded-md py-2 px-4 ml-2"
                          @click="increaseQuantity(item)"
                        >
                          +
                        </button>
                      </div>
                    </td>
                    <td class="py-4">{{ numbertoprice(item.total) }}</td>
                    <td class="py-4">
                      <button
                        @click="removeFromCart(item.id)"
                        class="border rounded-md py-2 px-4 bg-red-500 text-white font-semibold"
                      >
                        Xóa
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="md:w-1/4 space-y-3">
            <div class="bg-white rounded-lg shadow-md p-6">
              <h2 class="text-lg font-semibold mb-4">Tổng tiền</h2>
              <div class="flex justify-between mb-2">
                <span>Subtotal</span>
                <span>{{ numbertoprice(cart.total) }}</span>
              </div>
              <div class="flex justify-between mb-2">
                <span>Giảm giá</span>
                <span>0%</span>
              </div>
              <div class="flex justify-between mb-2">
                <span>Tiền ship</span>
                <span>Thỏa thuận</span>
              </div>
              <hr class="my-2" />
              <div class="flex justify-between mb-2">
                <span class="font-semibold">Tổng tất cả</span>
                <span class="font-semibold">{{
                  numbertoprice(cart.total)
                }}</span>
              </div>
              <button
                class="bg-blue-500 text-white py-2 px-4 rounded-lg mt-4 w-full"
                @click="placeOrder()"
              >
                Đặt hàng
              </button>
            </div>
            <div class="w-full max-w-xs mx-auto">
              <form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
                <div class="mb-4">
                  <label
                    class="block text-gray-700 text-sm font-bold mb-2"
                    for="customer_name"
                  >
                    Tên khách hàng
                  </label>
                  <input
                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="customer_name"
                    type="text"
                    placeholder="Tên khách hàng"
                    v-model="orderRequest.customer_name"
                  />
                </div>
                <div class="mb-4">
                  <label
                    class="block text-gray-700 text-sm font-bold mb-2"
                    for="email"
                  >
                    Email
                  </label>
                  <input
                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="email"
                    type="email"
                    placeholder="Email"
                    v-model="orderRequest.email"
                  />
                </div>
                <div class="mb-4">
                  <label
                    class="block text-gray-700 text-sm font-bold mb-2"
                    for="phone"
                  >
                    Số điện thoại
                  </label>
                  <input
                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="phone"
                    type="tel"
                    placeholder="Số điện thoại"
                    v-model="orderRequest.phone"
                  />
                </div>
                <div class="mb-6">
                  <label
                    class="block text-gray-700 text-sm font-bold mb-2"
                    for="address"
                  >
                    Địa chỉ
                  </label>
                  <input
                    class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                    id="address"
                    type="text"
                    placeholder="Địa chỉ"
                    v-model="orderRequest.address"
                  />
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts" setup>
// @ts-nocheck
definePageMeta({
  middleware: "check-login",
});
const store = useAuthStore();

const cart = ref();
const { data } = await useFetch("http://localhost:8080/api/v1/shop/cart", {
  method: "GET",
  headers: {
    Authorization: "Bearer " + store.token,
  },
});
cart.value = data.value;

const cartDetails = ref([]);

cartDetails.value = cart.value.cartProducts.map((item: any) => ({
  id: item.id,
  productID: item.product.id,
  name: item.product.name,
  imageUrl: item.product.productImages[0].imageurl,
  quantity: item.quantity,
  total: item.total,
  price: item.product.price,
  seo: item.product.seo,
}));

function numbertoprice(number: number) {
  const priceafterconvert = new Intl.NumberFormat("vi-VN", {
    style: "currency",
    currency: "VND",
  }).format(number);
  return priceafterconvert;
}

async function removeFromCart(ID: Number) {
  const { error } = await useFetch(
    "http://localhost:8080/api/v1/shop/cart/remove-product",
    {
      method: "DELETE",
      params: {
        cartProductID: ID,
      },
    }
  );
  if (error.value) {
    const errorMessage = error.value.data;
    alert(errorMessage); // Hiển thị thông báo lỗi cho người dùng
  }
  location.reload();
}

const updateQuantity = async (id: number, newQuantity: number) => {
  const { error } = await useFetch(
    "http://localhost:8080/api/v1/shop/cart/update-quantity",
    {
      method: "PUT",
      params: {
        cartProductID: id,
        newquantity: newQuantity,
      },
    }
  );
  if (error.value) {
    const errorMessage = error.value.data;
    alert(errorMessage); // Hiển thị thông báo lỗi cho người dùng
  }
};

const increaseQuantity = (item: any) => {
  updateQuantity(item.id, item.quantity + 1);
  location.reload();
};

const decreaseQuantity = (item: any) => {
  if (item.quantity > 1) {
    updateQuantity(item.id, item.quantity - 1);
    location.reload();
  }
};

const orderRequest = ref({
  customer_name: null,
  email: null,
  phone: null,
  address: null,
  note: null,
  orderProductRequests: [], // This should be filled with the products in the cart
});

const placeOrder = async () => {
  // Fill the orderProductRequests with the products in the cart
  orderRequest.value.orderProductRequests = cartDetails.value.map((item) => ({
    productID: item.productID,
    quantity: item.quantity,
    total: item.total,
  }));

  const { error } = await useFetch(
    "http://localhost:8080/api/v1/shop/cart/place-order",
    {
      method: "POST",
      headers: {
        Authorization: "Bearer " + store.token,
      },
      body: JSON.stringify(orderRequest.value),
    }
  );

  if (error.value) {
    const errorMessage = error.value.data;
    alert(errorMessage); // Hiển thị thông báo lỗi cho người dùng
  } else {
    location.reload();
  }
};
</script>
