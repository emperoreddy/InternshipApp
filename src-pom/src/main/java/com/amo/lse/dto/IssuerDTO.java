package com.amo.lse.dto;

public class IssuerDTO {
    private Long id;
    private String LEI;
    private String legalName;
    private String description;

    public IssuerDTO(Long id, String LEI, String legalName, String description) {
        this.id = id;
        this.LEI = LEI;
        this.legalName = legalName;
        this.description = description;
    }

    public IssuerDTO() {
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
        return "IssuerDTO{" +
                "id=" + id +
                ", LEI='" + LEI + '\'' +
                ", legalName='" + legalName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
