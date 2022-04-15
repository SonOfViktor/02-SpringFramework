package com.epam.esm.entity;

public record SelectQueryParameter(
        String tagName,
        String certificateName,
        String certificateDescription,
        SelectQueryOrder orderName,
        SelectQueryOrder orderDate) {
}


