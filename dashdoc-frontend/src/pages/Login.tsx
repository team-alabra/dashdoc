import React from 'react';
import { LOGIN_MAIN_IMAGE } from '@constants';
import LoginForm from '../components/forms/LoginForm';

const Login = () => {
  return (
    <>
      <div className='auth-container' data-testid='auth-container'>
        <LoginForm />
        <img className='image-container' src={LOGIN_MAIN_IMAGE} />
      </div>
    </>
  );
};

export default Login;
