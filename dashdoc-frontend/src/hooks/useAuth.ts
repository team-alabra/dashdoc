import { useState } from 'react';
import { validateUser } from '@api/auth';
import { setAuth } from '@services/slices/authSlice';
import { useDispatch, useSelector } from './hooks';
import { getAuth } from '@services/slices/authSlice';
import { ValidateUserType } from '@typings/auth';

export const useAuth = () => {
  const dispatch = useDispatch();
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const isValid = useSelector(getAuth);

  const isAuthenticated = async (): Promise<ValidateUserType> => {
    let result;
    try {
      result = await validateUser();
      dispatch(setAuth({ isAuthenticated: result.valid }));
      setIsLoading(false);
    } catch (error) {
      console.error(error);
      dispatch(setAuth({ isAuthenticated: false }));
      setIsLoading(false);
    }
    return result;
  };

  return {
    isAuthenticated,
    isValid,
    setIsLoading,
  };
};
