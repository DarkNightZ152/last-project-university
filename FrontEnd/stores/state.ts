export const useStateStore = defineStore("state", () => {
  const categoryState = ref(false);

  return {
    categoryState,
  };
});
