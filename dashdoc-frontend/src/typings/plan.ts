import { UserType } from "./user";

export type Plan = {
  id: number;
  price: number;
  pricePerAdditionalUser?: number,
  planTerm: PlanTerm;
  userType?: UserType;
  lastUpdated?: Date;
  priceCode: string;
  subscriptions?: any[];
};

export type PlanTypes = {
  soleProviderPlans: PlanTermDetail;
  smallAgencyPlans: PlanTermDetail;
  mediumAgencyPlans: PlanTermDetail;
  largeAgencyPlans: PlanTermDetail;
}

export type PlanTermDetail = {
  monthly: Plan;
  yearly: Plan
}

export enum PlanTerm {
  MONTHLY,
  ANNUAL,
  SEMI_ANNUAL,
  FREE_TRIAL
}