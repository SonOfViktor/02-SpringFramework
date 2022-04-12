package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;

import java.util.List;

public interface GiftCertificateService {
    List<GiftCertificate> findCertificatesWithParams(SelectParams params);
}
