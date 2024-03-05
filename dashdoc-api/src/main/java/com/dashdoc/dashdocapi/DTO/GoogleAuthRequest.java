package com.dashdocnow.DTO;

public class GoogleAuthRequest {

    private String email;
    private String isEmailVerified;

    private String idToken;

    public GoogleAuthRequest() {
    }

    public GoogleAuthRequest(String email, String isEmailVerified) {
        this.email = email;
        this.isEmailVerified = isEmailVerified;
    }

    public GoogleAuthRequest(String email, String isEmailVerified, String idToken) {
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.idToken = idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        isEmailVerified = emailVerified;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "GoogleAuthRequest{" +
                "email='" + email + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", idToken='" + idToken + '\'' +
                '}';
    }
}
