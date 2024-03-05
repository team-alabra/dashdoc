package com.dashdocnow.entities;

import com.dashdocnow.DTO.SubscriptionDTO;
import com.dashdocnow.interfaces.enums.SubscriptionStatus;
import com.dashdocnow.interfaces.enums.SubscriptionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Entity
@Table(name = "subscription")
@Getter
@Setter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "created_on")
    private Calendar createdOn = Calendar.getInstance();
    @Column(name = "last_updated")
    private Calendar lastUpdated = Calendar.getInstance();
    @Column(name = "number_of_users")
    private Integer numberOfUsers = 1;
    @Column(name = "stripe_customer_id")
    private String stripeCustomerID;
    @Column(name = "stripe_subscription_id")
    private String stripeSubscriptionID;
    @Column(name = "stripe_plan_id")
    private String stripePlanID;
    @Enumerated(EnumType.STRING)
    private SubscriptionStatus status = SubscriptionStatus.INCOMPLETE;
    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_type")
    private SubscriptionType subscriptionType;

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = Calendar.getInstance();
    }

    public Subscription() { }

    public Subscription(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Subscription(Long id, Calendar createdOn, Calendar lastUpdated, Integer numberOfUsers, SubscriptionStatus status, String stripeCustomerID, String stripeSubscriptionID, String stripePlanId, SubscriptionType subscriptionType) {
        this.id = id;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.numberOfUsers = numberOfUsers;
        this.status = status;
        this.stripeCustomerID = stripeCustomerID;
        this.stripeSubscriptionID = stripeSubscriptionID;
        this.stripePlanID = stripePlanId;
        this.subscriptionType = subscriptionType;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", lastUpdated=" + lastUpdated +
                ", numberOfUsers=" + numberOfUsers +
                ", stripeCustomerID='" + stripeCustomerID + '\'' +
                ", stripeSubscriptionID='" + stripeSubscriptionID + '\'' +
                ", status=" + status +
                ", subscriptionType=" + subscriptionType +
                '}';
    }

    public SubscriptionDTO asDTO() {
        return new SubscriptionDTO(
                getId(),
                getCreatedOn(),
                getLastUpdated(),
                getNumberOfUsers(),
                getStatus(),
                getStripeCustomerID(),
                getStripeSubscriptionID(),
                getSubscriptionType(),
                getStripePlanID()
        );
    }
}


