package com.example.testlogin.Service;

import com.example.testlogin.Model.Bills;
import com.example.testlogin.Model.Report;
import com.example.testlogin.Model.ReportYear;
import com.example.testlogin.Repository.BillsRepo;
import com.example.testlogin.Repository.ReportRepo;
import com.example.testlogin.Repository.ReportYearRepo;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

  private final BillsRepo billsRepo;
  private final ReportRepo reportRepo;
  private final ReportYearRepo reportYearRepo;

  @Scheduled(cron = "0 * * * * ?")
  public void generateReport() {
    List<Bills> bills = billsRepo.findAll();

    for (Bills bill : bills) {
      if (!bill.getIsCalculated()) {
        Date endOn = bill.getEnd_on();
        if (endOn != null) {
          Calendar cal = Calendar.getInstance();
          cal.setTime(endOn);
          int month = cal.get(Calendar.MONTH) + 1;
          int year = cal.get(Calendar.YEAR);

          Report report = reportRepo.findByMonthAndYear(month, year);
          if (report == null) {
            report = new Report();
            report.setMonth(month);
            report.setYear(year);
          }
          report.setTotal_income(
            report.getTotal_income() + bill.getTotalAfterDiscount()
          );
          reportRepo.save(report);

          bill.setIsCalculated(true);
          billsRepo.save(bill);
        }
      }
    }
  }

  @Scheduled(cron = "0 0 0 1 1 ?")
  public void generateYearlyReport() {
    List<Bills> bills = billsRepo.findAll();

    for (Bills bill : bills) {
      if (!bill.getIsCalculated()) {
        Date endOn = bill.getEnd_on();
        if (endOn != null) {
          Calendar cal = Calendar.getInstance();
          cal.setTime(endOn);
          int year = cal.get(Calendar.YEAR);

          ReportYear reportYear = reportYearRepo.findByYear(year);
          if (reportYear == null) {
            reportYear = new ReportYear();
            reportYear.setYear(year);
          }
          reportYear.setTotal_income(
            reportYear.getTotal_income() + bill.getTotalAfterDiscount()
          );
          reportYearRepo.save(reportYear);

          bill.setIsCalculated(true);
          billsRepo.save(bill);
        }
      }
    }
  }

  public ResponseEntity<?> returnMonthReport() {
    return ResponseEntity.ok(reportRepo.findAll());
  }

  public ResponseEntity<?> returnYearReport() {
    return ResponseEntity.ok(reportYearRepo.findAll());
  }
}
