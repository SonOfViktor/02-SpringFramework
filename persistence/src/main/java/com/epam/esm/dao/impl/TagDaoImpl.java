package com.epam.esm.dao.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {
    private static final String INSERT_TAG_SQL = """
            INSERT INTO tag (name) VALUES (?)
            """;
    private static final String READ_ALL_TAGS_SQL = """
            SELECT tag_id, name FROM tag
            """;
    private static final String READ_TAG_SQL = """
            SELECT tag_id, name FROM tag
            WHERE tag_id = ?
            """;
    private static final String DELETE_TAG_SQL = """
            DELETE FROM tag WHERE tag_id = ?
            """;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TagDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createTag(Tag tag) {
        int affectedRow = jdbcTemplate.update(INSERT_TAG_SQL, tag.getName());

        return affectedRow;
    }

    @Override
    public List<Tag> readAllTag() {
        List<Tag> tags = jdbcTemplate.queryForList(READ_ALL_TAGS_SQL, Tag.class);

        return tags;
    }

    @Override
    public Tag readTag(int id) {
        Tag tag = jdbcTemplate.queryForObject(READ_TAG_SQL, Tag.class, id);

        return tag;
    }

    @Override
    public int deleteTag(int id) {
        int affectedRow = jdbcTemplate.update(DELETE_TAG_SQL, id);

        return affectedRow;
    }
}
