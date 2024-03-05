export interface UserSignInRequest {
  email: string;
  password: string;
}

export interface UserSignUpRequest {
  email: string;
  password: string;
  userType: string;
  firstName: string,
  lastName: string,
  agencyName?: string;
  agencyCode?: string;
}

export interface ConfirmSignUpRequest {
  email: string;
  otpCode: string;
}

export type ValidateUserType = {
  email: String;
  valid: boolean;
};

export enum LoginTheme {
  bright_blue = 'var(--bright-blue)',
  bright_orange = 'var(--orange)',
}
