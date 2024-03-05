import { createSlice, Slice } from '@reduxjs/toolkit';
import { savePlanAction } from '@services/actions/plan';
import { RootState } from '../store';
import { PlanTypes, Plan, PlanTerm } from '@typings/plan';
import { UserType } from '@typings/user';

export const initialState: PlanTypes = {
  soleProviderPlans: {
    monthly: {
      id: 0,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.SOLE_PROVIDER,
      priceCode: ""
    } as Plan,
    yearly: {
      id: 1,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.SOLE_PROVIDER,
      priceCode: ""
    } as Plan
  },
  smallAgencyPlans: {
    monthly: {
      id: 2,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: ""
    } as Plan,
    yearly: {
      id: 3,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: ""
    } as Plan
  },
  mediumAgencyPlans: {
    monthly: {
      id: 4,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: ""
    } as Plan,
    yearly: {
      id: 5,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: ""
    } as Plan
  },
  largeAgencyPlans: {
    monthly: {
      id: 6,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: ""
    } as Plan,
    yearly: {
      id: 7,
      price: 0,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: ""
    } as Plan
  }
};

export const planSlice: Slice<PlanTypes> = createSlice({
  name: 'plan',
  initialState,
  reducers: {
    savePlan: savePlanAction,
  },
});

export const { savePlan } = planSlice.actions;
export const getPlans = (state: RootState) => state.plan;

export default planSlice.reducer;
