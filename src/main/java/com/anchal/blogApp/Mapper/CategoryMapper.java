package com.anchal.blogApp.Mapper;

import com.anchal.blogApp.Model.DTO.CategoryDto;
import com.anchal.blogApp.Model.DTO.CreateCategoryDto;
import com.anchal.blogApp.Model.Entities.Category;
import com.anchal.blogApp.Model.Entities.Post;
import com.anchal.blogApp.Model.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(source = "posts", target = "postCount", qualifiedByName = "convertPostsToPostCount")
    CategoryDto toDto(Category category);

    Category toEntity(CreateCategoryDto createCategoryDto);

    @Named("convertPostsToPostCount")
    default long convertPostsToPostCount(List<Post> posts){
        if(posts == null) return 0;
        return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }
}
