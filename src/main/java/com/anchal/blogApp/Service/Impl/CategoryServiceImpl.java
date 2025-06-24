package com.anchal.blogApp.Service.Impl;

import com.anchal.blogApp.Model.Entities.Category;
import com.anchal.blogApp.Repository.CategoryRepo;
import com.anchal.blogApp.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> listCategories() {
        return categoryRepo.findAllByPostCount();
    }
}
