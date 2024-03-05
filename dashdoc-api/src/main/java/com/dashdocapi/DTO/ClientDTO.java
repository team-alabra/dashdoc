package com.dashdocapi.DTO;

import com.dashdocapi.interfaces.enums.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProviderDTO provider;
    private Long providerID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AgencyDTO agency;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long agencyID;
    private String workNumber;
    private String homeNumber;

    private String mobileNumber;
    private String streetAddress;
    private String city;
    private State state;
    private String zipCode;
    private String country;
    private Calendar dob;
    private Gender gender;
    private Grade grade;
    private HomeLanguage preferredSpokenLanguage;
    private String age;

    private AgeGroup ageGroup;

    public ClientDTO(Long id, String firstName, String lastName, Long providerID, Long agencyID, String email, String workNumber, String homeNumber, String mobileNumber, String streetAddress, String city, State state, String zipCode, String country, Calendar dob, Gender gender, Grade grade, HomeLanguage preferredSpokenLanguage, String age, AgeGroup ageGroup) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.providerID = providerID;
        this.email = email;
        this.agencyID = agencyID;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.mobileNumber = mobileNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.dob = dob;
        this.gender = gender;
        this.grade = grade;
        this.preferredSpokenLanguage = preferredSpokenLanguage;
        this.age = age;
        this.ageGroup = ageGroup;
    }

    public ClientDTO() {
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", provider=" + provider +
                ", providerID=" + providerID +
                ", agency=" + agency +
                ", agencyID=" + agencyID +
                ", email ="  + email +
                ", workNumber='" + workNumber + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", grade=" + grade +
                ", preferredSpokenLanguage=" + preferredSpokenLanguage +
                ", age='" + age + '\'' +
                ", ageGroup=" + ageGroup +
                '}';
    }
}
