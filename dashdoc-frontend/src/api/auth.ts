import { UserSignUpRequest, UserSignInRequest } from '@typings/auth';
import { post, get } from '@utils/http';
import { User } from '@typings/user';
import { ValidateUserType } from '@typings/auth';

// with credentials -- this needs to be set, or the cookie would not be saved in your application
export const userSignIn = async (
  userSignInRequest: UserSignInRequest
): Promise<User> => {
  return await post(`/api/auth/signin`, userSignInRequest);
};

export const validateUser = async (): Promise<ValidateUserType> => {
  return await get('/api/auth/validateUser');
};

// export const validateGoogleUser = async (idToken : any): Promise<boolean> => {
//   return await post('/api/auth/validateGoogleUser', idToken);
// };

export const userSignUp = async (
  userSignInRequest: UserSignUpRequest
): Promise<User> => {
  return await post(`/api/auth/signup`, userSignInRequest);
};
