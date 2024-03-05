import React, { useState } from 'react';
import { useLogin } from '@hooks';
import Button from '@shared/buttons/Button';
import { LoginTheme } from '@typings/auth';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const { loginHandler, error } = useLogin();
  const navigate = useNavigate();

  return (
    <div className='form-container'>
      <p className='login-header'>
        <b>Dash into your account today</b>
      </p>

      {error ? (
        <p className='error-message' data-testid='error-message'>
          {error}
        </p>
      ) : null}

      <form
        onSubmit={async (event: any) => {
          event.preventDefault();
          await loginHandler(email, password);
        }}
        className='login-container'
      >
        <input
          className='input-email'
          placeholder='Enter your email'
          data-testid='email-input'
          type='email'
          value={email}
          onChange={(e: any) => setEmail(e.target.value)}
          required
        />
        <input
          className='input-password'
          placeholder='Enter your password'
          data-testid='password-input'
          type='password'
          value={password}
          onChange={(e: any) => setPassword(e.target.value)}
          required
        />

        <Button
          id='login-button'
          color={LoginTheme.bright_blue}
          data-testid='login-button'
          type='submit'
        >
          Log In
        </Button>
      </form>

      <p className='forgot-password-container'>
        <b>I forgot my password</b>
      </p>
      <hr className='line-break'></hr>

      <Button
        id='sign-up-button'
        color={LoginTheme.bright_orange}
        data-testid='sign-up-button'
        type='button'
        onClick={() => {
          navigate('/signup');
        }}
      >
        New to DashDoc? Join Now
      </Button>

    </div>
  );
};

export default LoginForm;
