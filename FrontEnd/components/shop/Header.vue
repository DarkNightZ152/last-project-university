<template>
  <div>
    <header class="mt-[15px] bg-white text-black pt-4 border-b">
      <div class="container mx-auto flex justify-between items-center">
        <!-- Left section with shop name and logo -->
        <NuxtLink to="/">
          <div class="flex items-center space-x-4 ml-[150px]">
            <img src="/favicon.ico" alt="Shop Logo" class="h-8 fill-red-500" />
            <h1 class="text-2xl font-bold">NONPStore</h1>
          </div>
        </NuxtLink>

        <!-- Middle section with search bar -->
        <div class="flex w-[550px] mx-4">
          <input
            type="text"
            placeholder="Tìm kiếm sản phẩm..."
            class="w-full px-4 py-2 rounded-l border-t border-l border-b border-gray-150 focus:outline-none focus:border-red-500"
            v-model="namecontain"
          />
          <NuxtLink
            :to="`/find/${namecontain}`"
            class="w-[50px] px-2 py-2 rounded-r border border-red-500 focus:outline-none bg-red-500"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="32"
              height="32"
              color="white"
              viewBox="0 0 24 24"
            >
              <path
                fill="currentColor"
                d="m19.485 20.154l-6.262-6.262q-.75.639-1.725.989q-.975.35-1.96.35q-2.402 0-4.066-1.663q-1.664-1.664-1.664-4.065T5.47 5.436q1.663-1.667 4.064-1.667q2.402 0 4.068 1.664q1.666 1.664 1.666 4.067q0 1.042-.369 2.017q-.37.975-.97 1.668l6.262 6.261l-.707.708ZM9.538 14.23q1.99 0 3.361-1.37q1.37-1.37 1.37-3.361q0-1.99-1.37-3.36q-1.37-1.37-3.36-1.37q-1.99 0-3.361 1.37q-1.37 1.37-1.37 3.36q0 1.99 1.37 3.36q1.37 1.37 3.36 1.37Z"
              />
            </svg>
          </NuxtLink>
        </div>

        <!-- Right section with buttons -->
        <div class="flex items-center space-x-4 mr-[150px]">
          <NuxtLink to="/cart">
            <button
              class="px-4 py-2 rounded border border-black hover:bg-gray-50"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="32"
                height="32"
                viewBox="0 0 24 24"
                class="hover:fill-red-500"
              >
                <path
                  fill="#ef4444
"
                  d="M7.308 21.115q-.633 0-1.067-.433q-.433-.434-.433-1.067q0-.632.433-1.066q.434-.434 1.067-.434q.632 0 1.066.434q.434.434.434 1.066q0 .633-.434 1.067q-.434.433-1.066.433Zm9.384 0q-.632 0-1.066-.433q-.434-.434-.434-1.067q0-.632.434-1.066q.434-.434 1.066-.434q.633 0 1.067.434q.433.434.433 1.066q0 .633-.433 1.067q-.434.433-1.067.433ZM5.392 4.5h13.02q.651 0 .98.532q.33.531.035 1.095l-2.858 5.208q-.217.365-.564.573q-.347.207-.763.207H8.1l-1.215 2.231q-.154.23-.01.5t.433.27h10.884v1H7.308q-.875 0-1.306-.738q-.43-.738-.021-1.482l1.504-2.68L3.808 3.5H2v-1h2.442l.95 2Z"
                />
              </svg>
            </button>
          </NuxtLink>
          <NuxtLink to="/login">
            <button
              class="px-4 py-2 rounded border border-black hover:bg-gray-50"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="32"
                height="32"
                viewBox="0 0 24 24"
              >
                <path
                  fill="#ef4444
"
                  d="M12 11.385q-1.238 0-2.119-.882T9 8.385q0-1.238.881-2.12q.881-.88 2.119-.88t2.119.88q.881.882.881 2.12q0 1.237-.881 2.118T12 11.385Zm-7 7.23V16.97q0-.619.36-1.158q.361-.54.97-.838q1.416-.679 2.833-1.018q1.418-.34 2.837-.34q1.42 0 2.837.34q1.417.34 2.832 1.018q.61.298.97.838q.361.539.361 1.158v1.646H5Z"
                />
              </svg>
            </button>
          </NuxtLink>
        </div>
      </div>
      <div>
        <div
          class="flex container justify-between mx-auto max-w-[1269px] mt-[20px] text-[22px] font-normal"
        >
          <div
            class="px-4 py-2 flex items-center space-x-1 border hover:text-red-700 font-medium cursor-pointer"
            @mouseenter="CategoryStore.categoryState = true"
            @mouseleave="CategoryStore.categoryState = false"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="32"
              height="32"
              viewBox="0 0 24 24"
            >
              <path
                fill="#ef4444"
                d="M9.615 18.5v-1H20v1H9.615Zm0-6v-1H20v1H9.615Zm0-6v-1H20v1H9.615ZM5.327 19.327q-.547 0-.937-.39T4 18t.39-.937q.39-.39.937-.39t.937.39q.39.39.39.937t-.39.937q-.39.39-.937.39Zm0-6q-.547 0-.937-.39T4 12t.39-.937q.39-.39.937-.39t.937.39q.39.39.39.937t-.39.937q-.39.39-.937.39Zm0-6q-.547 0-.937-.39T4 6t.39-.937q.39-.39.937-.39t.937.39q.39.39.39.937t-.39.937q-.39.39-.937.39Z"
              />
            </svg>
            <h1 class="">Danh mục sản phẩm</h1>
          </div>
          <NuxtLink to="/">
            <button
              class="px-4 py-2 items-center flex rounded-md hover:text-red-700"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="32"
                height="32"
                viewBox="0 0 24 24"
              >
                <path
                  fill="#ef4444"
                  d="M6 19v-8H4.615L12 4.346l3.846 3.485V5.538h1.616v3.724L19.385 11H18v8h-4.77v-5.23h-2.46V19H6Zm1-1h2.77v-5.23h4.46V18H17v-7.8l-5-4.5l-5 4.5V18Zm2.77-5.23h4.46h-4.46Zm.422-2.764h3.616q0-.704-.543-1.159q-.542-.455-1.265-.455q-.723 0-1.265.453q-.543.453-.543 1.16Z"
                />
              </svg>
              <h1>Trang chủ</h1>
            </button>
          </NuxtLink>
          <NuxtLink to="/products">
            <button
              class="px-4 py-2 rounded-md items-center flex hover:text-red-700"
            >
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="32"
                height="32"
                viewBox="0 0 24 24"
              >
                <path
                  fill="#ef4444"
                  d="M6.669 20.788q-1.157 0-1.97-.816q-.814-.817-.814-1.972v-7.637q-.58-.553-1.29-1.197q-.71-.643-.71-1.516q0-.483.333-.816q.334-.334.817-.334q.414 0 .764.252t.586.623q.236-.371.586-.623t.764-.252q.482 0 .816.334q.334.333.334.816q0 .873-.71 1.516t-1.29 1.197V18q0 .745.52 1.267q.52.521 1.265.521q.743 0 1.268-.521q.524-.522.524-1.267V7.5q0-1.836 1.293-3.13q1.294-1.293 3.13-1.293t3.129 1.294q1.294 1.293 1.294 3.129v.542q1.494.177 2.497 1.3q1.003 1.121 1.003 2.658v5q0 1.671-1.165 2.836T16.808 21q-1.671 0-2.836-1.164Q12.808 18.67 12.808 17v-5q0-1.537 1.003-2.659q1.002-1.122 2.497-1.299V7.5q0-1.426-.998-2.425q-.998-.998-2.423-.998t-2.425.998q-1 .999-1 2.425V18q0 1.155-.818 1.972q-.818.816-1.975.816ZM16.808 20q1.25 0 2.125-.875T19.808 17v-5q0-1.25-.875-2.125T16.808 9q-1.25 0-2.125.875T13.808 12v5q0 1.25.875 2.125t2.125.875Zm0-4.5q-.633 0-1.067.434q-.433.433-.433 1.066t.433 1.066q.434.434 1.067.434q.632 0 1.066-.434q.434-.433.434-1.066t-.434-1.066q-.434-.434-1.066-.434Zm0 1.5Z"
                />
              </svg>
              <h1>Sản phẩm</h1>
            </button>
          </NuxtLink>
          <button
            class="px-4 py-2 items-center rounded-md flex hover:text-red-700"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="32"
              height="32"
              viewBox="0 0 24 24"
            >
              <path
                fill="#ef4444"
                d="M12 16.5q.213 0 .357-.144q.143-.143.143-.356v-4.5q0-.213-.144-.356Q12.212 11 12 11t-.356.144q-.143.144-.143.356V16q0 .213.144.356q.144.144.357.144Zm0-6.923q.262 0 .438-.177q.177-.177.177-.438q0-.262-.177-.439q-.176-.177-.438-.177t-.438.177q-.177.177-.177.439q0 .261.177.438q.176.177.438.177ZM12.003 21q-1.866 0-3.51-.708q-1.643-.709-2.859-1.924q-1.216-1.214-1.925-2.856Q3 13.87 3 12.003q0-1.866.708-3.51q.709-1.643 1.924-2.859q1.214-1.216 2.856-1.925Q10.13 3 11.997 3q1.866 0 3.51.708q1.643.709 2.859 1.924q1.216 1.214 1.925 2.856Q21 10.13 21 11.997q0 1.866-.708 3.51q-.709 1.643-1.924 2.859q-1.214 1.216-2.856 1.925Q13.87 21 12.003 21ZM12 20q3.35 0 5.675-2.325T20 12q0-3.35-2.325-5.675T12 4Q8.65 4 6.325 6.325T4 12q0 3.35 2.325 5.675T12 20Zm0-8Z"
              />
            </svg>
            <h1>Giới thiệu</h1>
          </button>
          <button
            class="px-4 py-2 rounded-md items-center flex hover:text-red-700"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="32"
              height="32"
              viewBox="0 0 24 24"
            >
              <path
                fill="#ef4444"
                d="M5.07 20q-.459 0-.764-.3Q4 19.4 4 18.95v-2.435q0-.402.254-.712q.254-.31.661-.41l2.104-.431q.37-.05.674.033q.305.084.55.347l2.138 2.177q1.2-.665 2.204-1.414q1.003-.75 1.875-1.62q.844-.858 1.589-1.817q.745-.958 1.36-2.076l-2.194-2.046q-.238-.2-.332-.513q-.095-.314-.025-.733l.496-2.385q.108-.402.411-.658Q16.07 4 16.477 4h2.473q.45 0 .75.306q.3.305.3.763q0 2.53-1.266 5.175q-1.267 2.646-3.546 4.935q-2.278 2.288-4.934 3.555Q7.598 20 5.069 20ZM17.872 9.654q.502-1.129.794-2.244q.293-1.116.321-2.14q0-.116-.076-.193T18.719 5h-2.046q-.154 0-.25.077t-.135.23l-.45 2.12q-.038.115-.01.25q.03.135.126.211l1.92 1.766Zm-8.45 8.323l-1.88-1.92q-.097-.095-.183-.124q-.087-.03-.202.01l-1.85.384q-.154.038-.231.135q-.077.096-.077.25v2.007q0 .116.077.193q.077.076.192.076q.852-.017 1.996-.263q1.145-.246 2.158-.748Zm8.45-8.323Zm-8.45 8.323Z"
              />
            </svg>
            <h1>Liên hệ</h1>
          </button>
        </div>
        <div class="container justify-between mx-auto max-w-[1269px]">
          <ShopProductCategory
            v-show="CategoryStore.categoryState"
            class="absolute z-50"
          />
        </div>
      </div>
    </header>
  </div>
</template>
<script lang="ts" setup>
import { useStateStore } from "~/stores/state";

const CategoryStore = useStateStore();

const namecontain = ref<String>();
</script>
