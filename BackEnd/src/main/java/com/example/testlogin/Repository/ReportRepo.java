package com.example.testlogin.Repository;

import com.example.testlogin.Model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepo extends JpaRepository<Report, Integer> {
  Report findByMonthAndYear(int month, int year);
}
