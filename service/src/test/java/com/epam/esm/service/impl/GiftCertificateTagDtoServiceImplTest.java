package com.epam.esm.service.impl;

import com.epam.esm.config.ServiceConfig;
import com.epam.esm.dao.impl.GiftCertificateDaoImpl;
import com.epam.esm.dao.impl.GiftCertificateTagDaoImpl;
import com.epam.esm.dto.CertificateTagsDto;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.SelectQueryParameter;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringJUnitConfig(ServiceConfig.class)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("prod")
class GiftCertificateTagDtoServiceImplTest {
    private List<GiftCertificate> giftCertificateList;
    private List<CertificateTagsDto> certificateTagsDtoList;
    private Set<Tag> tagSet;

    @InjectMocks
    private GiftCertificateTagDtoServiceImpl giftCertificateTagDtoService;

    @Mock
    private GiftCertificateTagDaoImpl giftCertificateTagDao;

    @Mock
    private GiftCertificateServiceImpl giftCertificateService;

    @Mock
    private TagServiceImpl tagService;

    @BeforeAll
    void beforeAll() {
        giftCertificateList = List.of(new GiftCertificate(), new GiftCertificate());
        giftCertificateList.get(0).setGiftCertificateId(1);
        giftCertificateList.get(1).setGiftCertificateId(2);

        tagSet = Set.of(new Tag("food"), new Tag("summer"));

        certificateTagsDtoList = giftCertificateList.stream()
                .map((cert) -> new CertificateTagsDto(cert, tagSet))
                .toList();
    }

    @Test
    void testAddGiftCertificateTagDto() {
        CertificateTagsDto certificateTagsDto = new CertificateTagsDto(new GiftCertificate(), Set.of());
        when(giftCertificateService.addGiftCertificate(certificateTagsDto.certificate())).thenReturn(1);

        int actual = giftCertificateTagDtoService.addGiftCertificateTagDto(certificateTagsDto);

        assertEquals(1, actual);
    }

    @Test
    void testFindAllGiftCertificateTagDto() {
        when(giftCertificateService.findAllCertificates()).thenReturn(giftCertificateList);
        when(tagService.findTagsByCertificateId(anyInt())).thenReturn(tagSet);

        List<CertificateTagsDto> actual = giftCertificateTagDtoService.findAllGiftCertificateTagDto();

        assertEquals(certificateTagsDtoList, actual);
    }

    @Test
    void testFindGiftCertificateTagDtoByParam() {
        SelectQueryParameter selectQueryParameter = new SelectQueryParameter(null, null, null, null, null);
        when(giftCertificateService.findCertificatesWithParams(selectQueryParameter)).thenReturn(giftCertificateList);
        when(tagService.findTagsByCertificateId(anyInt())).thenReturn(tagSet);

        List<CertificateTagsDto> actual = giftCertificateTagDtoService
                .findGiftCertificateTagDtoByParam(selectQueryParameter);

        assertEquals(certificateTagsDtoList, actual);
    }

    @Test
    void testFindGiftCertificateTagDto() {
        when(giftCertificateService.findCertificateById(anyInt())).thenReturn(giftCertificateList.get(0));
        when(tagService.findTagsByCertificateId(anyInt())).thenReturn(tagSet);

        CertificateTagsDto actual = giftCertificateTagDtoService.findGiftCertificateTagDto(1);

        assertEquals(certificateTagsDtoList.get(0), actual);
    }

    @Test
    void testUpdateGiftCertificateTagDto() {
        when(giftCertificateTagDao.createGiftCertificateTagEntries(anyInt(), eq(tagSet))).thenReturn(new int[] {1, 0, 1});

        int[] actual = giftCertificateTagDtoService.updateGiftCertificateTagDto(certificateTagsDtoList.get(0), 1);

        assertArrayEquals(new int[] {1, 0, 1}, actual);
    }


}