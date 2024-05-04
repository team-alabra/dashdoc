import { useState } from 'react';
import { validateUser } from '@api/auth';
import { setAuth } from '@services/slices/authSlice';
import { useDispatch, useSelector } from './hooks';
import { ValidateUserType } from '@typings/auth';
import { getAuth } from '@services/slices/authSlice';

export const useAuth = () => {
  const dispatch = useDispatch();
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const isValid = useSelector(getAuth);

  console.log('from state', isValid);

  const isAuthenticated = async (): Promise<ValidateUserType> => {
    try {
      const result = await validateUser();
      dispatch(setAuth({ isAuthenticated: result.valid }));
      setIsLoading(false);
      return result;
    } catch (error) {
      console.error(error);
      dispatch(setAuth({ isAuthenticated: false }));
      setIsLoading(false);
    }
  };

  return {
    isAuthenticated,
    isValid,
    setIsLoading,
  };
};
