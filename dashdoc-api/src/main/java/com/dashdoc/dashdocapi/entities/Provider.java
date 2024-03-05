package com.dashdocnow.entities;

import com.dashdocnow.DTO.ProviderDTO;
import com.dashdocnow.interfaces.enums.Discipline;
import com.dashdocnow.interfaces.enums.Gender;
import com.dashdocnow.interfaces.enums.State;
import com.dashdocnow.interfaces.enums.UserType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "provider")
@Getter
@Setter
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {
            CascadeType.REFRESH,
            CascadeType.DETACH,
    })
    @JoinColumn(name = "agency_id")
    private Agency agency;
    // bidirectional association between Provider/User and Student
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provider", cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private List<Client> clients;
    @OneToOne(fetch = FetchType.EAGER, orphanRemoval = true, cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Column(name="street_address")
    private String streetAddress;
    private String city;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(name="zip_code")
    private String zipCode;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="birth_date")
    private Date dob;
    @Enumerated(EnumType.STRING)
    private Discipline discipline;
    @Column(name="last_updated")
    private Calendar lastUpdated = Calendar.getInstance();
    @Column(name="created_on")
    private Calendar createdOn = Calendar.getInstance();

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = Calendar.getInstance();
    }

    public Provider() {
    }

    public Provider(Long id, String email, UserType userType, Agency agency) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.agency = agency;
    }

    public Provider(Long id, String email, UserType userType, Agency agency, Subscription subscription) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.agency = agency;
        this.subscription = subscription;
    }

    public Provider(String email, UserType userType, Agency agency, Subscription subscription) {
        this.email = email;
        this.userType = userType;
        this.agency = agency;
        this.subscription = subscription;
    }

    public Provider(String email, UserType userType, Agency agency) {
        this.email = email;
        this.userType = userType;
        this.agency = agency;
    }

    public Provider(Long id, String email, UserType userType, Agency agency, List<Client> clients,  Subscription subscription) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.agency = agency;
        this.clients = clients;
        this.subscription = subscription;
    }

    // A helper class to manually map fields
    // This makes it so that Dozer doesn't fetch lazy loaded fields (like agency)
    public ProviderDTO asDTO(){
        Long agencyID = getAgency() == null ? null : getAgency().getId();
        var subscription = getSubscription() != null ? getSubscription().asDTO() : null;

        return new ProviderDTO(
                getId(),
                getEmail(),
                getUserType(),
                agencyID,
                subscription,
                getFirstName(),
                getLastName(),
                getGender(),
                getStreetAddress(),
                getCity(),
                getState(),
                getZipCode(),
                getPhoneNumber(),
                getDob(),
                getDiscipline()
        );
    }
}
