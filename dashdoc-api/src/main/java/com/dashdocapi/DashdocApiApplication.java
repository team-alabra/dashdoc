package com.dashdocapi;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import com.twilio.Twilio;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DashdocApiApplication {
    @Value("${aws.accessKeyId}")
    private String accessKey;
    @Value("${aws.secretAccessKey}")
    private String secretKey;
    @Value("${aws.region}")
    private String region;
    @Value("${twilio.account-sid}")
    private String twilioAccountSID;
    @Value("${twilio.auth-token}")
    private String twilioAuthToken;
    @Value("${google.auth.email}")
    private String googleUsername;
    @Value("${google.auth.password}")
    private String googlePassword;
    @Bean
    public void twilioInit(){
        Twilio.init(twilioAccountSID, twilioAuthToken);
    }
    @Bean
    // connect to access AWS resources in general with unique access key and secret
    // key.
    public AWSCognitoIdentityProvider cognitoClient() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials)).withRegion(region)
                .build();
    }

    @Bean
    public AmazonS3 getAmazonS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider((awsCredentials)))
                .withRegion(region)
                .build();
    }

    @Bean
    public DozerBeanMapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(25);

        mailSender.setUsername(googleUsername);
        mailSender.setPassword(googlePassword);

        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");

        return mailSender;
    }
    public static void main(String[] args) {
        SpringApplication.run(DashdocApiApplication.class, args);
    }

}
