import jwtDecode from "jwt-decode";

interface MyToken {
  sub: String;
  Password: String;
  UserID: number;
  Authorities: String;
  iat: number;
  exp: number;
}

export const checkLogin = () => {
  const store = useAuthStore();

  if (store.token == null) {
    console.log("Token đang null");
    store.authState.isNotLogin = true;
    store.authState.isStaff = false;
    store.authState.isAdmin = false;
  } else {
    console.log("Đang có token là: " + store.token);
    store.authState.isNotLogin = false;
    const decoded = jwtDecode(store.token) as MyToken;
    console.log(decoded);
    if (decoded.Authorities == "STAFF") {
      store.authState.isStaff = true;
    } else if (decoded.Authorities == "ADMIN") {
      store.authState.isStaff = true;
      store.authState.isAdmin = true;
    }
    console.log(store.authState);
  }
};
