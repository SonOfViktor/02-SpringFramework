package com.epam.esm.dao;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;

import java.util.List;

public interface GiftCertificateDao {
    int createGiftCertificate(GiftCertificate certificate);

    List<GiftCertificate> readGiftCertificateWithParam(String sql, List<String> args);

    GiftCertificate readGiftCertificate(int id);

    boolean updateGiftCertificate(int id);

    boolean deleteGiftCertificate(int id);
}
