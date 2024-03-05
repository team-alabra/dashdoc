import { useState } from 'react';
import { validateUser } from '@api/auth';
import { setAuth } from '@services/slices/authSlice';
import { useDispatch } from './hooks';
import { ValidateUserType } from '@typings/auth';

export const useAuth = () => {
  const dispatch = useDispatch();
  const [isValidUser, setIsValidUser] = useState<boolean | null>(null);
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const isAuthenticated = async (): Promise<ValidateUserType> => {
    try {
      const result = await validateUser();
      const { valid: isValid } = result;
      setIsValidUser(isValid);
      dispatch(setAuth({ isAuthenticated: isValid }));
      setIsLoading(false);
      return result;
    } catch (error) {
      console.error(error);
      setIsValidUser(false);
      dispatch(setAuth({ isAuthenticated: false }));
      setIsLoading(false);
    }
  };

  return {
    isAuthenticated,
    isValidUser,
    setIsLoading,
  };
};
