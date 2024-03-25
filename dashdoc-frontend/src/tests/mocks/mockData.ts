import { AgeGroup, Client, Gender } from "@typings/client";
import { Plan, PlanTerm, PlanTypes } from "@typings/plan";
import { UserType } from "@typings/user";

export const mockUser = {
  email: 'test@email.com',
  password: 'Dashdoc123!',
};

export const mockUserResponse = {
  id: 1,
  email: 'test@email.com',
  npi: '2223334445',
  providerDetail: {
    id: 1,
    firstName: 'Jane',
    lastName: 'Doe',
    dob: JSON.stringify(new Date('2023-05-10T17:30:33.362+00:00')),
    phoneNumber: '2122222232',
    streetAddress: '200 Broadway',
    city: 'New York',
    state: 'NY',
    zipCode: '10013',
    gender: 'FEMALE',
    discipline: 'ST',
  },
  subscription: {
    id: 34,
    planID: 1,
    createdOn: JSON.stringify(new Date('2023-07-19T17:30:33.362+00:00')),
    lastUpdated: JSON.stringify(new Date('2023-07-19T17:30:33.362+00:00')),
    numberOfUsers: 1,
    stripeCustomerID: 'cus_OIEg1D9MoImgVB',
    status: 'ACTIVE',
    stripeSubscriptionID: '30a',
  },
  stripeCustomerID: '30a',
  subscriptionPlanId: 1,
  userType: 'SOLE_PROVIDER',
};

export const mockPlansResponse: PlanTypes = {
  soleProviderPlans: {
    monthly: { 
      id: 1,
      price: 30,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.MONTHLY,
      userType: UserType.SOLE_PROVIDER,
      priceCode: "001xyz"
    } as Plan,
    yearly: { 
      id: 2,
      price: 250,
      pricePerAdditionalUser: 0,
      planTerm: PlanTerm.ANNUAL,
      userType: UserType.SOLE_PROVIDER,
      priceCode: "002xyz"
    } as Plan
  },
  smallAgencyPlans: {
    monthly: { 
      id: 3,
      price: 39,
      pricePerAdditionalUser: 25,
      planTerm: PlanTerm.MONTHLY,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: "003xyz"
    } as Plan,
    yearly: { 
      id: 4,
      price: 360,
      pricePerAdditionalUser: 25,
      planTerm: PlanTerm.ANNUAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: "004xyz"
    } as Plan
  },
  mediumAgencyPlans: {
    monthly: { 
      id: 5,
      price: 49,
      pricePerAdditionalUser: 30,
      planTerm: PlanTerm.MONTHLY,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: "005xyz"
    } as Plan,
    yearly: { 
      id: 6,
      price: 480,
      pricePerAdditionalUser: 30,
      planTerm: PlanTerm.ANNUAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: "006xyz"
    } as Plan
  },
  largeAgencyPlans: {
    monthly: { 
      id: 7,
      price: 59,
      pricePerAdditionalUser: 28,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: "007xyz"
    } as Plan,
    yearly: { 
      id: 8,
      price: 540,
      pricePerAdditionalUser: 28,
      planTerm: PlanTerm.FREE_TRIAL,
      userType: UserType.AGENCY_ADMINISTRATOR,
      priceCode: "008xyz"
    } as Plan
  }
};

export const mockClientResponse: Client = {
  id: 1,
  firstName: 'Testy',
  lastName: 'Testerson',
  ageGroup: AgeGroup.SCHOOL_AGED,
  age: 10,
  dob: new Date(2014, 1, 1),
  phoneNUmber: '(777)555-9999',
  email: "testy.testerson@gmail.com",
  gender: Gender.FEMALE,
  address: '1 Hacker Way',
  city: 'Hackerville',
  state: 'CA',
  zipCode: '55555',
  preferredLanguage: 'English'
};

export const mockClientsResponse: Client[] = [
  {...mockClientResponse},
  {...mockClientResponse, id: 2},
  {...mockClientResponse, id: 3},
  {...mockClientResponse, id: 4},
  {...mockClientResponse, id: 5},
  {...mockClientResponse, id: 6},
  {...mockClientResponse, id: 7},
  {...mockClientResponse, id: 8},
  {...mockClientResponse, id: 9},
  {...mockClientResponse, id: 10},
]