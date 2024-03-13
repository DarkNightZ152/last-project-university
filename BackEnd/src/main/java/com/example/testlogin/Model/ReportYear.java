package com.example.testlogin.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "reportyear")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ReportYear {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @Column(name = "total_income")
  private int total_income;

  @Column(name = "year")
  private int year;
}
