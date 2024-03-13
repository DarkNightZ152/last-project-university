export const useTest = () => {
  return {
    addToCart,
  };
};

async function addToCart(token: String, seo: String, quantity: Number) {
  const {error} = await useFetch(`http://localhost:8080/api/v1/shop/${seo}/addtocart`, {
    method: "POST",
    headers: {
      Authorization: `Bearer ${token}`,
    },
    body: quantity,
  });
  if (error.value) {
    const errorMessage = error.value.data
    alert(errorMessage) // Hiển thị thông báo lỗi cho người dùng
  }
  location.reload();
}
