package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;

import java.util.List;
import java.util.Optional;

public interface GiftCertificateDao {
    int createGiftCertificate(GiftCertificate certificate);

    List<GiftCertificate> readAllCertificate();

    List<GiftCertificate> readGiftCertificateWithParam(String sql, List<String> args);

    Optional<GiftCertificate> readGiftCertificate(int id);

    int updateGiftCertificate(GiftCertificate certificate);

    int deleteGiftCertificate(int id);
}
