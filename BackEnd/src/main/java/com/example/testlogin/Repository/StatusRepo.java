package com.example.testlogin.Repository;

import com.example.testlogin.Model.Status;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepo extends JpaRepository<Status, Integer> {
  Optional<Status> findByStatus(String status);
}
