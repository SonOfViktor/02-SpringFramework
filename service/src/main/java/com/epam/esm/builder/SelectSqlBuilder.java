package com.epam.esm.builder;

import com.epam.esm.validator.SelectQueryParameterValidator;
import com.epam.esm.entity.SelectQueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectSqlBuilder {
    private static final String BEGIN_SELECT_SQL = """
                SELECT gift_certificate_id, gift_certificate.name, description,
                       price, duration, create_date, last_update_date
                FROM gift_certificate
            """;
    private static final String SELECT_JOIN_PART = """
                JOIN gift_certificate_tag ON gift_certificate_id = gct_gift_certificate_id
                JOIN tag ON gct_tag_id = tag_id
            """;
    private static final String WHERE_PART_PATTERN = """
                WHERE %s%s%s%s%s
            """;
    private static final String WHERE_PART_PATTERN_WITH_BRACES = """
                WHERE %s%s(%s%s%s)
            """;
    private static final String ORDER_PART_PATTERN = """
                ORDER BY %s%s%s
            """;
    private static final String EMPTY_LINE = "";
    private static final String TAG_NAME = "tag.name = ? ";
    private static final String CERTIFICATE_NAME = "gift_certificate.name LIKE ? ";
    private static final String CERTIFICATE_DESCRIPTION = "description LIKE ?";
    private static final String OR = "OR ";
    private static final String AND = "AND ";
    private static final String COMMA = ", ";
    private static final String ORDER_NAME = "gift_certificate.name ";
    private static final String ORDER_DATE = "create_date ";
    SelectQueryParameterValidator validator;

    public String buildSelectGiftCertificateSQL(SelectQueryParameter params) {
        StringBuilder querySql = new StringBuilder(BEGIN_SELECT_SQL);

        if (params == null || !validator.isAnyFieldValid(params)) {
            return querySql.toString();
        }

        String tagName = appendQueryJoinPart(querySql, params);
        querySql.append(createQueryWherePart(params, tagName));
        querySql.append(createQueryOrderPart(params));

        return querySql.toString();
    }

    @Autowired
    public void setValidator(SelectQueryParameterValidator validator) {
        this.validator = validator;
    }

    private String appendQueryJoinPart(StringBuilder sql, SelectQueryParameter params) {
        String tagName = "";

        if (validator.isTagNameValid(params)) {
            sql.append(SELECT_JOIN_PART);
            tagName = TAG_NAME;
        }

        return tagName;
    }

    private String createQueryWherePart(SelectQueryParameter params, String tagName) {
        String queryWherePart = "";
        String name = validator.isCertificateNameValid(params) ? CERTIFICATE_NAME : EMPTY_LINE;
        String description = validator.isCertificateDescriptionValid(params) ? CERTIFICATE_DESCRIPTION : EMPTY_LINE;
        String or = "";
        String pattern = WHERE_PART_PATTERN;

        if(!name.isEmpty() && !description.isEmpty()) {
            or = OR;
            pattern = WHERE_PART_PATTERN_WITH_BRACES;
        }

        String and = (!tagName.isEmpty() && !(name.isEmpty() && description.isEmpty())) ? AND : EMPTY_LINE;

        if (!name.isEmpty() || !description.isEmpty() || !tagName.isEmpty()) {
            queryWherePart = String.format(pattern, tagName, and, name, or, description);
        }

        return queryWherePart;
    }

    private String createQueryOrderPart(SelectQueryParameter params) {
        String queryOrderPart = "";
        String orderName = validator.isOrderNameValid(params) ? ORDER_NAME + params.orderName() : EMPTY_LINE;
        String orderDate = validator.isOrderDateValid(params) ? ORDER_DATE + params.orderDate() : EMPTY_LINE;
        String orderComma = (!orderName.isEmpty() && !orderDate.isEmpty()) ? COMMA : EMPTY_LINE;

        if (!orderName.isEmpty() || !orderDate.isEmpty()) {
            queryOrderPart = String.format(ORDER_PART_PATTERN, orderName, orderComma, orderDate);
        }

        return queryOrderPart;
    }
}
