import {
  UserSignUpRequest,
  UserSignInRequest,
  ConfirmSignUpRequest,
} from '@typings/auth';
import { post, get } from '@utils/http';
import { User } from '@typings/user';
import { ValidateUserType } from '@typings/auth';

export const userSignIn = async (
  userSignInRequest: UserSignInRequest
): Promise<User> => {
  return await post(`/api/auth/signin`, userSignInRequest);
};

export const validateUser = async (): Promise<ValidateUserType> => {
  const { email, valid }: any = await get('/api/auth/validateUser');

  return { email, valid };
};

export const userSignUp = async (userSignUpRequest: UserSignUpRequest) => {
  try {
    const user = await post('/api/auth/signup', userSignUpRequest);
    return user;
  } catch (error) {
    throw error;
  }
};

export const confirmSignUp = async (request: ConfirmSignUpRequest) => {
  try {
    const confirmedUser = await post('/api/auth/confirmUser', request);
    return confirmedUser;
  } catch (error) {
    throw error;
  }
};
