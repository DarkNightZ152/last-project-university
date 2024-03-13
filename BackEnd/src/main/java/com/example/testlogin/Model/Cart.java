package com.example.testlogin.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cart")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @Column(name = "total")
  private int total;

  @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
  @JsonManagedReference
  private List<CartProduct> cartProducts;
}
