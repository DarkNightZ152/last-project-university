import { defineStore } from "pinia";

export const useAuthStore = defineStore(
  "authstore",
  () => {
    const authState = ref({
      isNotLogin: true,
      isStaff: false,
      isAdmin: false,
      loading: false,
    });

    const token = ref();
    console.log("Hello from useAuthStore!");
    console.log(authState.value);

    function logOut() {
      token.value = null;
    }
    return { authState, token, logOut };
  },
  { persist: true }
);
