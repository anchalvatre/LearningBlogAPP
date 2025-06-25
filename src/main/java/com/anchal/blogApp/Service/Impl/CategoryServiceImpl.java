package com.anchal.blogApp.Service.Impl;

import com.anchal.blogApp.Model.Entities.Category;
import com.anchal.blogApp.Repository.CategoryRepo;
import com.anchal.blogApp.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> listCategories() {
        return categoryRepo.findAllByPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepo.existsByNameIgnoreCase(category.getName()))
            throw new IllegalArgumentException("Category already exist with the name : " + category.getName());

        return categoryRepo.save(category);
    }

    @Override
    @Transactional
    public void deleteCategory(UUID id) {
        if (categoryRepo.findById(id).isEmpty())
            throw new IllegalStateException("Category not present " );

        Category category = categoryRepo.findById(id).get();

        if (!category.getPosts().isEmpty())
            throw new IllegalStateException("Cannot delete post " + category.getName() + " as it still contains post");

        categoryRepo.delete(category);
    }
}
