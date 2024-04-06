package com.dashdocapi.DTO;

import com.dashdocapi.interfaces.enums.UserType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequest  {
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private UserType userType;
    private String agencyName;
    private Long subscriptionPlanID;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;

    public UserSignUpRequest() {
    }
    public UserSignUpRequest(String email, String password, UserType userType, String agencyName,  Long subscriptionPlanID) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.agencyName = agencyName;
        this.subscriptionPlanID = subscriptionPlanID;
    }
    public UserSignUpRequest(String email, String password, UserType userType, String agencyName) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.agencyName = agencyName;
    }
    public UserSignUpRequest(String email, String password, UserType userType, String agencyName, Long subscriptionPlanID, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.agencyName = agencyName;
        this.subscriptionPlanID = subscriptionPlanID;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public UserSignUpRequest(String email) {
        this.email = email;
    }
    public UserSignUpRequest(String email, String password, UserType userType) {
        this(email, password, userType, null, null);
    }
    @Override
    public String toString() {
        return "UserSignUpRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", agencyName='" + agencyName + '\'' +
                ", subscriptionPlanID=" + subscriptionPlanID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
