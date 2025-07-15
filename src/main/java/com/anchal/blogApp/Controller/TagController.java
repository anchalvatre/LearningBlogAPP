package com.anchal.blogApp.Controller;

import com.anchal.blogApp.Mapper.TagMapper;
import com.anchal.blogApp.Model.DTO.TagDto;
import com.anchal.blogApp.Model.Entities.Tag;
import com.anchal.blogApp.Service.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @PostMapping
    public ResponseEntity<TagDto> createTag(@Valid @RequestBody TagDto tagDto) {
        Tag tag = tagMapper.toTag(tagDto);
        Tag createdTag = tagService.createTag(tag);
        return new ResponseEntity<>(tagMapper.toTagDto(createdTag), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TagDto>> getTags() {
        List<Tag> tags = tagService.findAllTags();
        return ResponseEntity.ok(tags.stream().map(tagMapper::toTagDto).toList());
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteTag(@PathVariable UUID uuid){
        tagService.deleteTag(uuid);
        return ResponseEntity.noContent().build();
    }

}
