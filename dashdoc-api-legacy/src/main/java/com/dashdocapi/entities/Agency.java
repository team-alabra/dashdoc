package com.dashdocapi.entities;

import com.dashdocapi.DTO.AgencyDTO;
import com.dashdocapi.interfaces.enums.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "agency")
@Getter
@Setter
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "street_address")
    private String streetAddress;
    private String city;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "zip_code")
    private String zipCode;
    @JsonManagedReference
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agency", cascade= {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<Provider> providers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agency", cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<Client> clients;
    @Column(name = "last_updated")
    private Date lastUpdated = new Date();
    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Date();
    }

    public Agency() {}

    public Agency(Long id, String name, String email, String phoneNumber, String streetAddress, String city, State state, String zipCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public AgencyDTO asDTO() {
        return new AgencyDTO(
                getId(),
                getName(),
                getEmail(),
                getStreetAddress(),
                getCity(),
                getState(),
                getZipCode(),
                getPhoneNumber()
        );
    }
}