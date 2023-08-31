package com.amo.lse.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "venue")
public class VenueEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VENUE_ID")
    private Long id;
    private String name;
    private String city;
    private String country;
    @OneToMany(mappedBy = "venueEntity", cascade = CascadeType.ALL)
    private List<MemberEntity> memberEntities;
    @ManyToMany
    private List<InstrumentEntity> instrumentEntities;

    public VenueEntity(List<InstrumentEntity> instrumentEntities, List<MemberEntity> memberEntities, Long id, String name, String city, String country) {
        this.instrumentEntities = instrumentEntities;
        this.memberEntities = memberEntities;
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public VenueEntity() {
    }

    public void addMember(MemberEntity memberEntity) {
        memberEntities.add(memberEntity);
        memberEntity.setVenue(this);
    }

    public void removeMember(MemberEntity memberEntity) {
        memberEntities.remove(memberEntity);
        memberEntity.setVenue(null);
    }

    public void addInstrument(InstrumentEntity instrumentEntity) {
        instrumentEntities.add(instrumentEntity);
    }

    public List<InstrumentEntity> getInstrumentEntities() {
        return instrumentEntities;
    }

    public void setInstrumentEntities(List<InstrumentEntity> instrumentEntities) {
        this.instrumentEntities = instrumentEntities;
    }

    public List<MemberEntity> getMemberEntities() {
        return memberEntities;
    }

    public void setMemberEntities(List<MemberEntity> memberEntities) {
        this.memberEntities = memberEntities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Venue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
