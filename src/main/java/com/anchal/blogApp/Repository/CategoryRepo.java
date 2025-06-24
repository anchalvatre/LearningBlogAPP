package com.anchal.blogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anchal.blogApp.Model.Entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID> {

    @Query("Select c FROM Category c LEFT JOIN FETCH c.posts")
    List<Category> findAllByPostCount();
}
