package com.example.testlogin.Controller;

import com.example.testlogin.Service.CategoryService;
import com.example.testlogin.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController {

  private final ProductService productService;
  private final CategoryService categoryService;

  @GetMapping("/product/{seo}")
  public ResponseEntity<?> getProductBySeo(@PathVariable("seo") String seo) {
    return productService.getInforProduct(seo);
  }

  @PostMapping("/product/{seo}")
  public ResponseEntity<?> comment(
    @RequestHeader("Authorization") String header,
    @PathVariable("seo") String seo,
    @RequestBody String comment
  ) {
    return productService.comment(header, seo, comment);
  }

  @PostMapping("/product/{seo}/rate")
  public ResponseEntity<?> rateStar(
    @PathVariable("seo") String seo,
    @RequestBody double point
  ) {
    return productService.rateStar(seo, point);
  }

  @PostMapping("/product/{seo}/update-view")
  public void rateStar(@PathVariable("seo") String seo) {
    productService.updateViewed(seo);
  }

  @GetMapping("/shop/home-product")
  public ResponseEntity<?> getHomeProduct() {
    return productService.getHomeProduct();
  }

  @GetMapping("/shop/product/category/{seo}")
  public ResponseEntity<?> getProductsByCategory(
    @PathVariable("seo") String seo
  ) {
    return categoryService.returnProductsByCategory(seo);
  }

  @GetMapping("/shop/products/contain")
  public ResponseEntity<?> getProductByNameContain(
    @RequestParam String namecontain
  ) {
    return productService.findProductByNameContain(namecontain);
  }
}
