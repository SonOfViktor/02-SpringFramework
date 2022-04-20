package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TagServiceImpl implements TagService {
    private static final String TAG_NOT_FOUND_MESSAGE = "There is no tag with Id %s in database";
    private TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public int addTag(Tag tag) {
        int tagId = tagDao.createTag(tag);

        return tagId;
    }

    @Override
    public int[] addTags(Set<Tag> tags) {
        int[] affectedRow = tagDao.addTags(tags);

        return affectedRow;
    }

    @Override
    public Set<Tag> findAllTags() {
        Set<Tag> tags = tagDao.readAllTag();

        return tags;
    }

    @Override
    public Set<Tag> findTagsByCertificateId(int certificateId) {
        Set<Tag> tags = tagDao.readAllTagByCertificateId(certificateId);

        return tags;
    }

    @Override
    public Tag findTagById(int tagId) throws ResourceNotFoundException {
        Optional<Tag> tagOptional = tagDao.readTag(tagId);

        Tag tag = tagOptional.orElseThrow(() ->
                new ResourceNotFoundException(String.format(TAG_NOT_FOUND_MESSAGE, tagId)));

        return tag;
    }

    @Override
    public int deleteTag(int tagId) {
        int affectedRow = tagDao.deleteTag(tagId);

        return affectedRow;
    }
}
