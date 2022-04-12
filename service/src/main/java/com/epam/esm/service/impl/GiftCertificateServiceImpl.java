package com.epam.esm.service.impl;

import com.epam.esm.builder.SelectSqlBuilder;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectParams;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {
    private static final String NAME_DESCRIPTION_PATTERN = "%%%s%%";
    private GiftCertificateDao giftCertificateDao;
    private SelectSqlBuilder builder;

    @Autowired
    public GiftCertificateServiceImpl(GiftCertificateDao giftCertificateDao, SelectSqlBuilder builder) {
        this.giftCertificateDao = giftCertificateDao;
        this.builder = builder;
    }

    @Override
    public List<GiftCertificate> findCertificatesWithParams(SelectParams params) {
//        SqlBuilder builder = new SqlBuilder();

        String findCertificatesSql = builder.buildSelectGiftCertificateSQL(params);
        List<String> args = defineArguments(params);

        List<GiftCertificate> certificates = giftCertificateDao.readGiftCertificateWithParam(findCertificatesSql, args);

        return certificates;
    }

    private List<String> defineArguments(SelectParams params) {
        List<String> args = new ArrayList<>();

        if(params.certificateName() != null && !params.certificateName().isBlank()) {
            args.add(String.format(NAME_DESCRIPTION_PATTERN, params.certificateName()));
        }

        if(params.certificateDescription() != null && !params.certificateDescription().isBlank()) {
            args.add(String.format(NAME_DESCRIPTION_PATTERN, params.certificateDescription()));
        }

        if(params.tagName() != null && !params.tagName().isBlank()) {
            args.add(params.tagName());
        }

        System.out.println(args);
        return args;
    }
}
