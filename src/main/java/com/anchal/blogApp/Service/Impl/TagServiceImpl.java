package com.anchal.blogApp.Service.Impl;

import com.anchal.blogApp.Model.Entities.Tag;
import com.anchal.blogApp.Repository.TagRepo;
import com.anchal.blogApp.Service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepo tagRepo;

    @Override
    public Tag createTag(Tag tag) {
        if (tagRepo.existsByNameIgnoreCase(tag.getName()))
            throw new IllegalArgumentException("Tag already exist with the name");
        return tagRepo.save(tag);
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepo.findAll();
    }

    @Override
    @Transactional
    public void deleteTag(UUID uuid) {

        tagRepo.findById(uuid).ifPresent(tag -> {
            if(!tag.getPosts().isEmpty())
                throw new IllegalStateException("Cannot delete tag with post");

            tagRepo.deleteById(uuid);
        });

    }
}
