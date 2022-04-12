package com.epam.esm.entity;

import java.util.Map;

public record SelectParams(
        String tagName,
        String certificateName,
        String certificateDescription,
        SelectOrder orderName,
        SelectOrder orderDate) {
}


