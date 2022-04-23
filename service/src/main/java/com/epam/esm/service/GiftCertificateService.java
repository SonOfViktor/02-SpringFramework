package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectQueryParameter;
import com.epam.esm.exception.ResourceNotFoundException;

import java.util.List;

public interface GiftCertificateService {
    int addGiftCertificate(GiftCertificate certificate);

    List<GiftCertificate> findAllCertificates();

    List<GiftCertificate> findCertificatesWithParams(SelectQueryParameter params);

    GiftCertificate findCertificateById(int certificateId);

    int updateGiftCertificate(GiftCertificate certificate, int id);

    int deleteCertificate(int certificateId);
}
