package com.epam.esm.dao;

import com.epam.esm.entity.Tag;
import java.util.Set;

public interface TagDao {
    int createTag(Tag tag);

    int[] addTags(Set<Tag> tags);

    Set<Tag> readAllTag();

    Set<Tag> readAllTagByCertificateId(int certificateId);

    Tag readTag(int id);

    int deleteTag(int id);
}
