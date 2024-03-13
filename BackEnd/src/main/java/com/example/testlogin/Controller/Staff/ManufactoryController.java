package com.example.testlogin.Controller.Staff;

import com.example.testlogin.Model.Manufactory;
import com.example.testlogin.Service.ManufactoryService;
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
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/shop")
@RequiredArgsConstructor
public class ManufactoryController {

  private final ManufactoryService manufactoryService;

  @GetMapping("/manufactory")
  public ResponseEntity<?> getAllManufactories() {
    return ResponseEntity.ok(manufactoryService.GetManufactory());
  }

  @PostMapping("/manufactory/add")
  public ResponseEntity<?> postManufactory(
    @RequestBody Manufactory manufactory
  ) {
    return manufactoryService.AddAndUpdateManufactory(manufactory);
  }

  @GetMapping("/manufactory/{id}")
  public ResponseEntity<?> getManufactory(@PathVariable("id") int ID) {
    return manufactoryService.getManufactoryByID(ID);
  }

  @PutMapping("/manufactory/update")
  public ResponseEntity<?> updateManufactory(
    @RequestBody Manufactory manufactory
  ) {
    return manufactoryService.AddAndUpdateManufactory(manufactory);
  }

  @DeleteMapping("/manufactory/{id}")
  public ResponseEntity<?> deleteManufactory(@PathVariable("id") int ID) {
    return manufactoryService.DeleteManufactory(ID);
  }
}
