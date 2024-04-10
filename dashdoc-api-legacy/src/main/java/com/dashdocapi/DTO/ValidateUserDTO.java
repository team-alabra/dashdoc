package com.dashdocapi.DTO;

public class ValidateUserDTO {
    private String email;

    private boolean isValid;

    private String idToken;

    public ValidateUserDTO() {
    }

    public ValidateUserDTO(String email, boolean isValid) {
        this.email = email;
        this.isValid = isValid;
    }

    public ValidateUserDTO(String code) {
        this.idToken = idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "ValidateUserDTO{" +
                "email='" + email + '\'' +
                ", isValid=" + isValid +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}
