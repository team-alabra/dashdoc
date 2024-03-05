package com.dashdocnow.DTO;

public class StripeChargeRequestDTO {

    private String email;

    private Long planID;

    public StripeChargeRequestDTO() {
    }

    public StripeChargeRequestDTO(String email, Long planID) {
        this.email = email;
        this.planID = planID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPlanID() {
        return planID;
    }

    public void setPlanID(Long planID) {
        this.planID = planID;
    }

    @Override
    public String toString() {
        return "StripeChargeRequestDTO{" +
                "email='" + email + '\'' +
                ", planID=" + planID +
                '}';
    }
}
