package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import java.util.Set;

public interface TagService {
    int addTag(Tag tag);

    int[] addTags(Set<Tag> tags);

    Set<Tag> findAllTags();

    Set<Tag> findTagsByCertificateId(int certificateId);

    Tag findTagById(int tagId);

    int deleteTag(int tagId);
}
