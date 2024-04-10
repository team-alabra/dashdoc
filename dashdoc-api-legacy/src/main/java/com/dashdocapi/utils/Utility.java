package com.dashdocapi.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Utility {
    public static String getAge(Calendar dob){
        Period p = Period.between(dob.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate(), LocalDate.now());

        String age = p.getYears() + " years " + p.getMonths() + " months " + p.getDays() + " days";
        return age;
    }
}
