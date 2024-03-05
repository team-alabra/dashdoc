package com.dashdocapi.entities;

import com.dashdocapi.DTO.ClientDTO;
import com.dashdocapi.interfaces.enums.*;
import com.dashdocapi.utils.Utility;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Entity
@Table(name = "client")
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    private Calendar dob;
    private String email;
    @Column(name = "work_number")
    private String workNumber;
    @Column(name = "home_number")
    private String homeNumber;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name = "street_address")
    private String streetAddress;
    private String city;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name = "zip_code")
    private String zipCode;
    private String country;
    @Enumerated(EnumType.STRING)
    private Grade grade;
    @Column(name = "preferred_spoken_language")
    @Enumerated(EnumType.STRING)
    private HomeLanguage preferredSpokenLanguage;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "provider_id")
    private Provider provider;
    @ManyToOne(fetch= FetchType.LAZY, cascade = {
            CascadeType.REFRESH
    })
    @JoinColumn(name="agency_id")
    private Agency agency;
    @Column(name = "last_updated")
    private Calendar lastUpdated;

    @PreUpdate
    @PrePersist
    protected void onUpdate() {
        lastUpdated = Calendar.getInstance();
    }
    public Client() {
    }

    public Client(Long id, String firstName, String lastName, AgeGroup ageGroup, Calendar dob, String email, String workNumber, String homeNumber, String mobileNumber, Gender gender, String streetAddress, String city, State state, String zipCode, String country, Grade grade, HomeLanguage preferredSpokenLanguage, Provider provider, Agency agency, Calendar lastUpdated) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ageGroup = ageGroup;
        this.dob = dob;
        this.email = email;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.grade = grade;
        this.preferredSpokenLanguage = preferredSpokenLanguage;
        this.provider = provider;
        this.agency = agency;
        this.lastUpdated = lastUpdated;
    }

    public ClientDTO asDTO() {
        Long providerID = getProvider() == null ? null : getProvider().asDTO().getId();
        Grade grade = getAgeGroup() == AgeGroup.ADULT || getAgeGroup() == AgeGroup.EARLY_INTERVENTION ? null : getGrade();
        Long agencyID = getAgency() == null ? null : getAgency().getId();

        return new ClientDTO(
                getId(),
                getFirstName(),
                getLastName(),
                providerID,
                agencyID,
                getEmail(),
                getWorkNumber(),
                getHomeNumber(),
                getMobileNumber(),
                getStreetAddress(),
                getCity(),
                getState(),
                getZipCode(),
                getCountry(),
                getDob(),
                getGender(),
                grade,
                getPreferredSpokenLanguage(),
                Utility.getAge(getDob()),
                getAgeGroup()
        );
    }
}
