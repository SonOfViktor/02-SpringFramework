package com.epam.esm.builder;

import com.epam.esm.validator.SelectParameterValidator;
import com.epam.esm.entity.SelectParams;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SqlBuilder {
    private static final Logger logger = LogManager.getLogger();
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
                WHERE %s%s%s
            """;
    private static final String ORDER_PART_PATTERN = """
                ORDER BY %s%s%s
            """;
    private static final String EMPTY_LINE = "";
    private static final String TAG_NAME = "tag.name = ?";
    private static final String CERTIFICATE_NAME_DESCRIPTION = "gift_certificate.name LIKE ? OR description LIKE ? ";
    private static final String AND = "AND ";
    private static final String COMMA = ", ";
    private static final String ORDER_NAME = "gift_certificate.name ";
    private static final String ORDER_DATE = "create_date ";
    SelectParameterValidator validator;

    @Autowired
    public SqlBuilder(SelectParameterValidator validator) {
        this.validator = validator;
    }

    public String buildSelectGiftCertificateSQL(SelectParams params) {
        StringBuilder sql = new StringBuilder(BEGIN_SELECT_SQL);

        if (params == null || validator.isAllFieldValid(params)) {
            logger.log(Level.INFO, "Sql request: \n{}", sql);
            return sql.toString();
        }

        String tagName = appendJoinPart(sql, params);
        appendWherePart(sql, params, tagName);
        appendOrderPart(sql, params);

        logger.log(Level.INFO, "Sql request: \n{}", sql);

        return sql.toString();
    }

    private String appendJoinPart(StringBuilder sql, SelectParams params) {
        String tagName = "";

        if (validator.isTagNameValid(params)) {
            sql.append(SELECT_JOIN_PART);
            tagName = TAG_NAME;
        }

        return tagName;
    }

    private void appendWherePart(StringBuilder sql, SelectParams params, String tagName) {
        String nameDescription = validator.isCertificateNameDescriptionValid(params) ?
                                CERTIFICATE_NAME_DESCRIPTION : EMPTY_LINE;
        String and = (!nameDescription.isEmpty() && !tagName.isEmpty()) ? AND : EMPTY_LINE;

        if (!nameDescription.isEmpty() || !tagName.isEmpty()) {
            String wherePart = String.format(WHERE_PART_PATTERN, nameDescription, and, tagName);
            sql.append(wherePart);
        }
    }

    private void appendOrderPart(StringBuilder sql, SelectParams params) {
        String orderName = validator.isOrderNameValid(params) ? ORDER_NAME + params.orderName() : EMPTY_LINE;
        String orderDate = validator.isOrderDateValid(params) ? ORDER_DATE + params.orderDate() : EMPTY_LINE;
        String orderComma = (!orderName.isEmpty() && !orderDate.isEmpty()) ? COMMA : EMPTY_LINE;

        if (!orderName.isEmpty() || !orderDate.isEmpty()) {
            String orderPart = String.format(ORDER_PART_PATTERN, orderName, orderComma, orderDate);
            sql.append(orderPart);
        }
    }
}
