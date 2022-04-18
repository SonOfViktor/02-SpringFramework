package com.epam.esm.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class GiftCertificate {
    @Positive
    private int giftCertificateId;

    @NotNull
    @Size(min = 3, max = 45)
    private String name;

    @Size(min = 0, max = 500)
    private String description;

    @Positive
    @Digits(integer = 3, fraction = 2)
    private BigDecimal price;

    @Positive
    @Digits(integer = 2, fraction = 0)
    private int duration;

    @PastOrPresent
    private LocalDateTime createDate;

    @PastOrPresent
    private LocalDateTime lastUpdateDate;

    public GiftCertificate() {
    }

    public GiftCertificate(String name, String description, BigDecimal price, int duration,
                           LocalDateTime createDate, LocalDateTime lastUpdateDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public int getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(int giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftCertificate that = (GiftCertificate) o;
        return giftCertificateId == that.giftCertificateId && duration == that.duration &&
                Objects.equals(name, that.name) && Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) && Objects.equals(createDate, that.createDate) &&
                Objects.equals(lastUpdateDate, that.lastUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftCertificateId, name, description, price, duration, createDate, lastUpdateDate);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("GiftCertificate #").append(giftCertificateId).append("\n")
                .append(", name= ").append(name).append("\n")
                .append("description ").append(description).append("\n")
                .append("duration ").append(duration).append(" day(s)")
                .append("price ").append(price)
                .append(", createDate ").append(createDate)
                .append(", lastUpdateDate ").append(lastUpdateDate);
        return builder.toString();
    }

    public static class GiftCertificateBuilder {
        private GiftCertificate giftCertificate;

        public GiftCertificateBuilder() {
            giftCertificate = new GiftCertificate();
        }

        public GiftCertificateBuilder setGiftCertificateId(int id) {
            giftCertificate.setGiftCertificateId(id);
            return this;
        }

        public GiftCertificateBuilder setName(String name) {
            giftCertificate.setName(name);
            return this;
        }

        public GiftCertificateBuilder setDescription(String description) {
            giftCertificate.setDescription(description);
            return this;
        }

        public GiftCertificateBuilder setPrice(BigDecimal price) {
            giftCertificate.setPrice(price);
            return this;
        }

        public GiftCertificateBuilder setDuration(int duration) {
            giftCertificate.setDuration(duration);
            return this;
        }

        public GiftCertificateBuilder setCreateDate(LocalDateTime dateTime) {
            giftCertificate.setCreateDate(dateTime);
            return this;
        }

        public GiftCertificateBuilder setLastUpdateDate(LocalDateTime updateDateTime) {
            giftCertificate.setLastUpdateDate(updateDateTime);
            return this;
        }

        public GiftCertificate createGiftCertificate() {
            return giftCertificate;
        }
    }
}
