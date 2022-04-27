package com.epam.esm.service.impl;

import com.epam.esm.config.ServiceConfig;
import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringJUnitConfig(ServiceConfig.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("prod")
class TagServiceImplTest {
    Set<Tag> tagSet;

    @InjectMocks
    TagServiceImpl tagService;

    @Mock
    TagDao tagDao;

    @BeforeAll
    void beforeAll() {
        tagSet = Set.of(new Tag(1, "food"), new Tag("summer"));
    }

    @Test
    void testAddTag() {
        Tag tag = new Tag();
        when(tagDao.createTag(tag)).thenReturn(1);

        int actual = tagService.addTag(tag);

        assertEquals(1, actual);
    }

    @Test
    void testAddExistedTag() {
        Tag tag = new Tag();
        when(tagDao.createTag(tag)).thenReturn(0);

        assertThrows(ResourceNotFoundException.class, () -> tagService.addTag(tag));
    }

    @Test
    void testAddTags() {
        when(tagDao.addTags(tagSet)).thenReturn(new int[]{0, 1});

        int[] actual = tagService.addTags(tagSet);

        assertArrayEquals(new int[]{0, 1}, actual);
    }

    @Test
    void testFindAllTags() {
        when(tagDao.readAllTag()).thenReturn(tagSet);

        Set<Tag> actual = tagService.findAllTags();

        assertEquals(tagSet, actual);
    }

    @Test
    void testFindTagsByCertificateId() {
        when(tagDao.readAllTagByCertificateId(1)).thenReturn(tagSet);

        Set<Tag> actual = tagService.findTagsByCertificateId(1);

        assertEquals(tagSet, actual);
    }

    @Test
    void testFindTagById() {
        Tag tag = new Tag();
        when(tagDao.readTag(1)).thenReturn(Optional.of(tag));

        Tag actual = tagService.findTagById(1);

        assertEquals(tag, actual);
    }

    @Test
    void testNotFindTagById() {
        when(tagDao.readTag(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> tagService.findTagById(1));
    }

    @Test
    void testDeleteTag() {
        when(tagDao.deleteTag(1)).thenReturn(1);

        int actual = tagService.deleteTag(1);

        assertEquals(1, actual);
    }

    @Test
    void testNotDeleteTag() {
        when(tagDao.deleteTag(1)).thenReturn(0);

        assertThrows(ResourceNotFoundException.class, () -> tagService.deleteTag(1));
    }
}