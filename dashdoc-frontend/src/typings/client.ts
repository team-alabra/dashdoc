export type Client = {
  id: number;
  firstName: string | null;
  lastName: string | null;
  ageGroup: AgeGroup;
  age: number;
  dob: Date | null;
  phoneNUmber: string | null;
  email: string | null;
  gender: Gender;
  address: string | null;
  city: string | null;
  state: string | null;
  zipCode: string | null;
  preferredLanguage: string | null;
};

export enum AgeGroup {
  EARLY_INTERVENTION,
  PRESCHOOL,
  SCHOOL_AGED,
  ADOLESCENT,
  ADULT,
  ALL_AGE_GROUPS,
}

export enum Gender {
  MALE, FEMALE, UNSPECIFIED
}