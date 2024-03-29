package com.dashdocapi.DTO.models;

import com.dashdocapi.interfaces.enums.PlanTerm;
import com.dashdocapi.interfaces.enums.SubscriptionType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchPlanRequest {
    private SubscriptionType planType;
    private PlanTerm planTerm;

    public FetchPlanRequest() {
    }

    public FetchPlanRequest(SubscriptionType planType, PlanTerm planTerm) {
        this.planType = planType;
        this.planTerm = planTerm;
    }
}
