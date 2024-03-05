import React, { useState } from 'react';
import Label from '../components/shared/input/Label';
import { Input } from '../components/shared/input/Input';
import { confirmSignUp } from '@api/auth';
import { useNavigate } from 'react-router-dom';

const EmailVerify = () => {
  const [email, setEmail] = useState<string>('');
  const [otpCode, setOtpCode] = useState<string>('');
  const [error, setError] = useState<string>('');
  const [confirmedUser, setConfirmedUser] = useState(null);
  const navigate = useNavigate();

  const onSubmitHandler = async (e: any) => {
    e.preventDefault();
    try {
      const user = (await confirmSignUp({ email, otpCode })) as any;
      if (user.id) {
        setConfirmedUser(user);
        navigate('/verifySuccess');
      }
      return user;
    } catch (error) {
      setError(error.message);
    }
  };

  return (
    <div>
      <form onSubmit={onSubmitHandler}>
        <div className='error-message'>{error ? error : null}</div>
        <div className='input-container-signup'>
          <Label
            className='signup-input-label'
            htmlFor='confirmEmail'
            label='Email'
          />
          <Input
            name='confirmEmail'
            className='input-email-field'
            data-testid='confirm-email'
            type='email'
            value={email}
            onChange={(e: any) => setEmail(e.target.value)}
            required
          />
        </div>

        <div className='input-container-signup'>
          <Label
            className='signup-input-label'
            htmlFor='confirmPasscode'
            label='One Time Passcode'
          />
          <Input
            name='confirmPasscode'
            className='input-email-field'
            data-testid='confirm-passcode'
            type='text'
            value={otpCode}
            onChange={(e: any) => setOtpCode(e.target.value)}
            required
          />
        </div>

        <button
          className='confirm-signup-button'
          id='confirm-signup-button'
          data-testid='confirm-signup-button'
          type='submit'
        >
          Confirm
        </button>
      </form>
    </div>
  );
};

export default EmailVerify;
