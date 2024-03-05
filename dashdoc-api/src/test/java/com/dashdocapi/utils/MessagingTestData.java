package com.dashdocapi.utils;

import com.dashdocapi.entities.SMSTemplate;
import com.dashdocapi.interfaces.enums.MessageType;
import net.datafaker.Faker;

import java.util.Random;

public class MessagingTestData {
    private static Faker faker = new Faker();
    private static Random rand = new Random();

    public static SMSTemplate getSMSTemplate() {
        return new SMSTemplate(
                faker.number().randomNumber(),
                "Test template for <provider> and <client>",
                MessageType.APPOINTMENT.name()
        );
    }
}
