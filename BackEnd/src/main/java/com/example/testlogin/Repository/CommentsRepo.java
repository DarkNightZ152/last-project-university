package com.example.testlogin.Repository;

import com.example.testlogin.Model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepo extends JpaRepository<Comments, Integer> {}
