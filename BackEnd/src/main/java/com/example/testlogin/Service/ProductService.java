package com.example.testlogin.Service;

import com.example.testlogin.Config.JwtService;
import com.example.testlogin.DTO.Request.ProductRequest;
import com.example.testlogin.DTO.Response.ProductHomeResponse;
import com.example.testlogin.DTO.Response.ProductResponse;
import com.example.testlogin.Model.Cart;
import com.example.testlogin.Model.CartProduct;
import com.example.testlogin.Model.Comments;
import com.example.testlogin.Model.Products;
import com.example.testlogin.Model.Users;
import com.example.testlogin.Repository.CartProductRepo;
import com.example.testlogin.Repository.CartRepo;
import com.example.testlogin.Repository.CategoryRepo;
import com.example.testlogin.Repository.CommentsRepo;
import com.example.testlogin.Repository.ManufactoryRepo;
import com.example.testlogin.Repository.ProductsRepo;
import com.example.testlogin.Repository.UsersRepo;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductsRepo productRepo;
  private final CategoryRepo categoryRepo;
  private final ManufactoryRepo manufactoryRepo;
  private final UsersRepo usersRepo;
  private final JwtService jwtService;
  private final CartRepo cartRepo;
  private final CartProductRepo cartProductRepo;
  private final CommentsRepo commentsRepo;

  //Trả về danh sách sản phẩm
  public List<Products> getAllProduct() {
    return productRepo.findAll(Sort.by("ID").ascending());
  }

  //Trả về sản phẩm theo ID
  public ResponseEntity<?> getProduct(int productID) {
    Optional<Products> productOptional = productRepo.findById(productID);
    if (productOptional.isPresent()) {
      Products product = productOptional.get();
      var productResponse = ProductResponse
        .builder()
        .name(product.getName())
        .category(product.getCategory().getName())
        .manufactory(product.getManufactory().getName())
        .descriptrion(product.getDescription())
        .detail_des(product.getDetail_des())
        .amount(product.getAmount())
        .quality(product.getQuality())
        .price(product.getPrice())
        .discount(product.getDiscount())
        .guarantee(product.getGuarantee())
        .buyed(product.getBuyed())
        .viewed(product.getViewed())
        .rated_total(product.getRated_total())
        .rated_count(product.getRated_count())
        .images(product.getProductImages())
        .build();
      return ResponseEntity.ok(productResponse);
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm");
    }
  }

  //Trả về sản phẩm theo seo
  public ResponseEntity<?> getProductBySeo(String seo) {
    Optional<Products> productOptional = productRepo.findBySeo(seo);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok(productOptional.get());
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm");
    }
  }

  /*Admin Side */

  //Thêm mới sản phẩm
  public ResponseEntity<String> addNewProduct(ProductRequest productRequest) {
    try {
      var product = Products
        .builder()
        .name(productRequest.getName())
        .category(categoryRepo.findByName(productRequest.getCategory()).get())
        .manufactory(
          manufactoryRepo.findByName(productRequest.getManufactory()).get()
        )
        .description(null)
        .detail_des(null)
        .amount(productRequest.getAmount())
        .price(productRequest.getPrice())
        .productImages(null)
        .quality("Còn mới")
        .guarantee(productRequest.getGuarantee())
        .comments(null)
        .seo(generateSEOUrl(productRequest.getName()))
        .build();
      productRepo.save(product);
      return ResponseEntity.ok("Thêm mới sản phẩm thành công");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
          "Thêm thất bại ☹ Có thể do sản phẩm đã tồn tại, hoặc định dạng thông tin không phù hợp"
        );
    }
  }

  //Xóa sản phẩm
  public ResponseEntity<String> removeProduct(int productID) {
    Optional<Products> product = productRepo.findById(productID);
    if (product.isPresent()) {
      Products removeProduct = product.get();
      productRepo.delete(removeProduct);

      return ResponseEntity.ok("Xóa sản phẩm thành công");
    } else {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Không tìm thấy sản phẩm");
    }
  }

  //Cập nhật sản phẩm
  public ResponseEntity<String> updateProduct(
    int productID,
    ProductRequest productRequest
  ) {
    try {
      Optional<Products> product = productRepo.findById(productID);
      if (product.isPresent()) {
        Products updateProduct = product.get();

        updateProduct.setName(productRequest.getName());
        updateProduct.setCategory(
          categoryRepo.findByName(productRequest.getCategory()).get()
        );
        updateProduct.setManufactory(
          manufactoryRepo.findByName(productRequest.getManufactory()).get()
        );
        updateProduct.setAmount(productRequest.getAmount());
        updateProduct.setPrice(productRequest.getPrice());
        updateProduct.setQuality(productRequest.getQuality());
        updateProduct.setDiscount(productRequest.getDiscount());
        updateProduct.setGuarantee(productRequest.getGuarantee());
        updateProduct.setSeo(generateSEOUrl(productRequest.getName()));

        productRepo.save(updateProduct);

        return ResponseEntity.ok("Cập nhật sản phẩm thành công");
      } else {
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Không tìm thấy sản phẩm");
      }
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(
          "Cập nhật thất bại ☹ Có thể định dạng thông tin không phù hợp: " +
          e.getMessage()
        );
    }
  }

  /*User Side */

  //Product ở trang chủ
  public ResponseEntity<?> getHomeProduct() {
    List<Products> products = productRepo.findAll();
    // int end = Math.min(products.size(), 5);
    // products = products.subList(0, end); // Gán kết quả của subList vào products
    List<ProductHomeResponse> productHomeResponse = new ArrayList<
      ProductHomeResponse
    >();

    for (Products product : products) {
      ProductHomeResponse response = new ProductHomeResponse(); // Tạo một đối tượng ProductHomeResponse mới

      response.setName(product.getName());
      response.setCategory(product.getCategory().getName());
      response.setManufactory(product.getManufactory().getName());
      response.setDescriptrion(product.getDescription());
      response.setDetail_des(product.getDetail_des());
      response.setAmount(product.getAmount());
      response.setDiscount(product.getDiscount());
      response.setGuarantee(product.getGuarantee());
      response.setPrice(product.getPrice());
      response.setViewed(product.getViewed());
      response.setRated_count(product.getRated_count());
      response.setRated_total(product.getRated_total());
      response.setQuality(product.getQuality());
      response.setSeo(product.getSeo());
      if (!product.getProductImages().isEmpty()) {
        response.setImagesurl(product.getProductImages().get(0).getImageurl());
      } else {
        response.setImagesurl("/default-product-image.png"); // URL của hình ảnh mặc định
      }

      productHomeResponse.add(response); // Thêm đối tượng ProductHomeResponse vào danh sách
    }

    return ResponseEntity.ok(productHomeResponse);
  }

  //Product ở Chi tiết sản phẩm
  public ResponseEntity<?> getInforProduct(String seo) {
    Optional<Products> productOptional = productRepo.findBySeo(seo);
    if (productOptional.isPresent()) {
      Products product = productOptional.get();
      ProductHomeResponse response = new ProductHomeResponse(); // Tạo một đối tượng ProductHomeResponse mới

      response.setName(product.getName());
      response.setCategory(product.getCategory().getName());
      response.setManufactory(product.getManufactory().getName());
      response.setDescriptrion(product.getDescription());
      response.setDetail_des(product.getDetail_des());
      response.setAmount(product.getAmount());
      response.setDiscount(product.getDiscount());
      response.setGuarantee(product.getGuarantee());
      response.setPrice(product.getPrice());
      response.setViewed(product.getViewed());
      response.setRated_count(product.getRated_count());
      response.setRated_total(product.getRated_total());
      response.setQuality(product.getQuality());
      response.setSeo(product.getSeo());
      if (!product.getProductImages().isEmpty()) {
        response.setImagesurl(product.getProductImages().get(0).getImageurl());
      } else {
        response.setImagesurl("/default-product-image.png"); // URL của hình ảnh mặc định
      }
      response.setComments(product.getComments());

      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.badRequest().body("Không tìm thấy sản phẩm!");
    }
  }

  public ResponseEntity<?> findProductByNameContain(String namecontain) {
    return ResponseEntity.ok(productRepo.findByNameContaining(namecontain));
  }

  //Thêm sản phẩm vào giỏ
  public ResponseEntity<?> addProductToCart(
    String header,
    String seo,
    int quantity
  ) {
    try {
      Optional<Users> userOptional = getUserByUsername(header);
      Products product = productRepo.findBySeo(seo).get();
      int amountinstock = product.getAmount();
      int productID = product.getID();
      if (userOptional.isPresent()) {
        Users user = userOptional.get();
        Cart cart = user.getCart();
        if (cart != null) {
          List<CartProduct> cartProducts = cart.getCartProducts();
          Optional<CartProduct> existingCartItem = cartProducts
            .stream()
            .filter(item -> item.getProduct().getID() == productID)
            .findFirst();
          if (existingCartItem.isPresent()) {
            CartProduct cartItem = existingCartItem.get();
            int currentQuantityExistingCartItem = cartItem.getQuantity();
            if (amountinstock >= currentQuantityExistingCartItem + quantity) {
              cartItem.setQuantity(currentQuantityExistingCartItem + quantity);
              cartItem.setTotal(
                (currentQuantityExistingCartItem + quantity) *
                product.getPrice()
              );
              cartProductRepo.save(cartItem);

              // Cập nhật total cho CartDetails
              List<CartProduct> newcartProducts = cart.getCartProducts();
              int totalCart = 0;
              for (CartProduct cp : newcartProducts) {
                totalCart += cp.getTotal();
              }
              cart.setTotal(totalCart);
              cartRepo.save(cart);
              return ResponseEntity.ok(
                "Số lượng sản phẩm đã được cập nhật trong giỏ hàng"
              );
            } else {
              return ResponseEntity
                .badRequest()
                .body("Không đủ số lượng trong kho!");
            }
          } else {
            if (amountinstock >= quantity) {
              CartProduct cartProduct = new CartProduct();
              cartProduct.setProduct(product);
              cartProduct.setQuantity(quantity);
              cartProduct.setTotal(product.getPrice() * quantity);
              cartProduct.setCart(cart);
              cartProductRepo.save(cartProduct);

              // Cập nhật total cho CartDetails
              List<CartProduct> newcartProducts = cart.getCartProducts();
              int totalCart = 0;
              for (CartProduct cp : newcartProducts) {
                totalCart += cp.getTotal();
              }
              cart.setTotal(totalCart);
              cartRepo.save(cart);
              return ResponseEntity.ok("Thêm sản phẩm vào giỏ hàng thành công");
            } else {
              return ResponseEntity
                .badRequest()
                .body("Không đủ số lượng trong kho!");
            }
          }
        }
      }
      // Không tìm thấy tài khoản, trả về thông báo lỗi hoặc ResponseEntity phù hợp
      return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body("Không tìm thấy thông tin người dùng");
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Lỗi: " + e.getMessage());
    }
  }

  //Bình luận
  public ResponseEntity<String> comment(
    String header,
    String productSeo,
    String comment
  ) {
    try {
      Optional<Users> userOptional = getUserByUsername(header);

      if (userOptional.isPresent()) {
        Products product = productRepo.findBySeo(productSeo).get();

        var usercomment = Comments
          .builder()
          .users(userOptional.get())
          .product(product)
          .comment(comment)
          .build();

        commentsRepo.save(usercomment);

        return ResponseEntity.ok("Bạn đã bình luận thành công!");
      } else {
        return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("Không tìm thấy người dùng!");
      }
    } catch (Exception e) {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Bạn phải đăng nhập mới được bình luận!");
    }
  }

  //Đánh giá sao
  public ResponseEntity<String> rateStar(String productSeo, double point) {
    Optional<Products> productOptional = productRepo.findBySeo(productSeo);
    if (productOptional.isPresent()) {
      Products product = productOptional.get();

      int ratedTotal = product.getRated_total();
      double ratedCount = product.getRated_count();

      // Kiểm tra xem đã có đánh giá nào cho sản phẩm này chưa
      if (ratedTotal == 0) {
        // Nếu chưa có đánh giá nào, chỉ cần cập nhật giá trị đánh giá
        product.setRated_count(point);
        product.setRated_total(1);
      } else {
        // Tính toán lại giá trị đánh giá trung bình
        // Tính toán lại giá trị đánh giá trung bình với điểm làm tròn
        double newAverage = (double) (ratedCount + point) / 2;
        double roundedAverage = Math.round(newAverage * 2) / 2.0; // làm tròn đến 0.5 gần nhất

        product.setRated_total(ratedTotal + 1);
        product.setRated_count(roundedAverage);
      }

      productRepo.save(product);
      return ResponseEntity.ok("Bạn đã đánh giá sao thành công!");
    } else {
      return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body("Không tìm thấy sản phẩm!");
    }
  }

  //Cập nhật lượt view
  public void updateViewed(String productSeo) {
    Products product = productRepo.findBySeo(productSeo).get();

    product.setViewed(product.getViewed() + 1);

    productRepo.save(product);
  }

  /*Side function*/
  private Optional<Users> getUserByUsername(String header) {
    String token = header.substring(7);
    String username = jwtService.extractUsername(token);

    return usersRepo.findByUsername(username);
  }

  //Tạo SEO
  private String generateSEOUrl(String key) {
    String temp = Normalizer.normalize(key, Normalizer.Form.NFD);
    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    temp = pattern.matcher(temp).replaceAll("");
    temp = temp.toLowerCase();
    temp = temp.replaceAll("[^a-z0-9 ]", "");
    temp = temp.replaceAll(" ", "-");
    return temp;
  }
}
