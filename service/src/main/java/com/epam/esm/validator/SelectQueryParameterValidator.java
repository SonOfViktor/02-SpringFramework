package com.epam.esm.validator;

import com.epam.esm.entity.SelectQueryParameter;
import org.springframework.stereotype.Component;

@Component
public class SelectQueryParameterValidator {
    public boolean isAnyFieldValid(SelectQueryParameter params) {
        return  isTagNameValid(params) || isCertificateNameValid(params) || isCertificateDescriptionValid(params) ||
                params.orderName() != null || params.orderDate() != null;
    }

    public boolean isTagNameValid(SelectQueryParameter params) {
        return params.tagName() != null && !params.tagName().isBlank();
    }

    public boolean isCertificateNameValid (SelectQueryParameter params) {
        return (params.certificateName() != null && !params.certificateName().isBlank());
    }

    public boolean isCertificateDescriptionValid (SelectQueryParameter params) {
        return (params.certificateDescription() != null && !params.certificateDescription().isBlank());
    }

    public boolean isCertificateNameDescriptionValid(SelectQueryParameter params) {
        return isCertificateNameValid(params) || isCertificateDescriptionValid(params);
    }

    public boolean isOrderNameValid(SelectQueryParameter params) {
        return params.orderName() != null;
    }

    public boolean isOrderDateValid(SelectQueryParameter params) {
        return params.orderDate() != null;
    }
}
