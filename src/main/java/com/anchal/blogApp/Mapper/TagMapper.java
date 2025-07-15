package com.anchal.blogApp.Mapper;

import com.anchal.blogApp.Model.DTO.TagDto;
import com.anchal.blogApp.Model.Entities.Post;
import com.anchal.blogApp.Model.Entities.Tag;
import com.anchal.blogApp.Model.PostStatus;
import jakarta.validation.groups.Default;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    @Mapping(source = "posts", target = "postCount", qualifiedByName = "convertPostToPostCounts")
    TagDto toTagDto(Tag tag);

    Tag toTag(TagDto tagDto);

    @Named("convertPostToPostCounts")
    default long convertPostToPostCounts(Set<Post> posts){
        if(posts == null) return 0;
        return posts.stream().filter(post -> PostStatus.PUBLISHED.equals(post.getStatus())).count();
    }
}
