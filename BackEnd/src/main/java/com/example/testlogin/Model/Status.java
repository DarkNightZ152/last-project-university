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

@Entity
@Table(name = "status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Status {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @Column(name = "status")
  private String status;

  @Column(name = "description")
  private String description;

  @Column(name = "color")
  private String color;
}
