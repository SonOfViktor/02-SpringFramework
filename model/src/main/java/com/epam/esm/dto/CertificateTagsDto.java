package com.epam.esm.dto;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.Set;

public record CertificateTagsDto(GiftCertificate certificate, Set<Tag> tags) {
}
