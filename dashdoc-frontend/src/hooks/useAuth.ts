import { useState } from 'react';
import { validateUser } from '@api/auth';
import { setAuth } from '@services/slices/authSlice';
import { useDispatch, useSelector } from './hooks';
import { getAuth } from '@services/slices/authSlice';
import { ValidateUserType } from '@typings/auth';

export const useAuth = () => {
  const dispatch = useDispatch();
  const [isLoading, setIsLoading] = useState<boolean>(false);
  const { isAuthenticated } = useSelector(getAuth);

  const authenticateUser = async (): Promise<ValidateUserType> => {
    try {
      let result = await validateUser();
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
    authenticateUser,
    isAuthenticated,
    setIsLoading,
  };
};
