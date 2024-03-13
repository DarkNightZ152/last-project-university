package com.example.testlogin.Repository;

import com.example.testlogin.Model.ReportYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportYearRepo extends JpaRepository<ReportYear, Integer> {
  ReportYear findByYear(int year);
}
