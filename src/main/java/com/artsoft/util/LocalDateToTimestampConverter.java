package com.artsoft.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateToTimestampConverter implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(LocalDateTime entityValue) {
        if (entityValue != null) {
            return java.sql.Timestamp.valueOf(entityValue);
        }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(java.sql.Timestamp databaseValue) {
        if (databaseValue != null) {
            return databaseValue.toLocalDateTime();
        }
        return null;
    }
}