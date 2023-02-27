package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findCategoryById(long id);

}
