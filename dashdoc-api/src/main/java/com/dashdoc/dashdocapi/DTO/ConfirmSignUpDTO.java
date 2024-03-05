package com.dashdocnow.DTO;

public class ConfirmSignUpDTO {
    private String email;
    private String otpCode;
    public ConfirmSignUpDTO() {
    }
    public ConfirmSignUpDTO(String email, String otpCode) {
        this.email = email;
        this.otpCode = otpCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtpCode() {
        return otpCode;
    }
    public void setOtpCode(String otpCode) {
        this.otpCode = otpCode;
    }

    @Override
    public String toString() {
        return "ConfirmSignUpDTO{" +
                "email='" + email + '\'' +
                ", otpCode='" + otpCode + '\'' +
                '}';
    }
}
