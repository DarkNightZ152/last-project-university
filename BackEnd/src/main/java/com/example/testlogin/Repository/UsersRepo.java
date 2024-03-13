package com.example.testlogin.Repository;

import com.example.testlogin.Model.Users;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
  Optional<Users> findByUsername(String username);
  Users findByNameAndEmailAndPhoneAndAddress(
    String name,
    String email,
    String phone,
    String address
  );
  List<Users> findByRoleOrderByIDDesc(String role);
}
