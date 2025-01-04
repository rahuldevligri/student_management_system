package com.StudentManagementSystem.com.repository;

import com.StudentManagementSystem.com.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
