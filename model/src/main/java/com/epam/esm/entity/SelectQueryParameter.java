package com.epam.esm.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record SelectQueryParameter(
        @NotBlank(message = "Name field must not be blank")
        @Size(min = 2, max = 45, message = "Tag name must be between 2 to 45 characters")
        String tagName,

        @Size(max = 45, message = "Number of symbols of name must be less than 46")
        String certificateName,

        @Size(max = 45, message = "Number of symbols of description must be less than 46")
        String certificateDescription,

        SelectQueryOrder orderName,
        SelectQueryOrder orderDate) {
}


