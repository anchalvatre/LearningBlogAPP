package com.anchal.blogApp.Service;

import com.anchal.blogApp.Model.Entities.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> listCategories();

    Category createCategory(Category category);

    void deleteCategory(UUID id);
}
