package com.dashdocapi.DTO.models;

import com.dashdocapi.interfaces.enums.PlanTerm;
import com.dashdocapi.interfaces.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubscriptionRequest {
    private SubscriptionType subscriptionType = SubscriptionType.INDIVIDUAL;
    private PlanTerm term = PlanTerm.MONTHLY;
}
