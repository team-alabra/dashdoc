import { useNavigate } from 'react-router-dom';
import { useDispatch } from './hooks';
import { userSignIn } from '@api/auth';
import { saveUser } from '@services/slices/userSlice';
import { setAuth } from '@services/slices/authSlice';
import { useState } from 'react';

export const useLogin = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [error, setError] = useState<string>('');

  const loginHandler = async (email: string, password: string) => {
    try {
      const authenticatedUser = await userSignIn({ email, password });
      dispatch(saveUser(authenticatedUser));
      dispatch(setAuth({ isAuthenticated: true }));
      navigate('/');
    } catch (error) {
      console.error(error);
      setError('Oops! Invalid email or password. Please try again!');
      dispatch(setAuth({ isAuthenticated: false }));
    }
  };

  return {
    loginHandler,
    error,
    setError,
  };
};
