package com.dashdocnow.DTO;

import com.dashdocnow.interfaces.enums.SubscriptionStatus;
import com.dashdocnow.interfaces.enums.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
public class SubscriptionDTO {
    private Long id;
    private Calendar createdOn = Calendar.getInstance();
    private Calendar lastUpdated = Calendar.getInstance();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer numberOfUsers;
    @NotNull
    private String stripeCustomerID;
    private SubscriptionStatus status;
    private String stripeSubscriptionID;
    private String stripePlanID;
    private SubscriptionType subscriptionType;

    public SubscriptionDTO() {
        this.stripeCustomerID = "";
        this.status = SubscriptionStatus.INCOMPLETE;
    }

    public SubscriptionDTO(Long id, Calendar createdOn, Calendar lastUpdated, Integer numberOfUsers, SubscriptionStatus status, String stripeCustomerID, String stripeSubscriptionID, SubscriptionType subscriptionType, String stripePlanID) {
        this.id = id;
        this.createdOn = createdOn;
        this.lastUpdated = lastUpdated;
        this.numberOfUsers = numberOfUsers;
        this.status = status;
        this.stripeCustomerID = stripeCustomerID ;
        this.stripeSubscriptionID = stripeSubscriptionID;
        this.subscriptionType = subscriptionType;
        this.stripePlanID = stripePlanID;
    }

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "id=" + id +
                ", createdOn=" + createdOn +
                ", lastUpdated=" + lastUpdated +
                ", numberOfUsers=" + numberOfUsers +
                ", stripeCustomerID='" + stripeCustomerID + '\'' +
                ", status=" + status +
                ", stripeSubscriptionID='" + stripeSubscriptionID + '\'' +
                ", stripePlanID='" + stripePlanID + '\'' +
                ", subscriptionType=" + subscriptionType +
                '}';
    }
}
