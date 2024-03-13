package com.example.testlogin.Controller.Admin;

import com.example.testlogin.Service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/report")
public class ReportController {

  public final ReportService reportService;

  @GetMapping("/months")
  public ResponseEntity<?> getMonthReport() {
    return reportService.returnMonthReport();
  }

  @GetMapping("/years")
  public ResponseEntity<?> getYearReport() {
    return reportService.returnYearReport();
  }
}
