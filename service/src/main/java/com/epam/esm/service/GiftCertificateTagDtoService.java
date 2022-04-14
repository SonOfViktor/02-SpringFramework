package com.epam.esm.service;

import com.epam.esm.dto.CertificateTagsDto;
import com.epam.esm.entity.SelectParams;
import java.util.List;

public interface GiftCertificateTagDtoService {
    int[] addGiftCertificateTagDto(CertificateTagsDto certificateTagsDto);

    List<CertificateTagsDto> findAllGiftCertificateTagDto();

    List<CertificateTagsDto> findGiftCertificateTagDtoByParam(SelectParams params);

    CertificateTagsDto findGiftCertificateTagDto(int certificateId);

    int updateGiftCertificateTagDto(CertificateTagsDto certificateTagsDto);
}
