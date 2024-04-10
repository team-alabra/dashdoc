package com.dashdocapi.interfaces.enums;

public enum EmailSender {
    DEFAULT("Dashdoc"),
    BILLING("Dashdoc Billing"),
    HELP("Dashdoc Help");
    public final String sender;

    private EmailSender(String sender) {
        this.sender = sender;
    }
}
