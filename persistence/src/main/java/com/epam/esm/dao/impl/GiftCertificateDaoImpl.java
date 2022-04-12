package com.epam.esm.dao.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public class GiftCertificateDaoImpl implements GiftCertificateDao {
    private static final String READ_ALL_CERTIFICATE_SQL = """
            SELECT gift_certificate_id, gift_certificate.name, description, price, duration, create_date,
                last_update_date
            FROM gift_certificate
            """;
    private static final String READ_CERTIFICATE_BY_ID_SQL = """
            SELECT gift_certificate_id, gift_certificate.name, description, price, duration, create_date,
                last_update_date
            FROM gift_certificate
            WHERE gift_certificate_id = ?
            """;
    private static final String DELETE_CERTIFICATE_SQL = """
            DELETE FROM gift_certificate
            WHERE gift_certificate_id = ?
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
    public List<GiftCertificate> readAllCertificate() {
        List<GiftCertificate> giftCertificates;

        giftCertificates = jdbcTemplate.queryForList(READ_ALL_CERTIFICATE_SQL, GiftCertificate.class);

        return giftCertificates;
    }

    @Override
    public List<GiftCertificate> readGiftCertificateWithParam(String sql, List<String> args) {
        List<GiftCertificate> giftCertificates;

        giftCertificates = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GiftCertificate.class), args.toArray());

        return giftCertificates;
    }

    @Override
    public GiftCertificate readGiftCertificate(int id) {
        GiftCertificate giftCertificates;

        giftCertificates = jdbcTemplate.queryForObject(READ_CERTIFICATE_BY_ID_SQL, GiftCertificate.class, id);

        return giftCertificates;
    }

    @Override
    public int updateGiftCertificate(int id) {
        return 0;
    }

    @Override
    public int deleteGiftCertificate(int id) {
        int affectedRow = jdbcTemplate.update(DELETE_CERTIFICATE_SQL, id);

        return affectedRow;
    }
}
