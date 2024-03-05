package com.dashdocnow.DTO;

import com.dashdocnow.interfaces.enums.Discipline;
import com.dashdocnow.interfaces.enums.Gender;
import com.dashdocnow.interfaces.enums.State;
import com.dashdocnow.interfaces.enums.UserType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Getter
@Setter
public class ProviderDTO {
    private Long id;
    private String email;
    private UserType userType;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AgencyDTO agency;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long agencyID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ClientDTO> clients = new ArrayList<>();
    private SubscriptionDTO subscription;
    private String firstName;
    private String lastName;
    private Gender gender;
    private String streetAddress;
    private String city;
    private State state;
    private String zipCode;
    private String phoneNumber;
    private Date dob;
    private Discipline discipline;

    public ProviderDTO() {
    }
    // This is used as a DBO -> DTO initializer
    public ProviderDTO(Long id, String email, UserType userType, Long agencyID) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.agencyID = agencyID;
    }

    public ProviderDTO(Long id, String email, UserType userType, Long agencyID, SubscriptionDTO subscription) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.agencyID = agencyID;
        this.subscription = subscription;
    }
    public ProviderDTO(String email, UserType userType, Long agencyID) {
        this.email = email;
        this.userType = userType;
        this.agencyID = agencyID;
    }
    public ProviderDTO(String firstName, String lastName, String email, UserType userType, Long agencyID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.agencyID = agencyID;
    }
    public ProviderDTO(Long id, String email, UserType userType, Long agencyID, SubscriptionDTO subscription, String firstName, String lastName, Gender gender, String streetAddress, String city, State state, String zipCode, String phoneNumber, Date dob, Discipline discipline) {
        this.id = id;
        this.email = email;
        this.userType = userType;
        this.agencyID = agencyID;
        this.subscription = subscription;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.discipline = discipline;
    }

    @Override
    public String toString() {
        return "ProviderDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", userType=" + userType +
                ", agency=" + agency +
                ", agencyID=" + agencyID +
                ", clients=" + clients +
                ", subscription=" + subscription +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                ", discipline=" + discipline +
                '}';
    }
}
