export interface ProviderDetail {
  id?: number;
  firstName: string;
  lastName: string;
  gender: string;
  streetAddress: string;
  city: string;
  zipCode: string;
  phoneNumber: string;
  dob: String;
  discipline: string;
}
export interface User {
  id: number;
  email: string;
  npi: string;
  userType: string;
  providerDetail: ProviderDetail;
  agencyID?: number;
  subscriptionPlanID: number;
  stripeCustomerID: number;
}

export enum UserType {
  SOLE_PROVIDER,
  AGENCY_PROVIDER,
  AGENCY_ADMINISTRATOR
}
