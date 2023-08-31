package com.amo.lse.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "issuer")
public class IssuerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISSUER_ID")
    private Long id;
    private String LEI;
    private String legalName;
    private String description;
    @OneToMany(mappedBy = "issuerEntity", cascade = CascadeType.ALL)
    private List<InstrumentEntity> instrumentEntities;

    public IssuerEntity(List<InstrumentEntity> instrumentEntities, Long id, String LEI, String legalName, String description) {
        this.instrumentEntities = instrumentEntities;
        this.id = id;
        this.LEI = LEI;
        this.legalName = legalName;
        this.description = description;
    }

    public IssuerEntity() {
    }

    public void addInstrument(InstrumentEntity instrumentEntity) {
       instrumentEntities.add(instrumentEntity);
       instrumentEntity.setIssuerEntity(this);
    }

    public void removeInstrument(InstrumentEntity instrumentEntity) {
        instrumentEntities.remove(instrumentEntity);
        instrumentEntity.setIssuerEntity(null);
    }
    public List<InstrumentEntity> getInstrumentEntities() {
        return instrumentEntities;
    }

    public void setInstrumentEntities(List<InstrumentEntity> instrumentEntities) {
        this.instrumentEntities = instrumentEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLEI() {
        return LEI;
    }

    public void setLEI(String LEI) {
        this.LEI = LEI;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Issuer{" +
                "id=" + id +
                ", LEI='" + LEI + '\'' +
                ", legalName='" + legalName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
