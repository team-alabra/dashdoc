export interface UserSignInRequest {
  email: string,
  password: string
}

export interface UserSignUpRequest {
  fullName: string,
  email: string,
  password: string
}

export type ValidateUserType = {
  email: String,
  valid: boolean
}

export enum LoginTheme {
  bright_blue = 'var(--bright-blue)',
  bright_orange = 'var(--orange)',
}
