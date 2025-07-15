package com.anchal.blogApp.Service;

import com.anchal.blogApp.Model.DTO.TagDto;
import com.anchal.blogApp.Model.Entities.Tag;

import java.util.List;
import java.util.UUID;

public interface TagService {

    public Tag createTag(Tag tag);

    List<Tag> findAllTags();

    void deleteTag(UUID uuid);
}
