<template>
  <div class="min-w-[1269px] max-w-[1269px] mx-auto py-5">
    <div class="py-4 flex">
      <div class="px-2 ml-5 w-[550px]">
        <div class="bg-white">
          <p
            tabindex="0"
            role="heading"
            class="text-2xl font-bold leading-6 text-gray-800 mb-5"
          >
            Đăng nhập
          </p>

          <div>
            <h1 class="text-sm font-medium leading-none text-gray-800">
              Tài khoản
            </h1>
            <input
              role="input"
              type="account"
              class="bg-gray-200 border rounded focus:outline-none text-xs font-medium leading-none text-gray-800 py-3 w-full pl-3 mt-2"
              v-model="username"
            />
          </div>
          <div class="mt-6">
            <h1 class="text-sm font-medium leading-none text-gray-800">
              Mật khẩu
            </h1>
            <div class="flex items-center justify-center">
              <input
                role="input"
                type="password"
                class="bg-gray-200 border rounded focus:outline-none text-xs font-medium leading-none text-gray-800 py-3 w-full pl-3 mt-2"
                v-model="password"
              />
            </div>

            <p class="text-sm mt-4 font-medium leading-none text-gray-500">
              Quên mật khẩu?
              <nuxt-link
                to="/login"
                class="text-sm font-medium leading-none underline text-gray-800 cursor-pointer"
                >Nhấn vào đây
              </nuxt-link>
            </p>
          </div>
          <div class="mt-8">
            <button
              role="button"
              class="focus:ring-2 focus:ring-offset-2 focus:ring-indigo-700 text-sm font-semibold leading-none text-white focus:outline-none bg-indigo-700 border rounded hover:bg-indigo-600 py-4 w-full"
              @click="logIn()"
              :disabled="isLoginNull"
            >
              Đăng nhập
            </button>
          </div>
        </div>
      </div>
      <div class="w-[300px] flex items-center rotate-90">
        <hr class="w-full bg-gray-700" />
        <p class="text-base font-medium px-2.5 text-gray-700 -rotate-90">OR</p>
        <hr class="w-full bg-gray-700" />
      </div>
      <div class="px-2 mr-5 w-[550px]">
        <div class="bg-white">
          <p
            tabindex="0"
            role="heading"
            class="text-2xl font-bold leading-6 text-gray-800 mb-5"
          >
            Đăng ký
          </p>

          <div>
            <h1 class="text-sm font-medium leading-none text-gray-800">
              Tài khoản
            </h1>
            <input
              role="input"
              type="email"
              class="bg-gray-200 border rounded focus:outline-none text-xs font-medium leading-none text-gray-800 py-3 w-full pl-3 mt-2"
              v-model="registerusername"
            />
          </div>
          <h1 class="text-red-500" v-show="isNull">
            Tài khoản không được để trống
          </h1>
          <div class="mt-4 w-full">
            <h1 class="text-sm font-medium leading-none text-gray-800">
              Mật khẩu
            </h1>
            <div class="relative flex items-center justify-center mb-4">
              <input
                role="input"
                type="password"
                class="bg-gray-200 border rounded focus:outline-none text-xs font-medium leading-none text-gray-800 py-3 w-full pl-3 mt-2"
                v-model="registerpassword"
              />
              <div class="absolute right-0 mt-2 mr-3 cursor-pointer">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 16 16"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M7.99978 2C11.5944 2 14.5851 4.58667 15.2124 8C14.5858 11.4133 11.5944 14 7.99978 14C4.40511 14 1.41444 11.4133 0.787109 8C1.41378 4.58667 4.40511 2 7.99978 2ZM7.99978 12.6667C9.35942 12.6664 10.6787 12.2045 11.7417 11.3568C12.8047 10.509 13.5484 9.32552 13.8511 8C13.5473 6.67554 12.8031 5.49334 11.7402 4.64668C10.6773 3.80003 9.35864 3.33902 7.99978 3.33902C6.64091 3.33902 5.32224 3.80003 4.25936 4.64668C3.19648 5.49334 2.45229 6.67554 2.14844 8C2.45117 9.32552 3.19489 10.509 4.25787 11.3568C5.32085 12.2045 6.64013 12.6664 7.99978 12.6667ZM7.99978 11C7.20413 11 6.44106 10.6839 5.87846 10.1213C5.31585 9.55871 4.99978 8.79565 4.99978 8C4.99978 7.20435 5.31585 6.44129 5.87846 5.87868C6.44106 5.31607 7.20413 5 7.99978 5C8.79543 5 9.55849 5.31607 10.1211 5.87868C10.6837 6.44129 10.9998 7.20435 10.9998 8C10.9998 8.79565 10.6837 9.55871 10.1211 10.1213C9.55849 10.6839 8.79543 11 7.99978 11ZM7.99978 9.66667C8.4418 9.66667 8.86573 9.49107 9.17829 9.17851C9.49085 8.86595 9.66644 8.44203 9.66644 8C9.66644 7.55797 9.49085 7.13405 9.17829 6.82149C8.86573 6.50893 8.4418 6.33333 7.99978 6.33333C7.55775 6.33333 7.13383 6.50893 6.82126 6.82149C6.5087 7.13405 6.33311 7.55797 6.33311 8C6.33311 8.44203 6.5087 8.86595 6.82126 9.17851C7.13383 9.49107 7.55775 9.66667 7.99978 9.66667Z"
                    fill="#71717A"
                  />
                </svg>
              </div>
            </div>
            <h1 class="text-sm font-medium leading-none text-gray-800">
              Nhập lại mật khẩu
            </h1>
            <div class="relative flex items-center justify-center">
              <input
                role="input"
                type="password"
                class="bg-gray-200 border rounded focus:outline-none text-xs font-medium leading-none text-gray-800 py-3 w-full pl-3 mt-2"
                v-model="repassword"
              />
              <div class="absolute right-0 mt-2 mr-3 cursor-pointer">
                <svg
                  width="16"
                  height="16"
                  viewBox="0 0 16 16"
                  fill="none"
                  xmlns="http://www.w3.org/2000/svg"
                >
                  <path
                    d="M7.99978 2C11.5944 2 14.5851 4.58667 15.2124 8C14.5858 11.4133 11.5944 14 7.99978 14C4.40511 14 1.41444 11.4133 0.787109 8C1.41378 4.58667 4.40511 2 7.99978 2ZM7.99978 12.6667C9.35942 12.6664 10.6787 12.2045 11.7417 11.3568C12.8047 10.509 13.5484 9.32552 13.8511 8C13.5473 6.67554 12.8031 5.49334 11.7402 4.64668C10.6773 3.80003 9.35864 3.33902 7.99978 3.33902C6.64091 3.33902 5.32224 3.80003 4.25936 4.64668C3.19648 5.49334 2.45229 6.67554 2.14844 8C2.45117 9.32552 3.19489 10.509 4.25787 11.3568C5.32085 12.2045 6.64013 12.6664 7.99978 12.6667ZM7.99978 11C7.20413 11 6.44106 10.6839 5.87846 10.1213C5.31585 9.55871 4.99978 8.79565 4.99978 8C4.99978 7.20435 5.31585 6.44129 5.87846 5.87868C6.44106 5.31607 7.20413 5 7.99978 5C8.79543 5 9.55849 5.31607 10.1211 5.87868C10.6837 6.44129 10.9998 7.20435 10.9998 8C10.9998 8.79565 10.6837 9.55871 10.1211 10.1213C9.55849 10.6839 8.79543 11 7.99978 11ZM7.99978 9.66667C8.4418 9.66667 8.86573 9.49107 9.17829 9.17851C9.49085 8.86595 9.66644 8.44203 9.66644 8C9.66644 7.55797 9.49085 7.13405 9.17829 6.82149C8.86573 6.50893 8.4418 6.33333 7.99978 6.33333C7.55775 6.33333 7.13383 6.50893 6.82126 6.82149C6.5087 7.13405 6.33311 7.55797 6.33311 8C6.33311 8.44203 6.5087 8.86595 6.82126 9.17851C7.13383 9.49107 7.55775 9.66667 7.99978 9.66667Z"
                    fill="#71717A"
                  />
                </svg>
              </div>
            </div>
            <h1 class="text-red-500" v-show="isNoMatch">Mật khẩu chưa đúng!</h1>
          </div>
          <div class="mt-8">
            <button
              role="button"
              class="focus:ring-2 focus:ring-offset-2 focus:ring-indigo-700 text-sm font-semibold leading-none text-white focus:outline-none bg-red-700 border rounded hover:bg-red-600 py-4 w-full"
              @click="register()"
              :disabled="isNoMatch || isNull"
            >
              Đăng ký tài khoản
            </button>
          </div>
        </div>
      </div>
    </div>
    <Noftification
      :isModalOpen="isModalOpen"
      message="Đăng nhập thành công"
      @update:isModalOpen="isModalOpen = $event"
      @confirm="goToHome()"
    />
  </div>
</template>
<script lang="ts" setup>
const store = useAuthStore();
const isModalOpen = ref(false);

definePageMeta({
  middleware: [
    function (to, from) {
      const store = useAuthStore();
      if (store.authState.isNotLogin === false) {
        return navigateTo("/account");
      }
    },
  ],
});

const username = ref("");
const password = ref("");

const isLoginNull = ref(true);

watchEffect(() => {
  if (username.value && password.value) {
    isLoginNull.value = false;
  } else {
    isLoginNull.value = true;
  }
});

const registerusername = ref("");
const registerpassword = ref("");
const repassword = ref("");

const isNull = ref(false);
const isNoMatch = ref(false);

watchEffect(() => {
  isNoMatch.value = registerpassword.value !== repassword.value;
});

watchEffect(() => {
  isNull.value = registerusername.value === "";
});

async function logIn() {
  const { data, error } = await useFetch(
    "http://localhost:8080/api/v1/auth/authenticate",
    {
      method: "POST",
      body: {
        username: username.value,
        password: password.value,
      },
      headers: {
        "Content-Type": "application/json",
      },
    }
  );
  if (error.value) {
    const errorMessage = error.value.data;
    alert(errorMessage); // Hiển thị thông báo lỗi cho người dùng
  } else {
    store.token = data.value;
    navigateTo("/");
  }
}

async function register() {
  await useFetch("http://localhost:8080/api/v1/auth/register", {
    method: "POST",
    body: JSON.stringify({
      username: registerusername.value,
      password: registerpassword.value,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  });
  location.reload();
}

function goToHome() {
  location.reload();
}
</script>
