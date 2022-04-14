package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;
import java.util.List;

public interface GiftCertificateService {
    int addGiftCertificate(GiftCertificate certificate);

    List<GiftCertificate> findAllCertificates();

    List<GiftCertificate> findCertificatesWithParams(SelectParams params);

    GiftCertificate findCertificateById(int certificateId);

    int updateGiftCertificate(GiftCertificate certificate);

    int deleteCertificate(int certificateId);
}
