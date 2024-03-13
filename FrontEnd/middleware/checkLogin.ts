export default defineNuxtRouteMiddleware((to, from) => {
  const store = useAuthStore();

  if (store.authState.isNotLogin === true) {
    return navigateTo("/login");
  }
});
