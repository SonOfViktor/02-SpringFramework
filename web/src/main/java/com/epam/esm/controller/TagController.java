package com.epam.esm.controller;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.Positive;
import java.util.Set;

@RestController
@RequestMapping("/tags")
@Validated
public class TagController {
    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public Set<Tag> showAllTags() {
        Set<Tag> tags = tagService.findAllTags();

        return tags;
    }

    @GetMapping("/{id}")
    public Tag showTag(@PathVariable @Positive int id) {
        Tag tag = tagService.findTagById(id);

        return tag;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tag addTag(@RequestBody Tag tag) {
        int tagId = tagService.addTag(tag);
        tag.setTagId(tagId);

        return tag;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTag(@PathVariable @Positive int id) {
        tagService.deleteTag(id);
    }
}
