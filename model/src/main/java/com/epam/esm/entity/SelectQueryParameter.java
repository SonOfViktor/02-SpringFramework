package com.epam.esm.entity;

import javax.validation.constraints.Size;

public record SelectQueryParameter(
        @Size(min = 2, max = 45)
        String tagName,

        @Size(min = 3, max = 45)
        String certificateName,

        @Size(min = 1, max = 45)
        String certificateDescription,

        SelectQueryOrder orderName,
        SelectQueryOrder orderDate) {
}


