package com.dashdocapi.utils;

import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.entities.*;
import com.dashdocapi.interfaces.enums.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.Customer;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import net.datafaker.Faker;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;

public class TestData {
    private static Faker faker = new Faker();
    private static Random rand = new Random();
    private static Gender[] genders = Gender.values();


    public static Provider getProviderDBO(){
        return new Provider(
                faker.number().randomNumber(),
                faker.internet().emailAddress(),
                UserType.SOLE_PROVIDER,
                null,
                getSoleProviderSubscriptionDBO()
        );
    }

    public static List<Provider> getAgencyProvidersDBO() {
        var providers = new ArrayList<Provider>();
        var agency = getAgencyDBO();

        for(int i=0; i < 3; i++) {
            providers.add( new Provider(
                faker.number().randomNumber(),
                faker.internet().emailAddress(),
                UserType.AGENCY_PROVIDER,
                agency,
                getAgencySubscriptionDBO()
            ));
        }

        return providers;
    }
    public static Provider getAgencyAdminDBO(){
        return new Provider(
                faker.number().randomNumber(),
                faker.internet().emailAddress(),
                UserType.AGENCY_ADMINISTRATOR,
                getAgencyDBO(),
                getAgencySubscriptionDBO()
        );
    }
    public static Agency getAgencyDBO(){
        return new Agency(
                faker.number().randomNumber(),
                "Care Agency LLC",
                "admin@careagency.com",
                "7185552525",
                "1 Care Blvd",
                "Brooklyn",
                State.NY,
                "12100"
        );
    }


    public static Document getDocumentDBO(){
        return new Document(
                faker.number().randomNumber(),
                DocumentType.THERAPY_NOTES,
                "SOAP_NOTE",
                AgeGroup.ALL_AGE_GROUPS,
                getProviderDBO(),
                getClientDBO(),
                Calendar.getInstance(),
                Calendar.getInstance(),
                "{ \"id\" : \n" +
                        "      {\n" +
                        "         \"firstName\": \"Bugs\",\n" +
                        "         \"lastName\" : \"Bunny\"\n" +
                        "      }\n" +
                        "}"
        );
    }

    public static Subscription getSoleProviderSubscriptionDBO(){
        return new Subscription(
                faker.number().randomNumber(),
                Calendar.getInstance(),
                Calendar.getInstance(),
                1,
                SubscriptionStatus.ACTIVE,
                "xyzabc123000",
                "sub_hello123",
                "xyz_jjj",
                SubscriptionType.INDIVIDUAL);
    }

    public static Subscription getAgencySubscriptionDBO(){
        return new Subscription(
                faker.number().randomNumber(),
                Calendar.getInstance(),
                Calendar.getInstance(),
                3,
                SubscriptionStatus.ACTIVE,
                "xyzabc123000",
                "sub_hello123",
                "plan_xyzjjj",
                SubscriptionType.AGENCY);
    }

    public static Client getClientDBO(){
        Grade[] grades = Grade.values();
        AgeGroup[] ageGroup = AgeGroup.values();
        var dob = Calendar.getInstance();
        dob.set(2000, 10, 10);

        return new Client(
                faker.number().randomNumber(),
                faker.name().firstName(),
                faker.name().lastName(),
                ageGroup[rand.nextInt(ageGroup.length)],
                dob,
                faker.internet().emailAddress(),
                faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().phoneNumber(),
                faker.phoneNumber().phoneNumber(),
                genders[rand.nextInt(genders.length)],
                faker.address().streetAddress(),
                faker.address().city(),
                State.NY,
                faker.address().zipCode(),
                faker.address().country(),
                grades[rand.nextInt(grades.length)],
                HomeLanguage.English,
                getProviderDBO(),
                getAgencyDBO(),
                Calendar.getInstance()
        );
    }

    public static List<ProviderDTO> getListOfProviders(int num){
        List<ProviderDTO> providers = new ArrayList<>();

        for (int i = 0; i < num; i++){
            providers.add(getProviderDBO().asDTO());
        }

        return providers;
    }

    public static List<Client> getListOfClients(int num){
        List<Client> clients = new ArrayList<>();
        Provider sampleProvider = getProviderDBO();

        for (int i = 0; i < num; i++){
            // Manually setting provider so all clients can have the same provider
            Client client = getClientDBO();
            client.setProvider(sampleProvider);
            clients.add(client);
        }

        return clients;
    }

    public static String getTestJwt() {
        // Fake token data
        final String ISSUER = "TestIssuer";
        final String SUB = "sub";
        final String AUTH0ID = "sms|12345678";
        byte[] keyBytes = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=".getBytes(StandardCharsets.UTF_8);
        var key = Keys.hmacShaKeyFor(keyBytes);

        // This is a fake signed jwt (jws) used for testing
        String jws = Jwts.builder()
                .setIssuer(ISSUER)
                .setSubject(SUB)
                .claim(SUB, AUTH0ID)
                .claim("username", "jjjiii888-ollllll")
                // Fri Jun 24 2016 15:33:42 GMT-0400 (EDT)
                .setIssuedAt(Date.from(Instant.now()))
                // Sat Jun 24 2116 15:33:42 GMT-0400 (EDT)
                .setExpiration(Date.from(Instant.now().plusSeconds(30)))
                .signWith(
                        key,
                        SignatureAlgorithm.HS256
                )
                .compact();

        return jws;
    }

    public static Customer getStripeCustomer(){
        var customer = new Customer();
        customer.setId("ppp");
        customer.setName("Testy Testerson");
        customer.setEmail("testerson@gmail.com");
        customer.setMetadata(Map.of("Agency", "N/A"));

        return customer;
    }

    public static Customer getStripeAgencyCustomer(){
        var customer = new Customer();
        customer.setId("ppp");
        customer.setName("Testy Testerson");
        customer.setEmail("testerson@gmail.com");
        customer.setMetadata(Map.of("Agency", "Brooklyn Agency LLC"));

        return customer;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
