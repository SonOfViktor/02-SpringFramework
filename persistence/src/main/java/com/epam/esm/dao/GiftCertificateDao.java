package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;

import java.util.List;

public interface GiftCertificateDao {
    int createGiftCertificate(GiftCertificate certificate);

    List<GiftCertificate> readAllCertificate();

    List<GiftCertificate> readGiftCertificateWithParam(String sql, List<String> args);

    GiftCertificate readGiftCertificate(int id);

    int updateGiftCertificate(int id);

    int deleteGiftCertificate(int id);
}
