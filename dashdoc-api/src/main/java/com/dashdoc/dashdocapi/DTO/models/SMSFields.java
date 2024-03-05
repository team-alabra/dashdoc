package com.dashdocnow.DTO.models;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

@Getter
@Setter
public class SMSFields {
    /*
        one time passcodes on signup
    */
    private String passCode = null;
    /*
        Password Reset links
        Scheduling
        Signatures (?)
    */
    private String link = null;
    /*
        appointment dates
        deadlines
        billing
    */
    private DateTime date = null;

    public SMSFields() {
    }

    public SMSFields(String passCode, String link, DateTime date) {
        this.passCode = passCode;
        this.link = link;
        this.date = date;
    }
}
