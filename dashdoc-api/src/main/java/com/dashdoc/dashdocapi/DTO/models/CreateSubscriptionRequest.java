package com.dashdocnow.DTO.models;

import com.dashdocnow.interfaces.enums.PlanTerm;
import com.dashdocnow.interfaces.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSubscriptionRequest {
    private SubscriptionType subscriptionType = SubscriptionType.INDIVIDUAL;
    private PlanTerm term = PlanTerm.MONTHLY;
}
