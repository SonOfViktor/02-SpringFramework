package com.epam.esm.controller;

import com.epam.esm.dto.CertificateTagsDto;
import com.epam.esm.exception.DataNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftCertificateTagDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
public class GiftCertificateController {
    private GiftCertificateTagDtoService certificateTagService;
    private GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateTagDtoService certificateTagService,
                                     GiftCertificateService certificateService) {
        this.certificateTagService = certificateTagService;
        this.certificateService = certificateService;
    }

    @GetMapping("/certificates")
    public List<CertificateTagsDto> showAllGiftCertificates() {
        List<CertificateTagsDto> allCertificates = certificateTagService.findAllGiftCertificateTagDto();

        return allCertificates;
    }

    @GetMapping("/certificates/{id}")
    public CertificateTagsDto showCertificate(@PathVariable int id) throws DataNotFoundException {
        CertificateTagsDto certificate = certificateTagService.findGiftCertificateTagDto(id);

        return certificate;
    }
}
