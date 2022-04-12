package com.epam.esm.dto;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

import java.util.Set;

public record CertificateWithTags(GiftCertificate certificate, Set<Tag> tags) {
}
