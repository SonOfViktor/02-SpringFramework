package com.epam.esm.validator;

import com.epam.esm.entity.SelectParams;
import org.springframework.stereotype.Component;

@Component
public class SelectParameterValidator {
    public boolean isAnyFieldValid(SelectParams params) {
        return  isTagNameValid(params) || isCertificateNameValid(params) || isCertificateDescriptionValid(params) ||
                params.orderName() != null || params.orderDate() != null;
    }

    public boolean isTagNameValid(SelectParams params) {
        return params.tagName() != null && !params.tagName().isBlank();
    }

    public boolean isCertificateNameValid (SelectParams params) {
        return (params.certificateName() != null && !params.certificateName().isBlank());
    }

    public boolean isCertificateDescriptionValid (SelectParams params) {
        return (params.certificateDescription() != null && !params.certificateDescription().isBlank());
    }

    public boolean isCertificateNameDescriptionValid(SelectParams params) {
        return isCertificateNameValid(params) || isCertificateDescriptionValid(params);
    }

    public boolean isOrderNameValid(SelectParams params) {
        return params.orderName() != null;
    }

    public boolean isOrderDateValid(SelectParams params) {
        return params.orderDate() != null;
    }
}
