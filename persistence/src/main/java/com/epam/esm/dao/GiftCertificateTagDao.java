package com.epam.esm.dao;

import com.epam.esm.entity.Tag;

import java.util.Set;

public interface GiftCertificateTagDao {
    int[] createGiftCertificateTagEntries(int certificateId, Set<Tag> tags);
}
