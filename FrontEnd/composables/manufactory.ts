export const useManufactory = () => {
  return {
    getManufactory,
    getManufactoryByID,
    postNewManufactory,
    updateManufactory,
    deleteManufactoryById,
    deleteAllManufactoryById,
  };
};

async function getManufactory() {
  const { data, pending, error, refresh } = await useAsyncData("getdata", () =>
    $fetch("http://localhost:8080/api/v1/shop/manufactory")
  );
  return { data, pending, error, refresh };
}

async function getManufactoryByID(id: number) {
  const { data, pending, error, refresh } = await useAsyncData("getdata", () =>
    $fetch(`http://localhost:8080/api/v1/manufactory/${id}`)
  );
  return { data, pending, error, refresh };
}

async function postNewManufactory(body: any) {
  await useFetch(`http://localhost:8080/api/manufactory/add`, {
    method: "POST",
    body: { body },
    headers: {
      "Content-Type": "application/json",
    },
  });

  location.reload();
}

async function updateManufactory(body: any) {
  await useFetch(`http://localhost:8080/api/manufactory/add`, {
    method: "POST",
    body: { body },
    headers: {
      "Content-Type": "application/json",
    },
  });

  location.reload();
}

async function deleteManufactoryById(id: number) {
  await useFetch(`http://localhost:8080/api/manufactory/delete/${id}`, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
  });

  location.reload();
}

async function deleteAllManufactoryById(id: number[]) {
  id.forEach(async (id) => {
    const response = await fetch(
      `http://localhost:8080/api/manufactory/delete/${id}`,
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
      }
    );

    if (!response.ok) {
      throw new Error(`Failed to delete manufactory with ID ${id}`);
    }
  });

  location.reload();
}
