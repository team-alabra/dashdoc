package com.dashdocapi.DTO;

import com.dashdocapi.interfaces.enums.State;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AgencyDTO {
    private Long id;
    private String name;
    @NotNull(message = "Email is a required field")
    private String email;
    private String streetAddress;
    private String city;
    private State state;
    @Size(min = 5, max = 5, message
            = "Zip Code must be 5 characters long")
    private String zipCode;
    private String phoneNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProviderDTO> providers = new ArrayList<>();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ProviderDTO> client = new ArrayList<>();

    public AgencyDTO() {
    }

    public AgencyDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public AgencyDTO(Long id, String name, String email, String streetAddress, String city, State state, String zipCode, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "AgencyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state=" + state +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", providers=" + providers +
                '}';
    }
}

