import { useState } from 'react';
import { userSignUp } from '@api/auth';
import { useNavigate } from 'react-router-dom';
import { UserSignUpRequest } from '@typings/auth';

export const useSignup = () => {
  const navigate = useNavigate();
  const [error, setError] = useState<string>('');
  const [customer, setCustomer] = useState(null);

  const signupHandler = async (userSignUpRequest: UserSignUpRequest) => {
    try {
      const unconfirmedUser = (await userSignUp(userSignUpRequest)) as any;
      if (unconfirmedUser?.id) {
        setCustomer(unconfirmedUser);
        navigate('/emailVerify');
      }
      return unconfirmedUser;
    } catch (error) {
      setError(error.message);
    }
  };

  return {
    signupHandler,
    error,
    setError,
    customer,
    setCustomer,
  };
};
