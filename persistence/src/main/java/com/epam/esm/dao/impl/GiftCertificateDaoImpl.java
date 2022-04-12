package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;

@Component("giftCertificateDao")
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private static final String EXAMPLE_SQL= """
        SELECT gift_certificate_id, gift_certificate.name, description, price, duration, create_date, last_update_date
        FROM gift_certificate
        JOIN gift_certificate_tag ON gift_certificate_id = gct_gift_certificate_id
        JOIN tag ON gct_tag_id = tag_id
        WHERE tag.name = 'food' AND (gift_certificate.name LIKE '%e%' OR description LIKE '%e%')
        ORDER BY gift_certificate.name, create_date
    """;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public GiftCertificateDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createGiftCertificate(GiftCertificate certificate) {

        return 0;
    }

    @Override
    public List<GiftCertificate> readGiftCertificateWithParam(String sql, List<String> args) {
        List<GiftCertificate> giftCertificates;

        giftCertificates = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GiftCertificate.class), args.toArray());

        return giftCertificates;
    }

    @Override
    public GiftCertificate readGiftCertificate(int id) {
        return null;
    }

    @Override
    public boolean updateGiftCertificate(int id) {
        return false;
    }

    @Override
    public boolean deleteGiftCertificate(int id) {
        return false;
    }
}
