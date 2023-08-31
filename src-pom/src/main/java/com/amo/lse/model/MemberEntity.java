package com.amo.lse.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;
    private String LEI;
    private String legalName;
    private String description;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private VenueEntity venueEntity;

    public MemberEntity(Long id, String LEI, String legalName, String description, String address, VenueEntity venueEntity) {
        this.id = id;
        this.LEI = LEI;
        this.legalName = legalName;
        this.description = description;
        this.address = address;
        this.venueEntity = venueEntity;
    }

    public MemberEntity() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberEntity)) return false;
        MemberEntity memberEntity = (MemberEntity) o;
        return Objects.equals(id, memberEntity.id) && Objects.equals(LEI, memberEntity.LEI) && Objects.equals(legalName, memberEntity.legalName) && Objects.equals(description, memberEntity.description) && Objects.equals(address, memberEntity.address) && Objects.equals(venueEntity, memberEntity.venueEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, LEI, legalName, description, address, venueEntity);
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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", LEI='" + LEI + '\'' +
                ", legalName='" + legalName + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public VenueEntity getVenue() {
        return venueEntity;
    }

    public void setVenue(VenueEntity venueEntity) {
        this.venueEntity = venueEntity;
    }
}
