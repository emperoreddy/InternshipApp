package com.amo.lse.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "instrument")
public class InstrumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INSTRUMENT_ID")
    private Long id;
    private String ISIN;
    private String currency;
    private String type;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private Date effectiveDate;
    private String status;
    @ManyToMany
    private List<VenueEntity> venueEntities;
    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private IssuerEntity issuerEntity;

    public InstrumentEntity(IssuerEntity issuerEntity, List<VenueEntity> venueEntities, Long id, String ISIN, String currency, String type, String description, Date effectiveDate, String status) {
        this.issuerEntity = issuerEntity;
        this.venueEntities = venueEntities;
        this.id = id;
        this.ISIN = ISIN;
        this.currency = currency;
        this.type = type;
        this.description = description;
        this.effectiveDate = effectiveDate;
        this.status = status;
    }

    public InstrumentEntity() {
    }

    public IssuerEntity getIssuerEntity() {
        return issuerEntity;
    }

    public void setIssuerEntity(IssuerEntity issuerEntity) {
        this.issuerEntity = issuerEntity;
    }

    public List<VenueEntity> getVenueEntities() {
        return venueEntities;
    }

    public void setVenueEntities(List<VenueEntity> venueEntities) {
        this.venueEntities = venueEntities;
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
        return "Instrument{" +
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
