package com.epam.esm.service;

import com.epam.esm.dto.CertificateTagsDto;
import com.epam.esm.entity.SelectQueryParameter;
import com.epam.esm.exception.ResourceNotFoundException;

import java.util.List;

public interface GiftCertificateTagDtoService {
    int addGiftCertificateTagDto(CertificateTagsDto certificateTagsDto);

    List<CertificateTagsDto> findAllGiftCertificateTagDto();

    List<CertificateTagsDto> findGiftCertificateTagDtoByParam(SelectQueryParameter params);

    CertificateTagsDto findGiftCertificateTagDto(int certificateId) throws ResourceNotFoundException;

    int[] updateGiftCertificateTagDto(CertificateTagsDto certificateTagsDto, int id);
}
