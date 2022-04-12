package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagDao {
    int createTag(Tag tag);

    List<Tag> readAllTag();

    Tag readTag(int id);

    boolean deleteTag(int id);
}
