export const useGetAccountIn4 = () => {
  console.log("Hello from composable!");
  return {
    getUserData,
    updateObject,
  };
};

async function getUserData(token: String) {
  const { data, pending, error, refresh } = await useFetch(
    `http://localhost:8080/api/v1/user/get-infor`,
    {
      headers: {
        Authorization: "Bearer " + useCookie("token"),
      },
    }
  );
  return { data, pending, error, refresh };
}

async function updateUserIn4(dataname: String, body: any) {
  await useFetch(`http://localhost:8080/api/${dataname}/add`, {
    method: "POST",
    body: { body },
    headers: {
      "Content-Type": "application/json",
    },
  });

  location.reload();
}

async function updateObject(dataname: String, body: any) {
  await useFetch(`http://localhost:8080/api/${dataname}/add`, {
    method: "POST",
    body: { body },
    headers: {
      "Content-Type": "application/json",
    },
  });

  location.reload();
}
