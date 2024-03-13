package com.example.testlogin.Controller.Staff;

import com.example.testlogin.DTO.Request.ProductRequest;
import com.example.testlogin.Service.ImageService;
import com.example.testlogin.Service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;
  private final ImageService imageService;

  @GetMapping("/product")
  public ResponseEntity<?> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProduct());
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<?> getProductByID(@PathVariable("id") int id) {
    return productService.getProduct(id);
  }

  @PostMapping("/product/add")
  public ResponseEntity<?> addNewProduct(@RequestBody ProductRequest request) {
    return productService.addNewProduct(request);
  }

  @PostMapping("/product/{id}/image-upload")
  public ResponseEntity<?> addProductImages(
    @PathVariable("id") int id,
    @RequestParam("image") List<MultipartFile> files
  ) {
    return imageService.addProductImages(id, files);
  }

  @PutMapping("/product/{id}")
  public ResponseEntity<?> updateProduct(
    @PathVariable("id") int productID,
    @RequestBody ProductRequest request
  ) {
    return productService.updateProduct(productID, request);
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<?> deleteProduct(@PathVariable("id") int ID) {
    return productService.removeProduct(ID);
  }
}
