package com.anchal.blogApp.Controller;

import com.anchal.blogApp.Mapper.CategoryMapper;
import com.anchal.blogApp.Model.DTO.CategoryDto;
import com.anchal.blogApp.Model.DTO.CreateCategoryDto;
import com.anchal.blogApp.Model.Entities.Category;
import com.anchal.blogApp.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
        List<CategoryDto> categories = categoryService.listCategories().stream().map(categoryMapper::toDto).toList();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CreateCategoryDto createCategoryDto){
        Category category = categoryMapper.toEntity(createCategoryDto);
        return new ResponseEntity<>(categoryMapper.toDto(categoryService.createCategory(category)), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable UUID id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
