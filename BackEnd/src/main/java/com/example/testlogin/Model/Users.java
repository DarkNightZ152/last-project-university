package com.example.testlogin.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Users implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int ID;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "name")
  private String name;

  @Column(name = "birth")
  private Date birth;

  @Column(name = "gender")
  private String gender;

  @Column(name = "address")
  private String address;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "avatar")
  private String avatar;

  @Enumerated(EnumType.STRING)
  private Roles role;

  @Column(name = "join_in")
  @CurrentTimestamp
  private Date joinIn;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cartid", referencedColumnName = "id")
  @JsonManagedReference
  private Cart cart;

  @OneToMany(mappedBy = "user", orphanRemoval = true)
  @JsonManagedReference
  @OrderBy("id DESC")
  private List<Bills> bills;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role.name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
