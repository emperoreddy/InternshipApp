package com.amo.lse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class InstrumentDTO {

    private Long id;
    private String ISIN;
    private String currency;
    private String type;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date effectiveDate;
    private String status;
    private Long issuerId;

    public InstrumentDTO(Long issuerId, Long id, String ISIN, String currency, String type, String description, Date effectiveDate, String status) {
        this.issuerId = issuerId;
        this.id = id;
        this.ISIN = ISIN;
        this.currency = currency;
        this.type = type;
        this.description = description;
        this.effectiveDate = effectiveDate;
        this.status = status;
    }

    public InstrumentDTO() {
    }

    public Long getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(Long issuerId) {
        this.issuerId = issuerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getISIN() {
        return ISIN;
    }

    public void setISIN(String ISIN) {
        this.ISIN = ISIN;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "InstrumentDTO{" +
                "id=" + id +
                ", ISIN='" + ISIN + '\'' +
                ", currency='" + currency + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", status=" + status +
                '}';
    }
}
