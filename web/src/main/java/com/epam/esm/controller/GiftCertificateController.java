package com.epam.esm.controller;

import com.epam.esm.dto.CertificateTagsDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectQueryParameter;
import com.epam.esm.exception.ResourceNotFoundException;
import com.epam.esm.service.GiftCertificateService;
import com.epam.esm.service.GiftCertificateTagDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/certificates")
public class GiftCertificateController {
    private GiftCertificateTagDtoService certificateTagService;
    private GiftCertificateService certificateService;

    @Autowired
    public GiftCertificateController(GiftCertificateTagDtoService certificateTagService,
                                     GiftCertificateService certificateService) {
        this.certificateTagService = certificateTagService;
        this.certificateService = certificateService;
    }

    @GetMapping
    public List<CertificateTagsDto> showAllGiftCertificates() {
        List<CertificateTagsDto> allCertificates = certificateTagService.findAllGiftCertificateTagDto();

        return allCertificates;
    }

    @GetMapping("/{id}")
    public CertificateTagsDto showCertificate(@PathVariable int id) throws ResourceNotFoundException {
        CertificateTagsDto certificate = certificateTagService.findGiftCertificateTagDto(id);

        return certificate;
    }

    @PostMapping("/param")
    public List<CertificateTagsDto> showCertificateWithParameters(@RequestBody SelectQueryParameter queryParam) {
        List<CertificateTagsDto> certificateWithParameters =
                certificateTagService.findGiftCertificateTagDtoByParam(queryParam);
        return certificateWithParameters;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public int addCertificate(@RequestBody CertificateTagsDto certificateTagsDto) {
        int id = certificateTagService.addGiftCertificateTagDto(certificateTagsDto);

        return id;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCertificate(@RequestBody @Valid CertificateTagsDto certificateTagDto, @PathVariable int id) {
        certificateTagService.updateGiftCertificateTagDto(certificateTagDto, id);
    }
//
//    @PatchMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void updateCertificate(@Valid @RequestBody GiftCertificate certificate, @PathVariable int id) {
//        certificateService.updateGiftCertificate(certificate, id);
//    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCertificate(@PathVariable int id) {
        certificateService.deleteCertificate(id);
    }
}
