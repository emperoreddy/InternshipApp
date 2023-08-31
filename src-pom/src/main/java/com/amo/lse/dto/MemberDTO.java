package com.amo.lse.dto;


public class MemberDTO {
    private Long id;
    private String LEI;
    private String legalName;
    private String description;
    private String address;
    private Long venueId;

    public MemberDTO(Long id, String LEI, String legalName, String description, String address, Long venueId) {
        this.id = id;
        this.LEI = LEI;
        this.legalName = legalName;
        this.description = description;
        this.address = address;
        this.venueId = venueId;
    }

    public MemberDTO() {

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }
}
