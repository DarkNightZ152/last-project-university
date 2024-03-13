export default defineNuxtRouteMiddleware((to, from) => {
  const store = useAuthStore();
  if (store.authState.isStaff === false) {
    return navigateTo("/");
  }
});
