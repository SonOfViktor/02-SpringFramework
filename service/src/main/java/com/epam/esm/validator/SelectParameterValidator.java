package com.epam.esm.validator;

import com.epam.esm.entity.SelectParams;
import org.springframework.stereotype.Component;

@Component
public class SelectParameterValidator {
    public boolean isAllFieldValid(SelectParams params) {
        return  (params.tagName() == null || params.tagName().isBlank()) &&
                (params.certificateName() == null || params.certificateName().isBlank()) &&
                (params.certificateDescription() == null || params.certificateDescription().isBlank()) &&
                params.orderName() == null && params.orderDate() == null;
    }

    public boolean isTagNameValid(SelectParams params) {
        return params.tagName() != null && !params.tagName().isBlank();
    }

    public boolean isCertificateNameDescriptionValid(SelectParams params) {
        return (params.certificateName() != null && !params.certificateName().isBlank()) ||
                (params.certificateDescription() != null && !params.certificateDescription().isBlank());
    }

    public boolean isOrderNameValid(SelectParams params) {
        return params.orderName() != null;
    }

    public boolean isOrderDateValid(SelectParams params) {
        return params.orderName() != null;
    }
}
