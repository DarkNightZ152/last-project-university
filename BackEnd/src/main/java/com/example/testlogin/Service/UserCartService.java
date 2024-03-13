package com.example.testlogin.Service;

import com.example.testlogin.Config.JwtService;
import com.example.testlogin.DTO.Request.OrderProductRequest;
import com.example.testlogin.DTO.Request.OrderRequest;
import com.example.testlogin.Model.BillProduct;
import com.example.testlogin.Model.Bills;
import com.example.testlogin.Model.Cart;
import com.example.testlogin.Model.CartProduct;
import com.example.testlogin.Model.Products;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.BillProductRepo;
import com.example.testlogin.Repository.BillsRepo;
import com.example.testlogin.Repository.CartProductRepo;
import com.example.testlogin.Repository.CartRepo;
import com.example.testlogin.Repository.ProductsRepo;
import com.example.testlogin.Repository.StatusRepo;
import com.example.testlogin.Repository.UsersRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCartService {

  private final CartRepo cartRepo;
  private final CartProductRepo cartProductRepo;
  private final UsersRepo usersRepo;
  private final JwtService jwtService;
  private final ProductsRepo productRepo;
  private final BillsRepo billsRepo;
  private final BillProductRepo billProductRepo;
  private final StatusRepo statusRepo;

  public ResponseEntity<?> returnCustomerCart(String header) {
    Optional<Users> userOptional = getUserByUsername(header);
    if (userOptional.isPresent()) {
      Users user = userOptional.get();
      Cart cart = user.getCart();

      List<CartProduct> cartProducts = cart.getCartProducts();
      int totalCart = 0;
      for (CartProduct cp : cartProducts) {
        totalCart += cp.getTotal();
      }
      cart.setTotal(totalCart);
      cartRepo.save(cart);

      return ResponseEntity.ok(cart);
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy tài khoản!");
    }
  }

  public ResponseEntity<String> updateQuantity(
    int cartProductID,
    int newQuantity
  ) {
    Optional<CartProduct> cartProductOptional = cartProductRepo.findById(
      cartProductID
    );
    if (cartProductOptional.isPresent()) {
      CartProduct cartProduct = cartProductOptional.get();
      Products product = cartProduct.getProduct();

      // Check if there is enough stock
      if (
        // newQuantity > cartProduct.getQuantity() &&
        // product.getAmount() < newQuantity - cartProduct.getQuantity()

        product.getAmount() < newQuantity
      ) {
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Không đủ hàng trong kho");
      }

      // Update the quantity in the cart
      cartProduct.setQuantity(newQuantity);
      cartProduct.setTotal(newQuantity * product.getPrice());
      cartProductRepo.save(cartProduct);

      // Update the total price of the cart
      Cart cart = cartProduct.getCart();
      List<CartProduct> cartProducts = cart.getCartProducts();
      int totalCart = 0;
      for (CartProduct cp : cartProducts) {
        totalCart += cp.getTotal();
      }
      cart.setTotal(totalCart);
      cartRepo.save(cart);

      return ResponseEntity.ok(
        "Số lượng sản phẩm đã được cập nhật trong giỏ hàng"
      );
    } else {
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Không tìm thấy sản phẩm trong giỏ hàng");
    }
  }

  //Xóa sản phẩm khỏi giỏ hàng
  public ResponseEntity<String> removeProductFromCart(int cartProductID) {
    Optional<CartProduct> cartProductOptional = cartProductRepo.findById(
      cartProductID
    );
    if (cartProductOptional.isPresent()) {
      CartProduct cartProduct = cartProductOptional.get();

      Cart cart = cartProduct.getCart();
      cart.getCartProducts().remove(cartProduct);
      cartProductRepo.delete(cartProduct);

      List<CartProduct> cartProducts = cart.getCartProducts();
      int totalCart = 0;
      for (CartProduct cp : cartProducts) {
        totalCart += cp.getTotal();
      }
      cart.setTotal(totalCart);
      cartRepo.save(cart);

      return ResponseEntity.ok("Sản phẩm đã được xóa khỏi giỏ hàng");
    } else {
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Không tìm thấy sản phẩm trong giỏ hàng");
    }
  }

  //Chức năng đặt hàng
  public ResponseEntity<String> placeOrderFromCart(
    String header,
    OrderRequest orderRequest
  ) {
    try {
      Users user = null;
      Bills bill = new Bills();

      if (header != null) {
        Optional<Users> userOptional = getUserByUsername(header);
        if (userOptional.isPresent()) {
          user = userOptional.get();
          if (
            orderRequest.getCustomer_name() == null ||
            orderRequest.getEmail() == null ||
            orderRequest.getPhone() == null ||
            orderRequest.getAddress() == null
          ) {
            bill.setCustomer_name(user.getName());
            bill.setEmail(user.getEmail());
            bill.setPhone(user.getPhone());
            bill.setAddress(user.getAddress());
          }
        }
      }

      if (
        orderRequest.getCustomer_name() == null ||
        orderRequest.getEmail() == null ||
        orderRequest.getPhone() == null ||
        orderRequest.getAddress() == null
      ) {
        return ResponseEntity
          .badRequest()
          .body("Bạn cần nhập thông tin khách hàng!");
      }

      bill.setUser(user);
      bill.setCustomer_name(orderRequest.getCustomer_name());
      bill.setEmail(orderRequest.getEmail());
      bill.setPhone(orderRequest.getPhone());
      bill.setAddress(orderRequest.getAddress());
      bill.setNote(orderRequest.getNote());
      bill.setTotal(0);
      bill.setDiscount(0);
      bill.setTotalAfterDiscount(0);
      bill.setStatus(statusRepo.findById(1).get());
      bill = billsRepo.save(bill);

      int totalBill = 0;
      for (OrderProductRequest orderProductRequest : orderRequest.getOrderProductRequests()) {
        int productID = orderProductRequest.getProductID();
        Products product = productRepo.findById(productID).get();
        BillProduct billProduct = new BillProduct();
        billProduct.setBill(bill);
        billProduct.setProduct(product);
        billProduct.setProductname(product.getName());
        billProduct.setQuantity(orderProductRequest.getQuantity());
        billProduct.setPrice(product.getPrice());
        billProduct.setTotal(orderProductRequest.getTotal());
        totalBill += billProduct.getTotal();
        billProductRepo.save(billProduct);
      }

      bill.setTotal(totalBill);
      bill.setTotalAfterDiscount(
        totalBill - (totalBill * bill.getDiscount()) / 100
      );
      billsRepo.save(bill);

      // If the user is not null, clear the cart
      if (user != null) {
        Cart cart = user.getCart();
        if (cart != null) {
          List<CartProduct> cartProducts = cart.getCartProducts();
          for (CartProduct cartProduct : cartProducts) {
            cartProductRepo.delete(cartProduct);
          }
          cart.setCartProducts(new ArrayList<>());
          cartRepo.save(cart);
        }
      }

      return ResponseEntity.ok("Đơn hàng đã được đặt thành công!");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Đã có lỗi xảy ra khi đặt hàng!" + e.getMessage());
    }
  }

  //Chức năng In

  //Chức năng xuất file Excel

  /*Side function */

  //Lấy thông tin người dùng qua username
  private Optional<Users> getUserByUsername(String header) {
    String token = header.substring(7);
    String username = jwtService.extractUsername(token);

    return usersRepo.findByUsername(username);
  }
}
