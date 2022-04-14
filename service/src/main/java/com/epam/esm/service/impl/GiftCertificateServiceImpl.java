package com.epam.esm.service.impl;

import com.epam.esm.builder.SelectSqlBuilder;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.validator.SelectParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private static final String NAME_DESCRIPTION_PATTERN = "%%%s%%";
    private GiftCertificateDao giftCertificateDao;
    private SelectSqlBuilder builder;
    private SelectParameterValidator validator;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, SelectSqlBuilder builder,
                                      SelectParameterValidator validator) {
        this.giftCertificateDao = giftCertificateDao;
        this.builder = builder;
        this.validator = validator;
    }

    @Override
    public int addGiftCertificate(GiftCertificate certificate) {
        int certificateId = giftCertificateDao.createGiftCertificate(certificate);

        return certificateId;
    }

    @Override
    public List<GiftCertificate> findAllCertificates() {
        List<GiftCertificate> certificates = giftCertificateDao.readAllCertificate();

        return certificates;
    }

    @Override
    public List<GiftCertificate> findCertificatesWithParams(SelectParams params) {
        String findCertificatesSql = builder.buildSelectGiftCertificateSQL(params);
        List<String> args = defineArguments(params);

        List<GiftCertificate> certificates = giftCertificateDao.readGiftCertificateWithParam(findCertificatesSql, args);

        return certificates;
    }

    @Override
    public GiftCertificate findCertificateById(int certificateId) {
        GiftCertificate certificate = giftCertificateDao.readGiftCertificate(certificateId);

        return certificate;
    }

    @Override
    public int updateGiftCertificate(GiftCertificate certificate) {
        int affectedRow = giftCertificateDao.updateGiftCertificate(certificate);

        return affectedRow;
    }

    @Override
    public int deleteCertificate(int certificateId) {
        int affectedRow = giftCertificateDao.deleteGiftCertificate(certificateId);

        return affectedRow;
    }

    private List<String> defineArguments(SelectParams params) {
        List<String> args = new ArrayList<>();

        if(validator.isTagNameValid(params)) {
            args.add(params.tagName());
        }

        if(validator.isCertificateNameValid(params)) {
            args.add(String.format(NAME_DESCRIPTION_PATTERN, params.certificateName()));
        }

        if(validator.isCertificateDescriptionValid(params)) {
            args.add(String.format(NAME_DESCRIPTION_PATTERN, params.certificateDescription()));
        }

        return args;
    }
}
