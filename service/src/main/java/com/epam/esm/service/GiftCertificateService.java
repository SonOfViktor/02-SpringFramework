package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectQueryParameter;
import com.epam.esm.exception.DataNotFoundException;

import java.util.List;

public interface GiftCertificateService {
    int addGiftCertificate(GiftCertificate certificate);

    List<GiftCertificate> findAllCertificates();

    List<GiftCertificate> findCertificatesWithParams(SelectQueryParameter params);

    GiftCertificate findCertificateById(int certificateId) throws DataNotFoundException;

    int updateGiftCertificate(GiftCertificate certificate);

    int deleteCertificate(int certificateId);
}
