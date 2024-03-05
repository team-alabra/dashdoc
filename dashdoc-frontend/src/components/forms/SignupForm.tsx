import React, { useState } from 'react';
import { useSignup } from '@hooks/useSignup';
import { useNavigate } from 'react-router-dom';
import { Input } from '../shared/input/Input';
import { Label } from '../shared/input/Label';
import { UserSignUpRequest } from '@typings/auth';
import { RadioGroup, Radio, FormControlLabel } from '@mui/material';
import { setHTMLFor, setLabel } from '@utils/helpers/helpers';

const SignupForm = () => {
  const navigate = useNavigate();
  const [selectedUserType, setSelectedUserType] =
    useState<string>('SOLE_PROVIDER');
  const [isAgency, setIsAgency] = useState<boolean>(false);
  const { signupHandler, error } = useSignup();
  const INITIAL_STATE = {
    email: '',
    password: '',
    userType: selectedUserType,
    firstName: '',
    lastName: '',
    agencyCode: '',
    agencyName: '',
  };
  const [userSignUpRequest, setUserSignUpRequest] =
    useState<UserSignUpRequest>(INITIAL_STATE);

  const handleChange = (event: any) => {
    const { name, value } = event.target;
    if (name == 'userType') {
      setSelectedUserType(value);
      setIsAgency(value !== 'SOLE_PROVIDER');
    }
    setUserSignUpRequest((request) => ({
      ...request,
      [name]: value,
    }));
  };
  const submitHandler = async (e: any) => {
    e.preventDefault();
    await signupHandler(userSignUpRequest);
    setUserSignUpRequest(INITIAL_STATE);
  };

  return (
    <form className='signup-form-container' onSubmit={submitHandler}>
      <div className='usertype-container' data-testid='usertype-container'>
        <div className='signup-header-text'>
          <div>Create your DashDoc account today</div>
        </div>

        {userSignUpRequest.userType === 'SOLE_PROVIDER' ? (
          <div className='i-am-text'>I am a... </div>
        ) : (
          <div className='i-am-text'>I am an... </div>
        )}

        {error ? (
          <p className='error-message' data-testid='error-message'>
            {error}
          </p>
        ) : null}

        <RadioGroup
          row
          aria-labelledby='demo-radio-buttons-group-label'
          defaultValue='SOLE_PROVIDER'
          name='userType'
          className='radio-input-container'
        >
          <FormControlLabel
            value='SOLE_PROVIDER'
            control={<Radio />}
            label='Sole Provider'
            onChange={handleChange}
          />
          <FormControlLabel
            value='AGENCY_PROVIDER'
            control={<Radio />}
            label='Agency Provider'
            onChange={handleChange}
          />
          <FormControlLabel
            value='AGENCY_ADMINISTRATOR'
            control={<Radio />}
            label='Agency Administrator'
            onChange={handleChange}
          />
        </RadioGroup>

        {isAgency && (
          <div className='input-container-signup'>
            <Label
              className='signup-input-label'
              htmlFor={setHTMLFor(selectedUserType)}
              label={setLabel(setHTMLFor(selectedUserType))}
            />
            <Input
              name={setHTMLFor(selectedUserType)}
              id={setHTMLFor(selectedUserType)}
              className='user-input-field'
              type='text'
              value={
                selectedUserType === 'AGENCY_PROVIDER'
                  ? userSignUpRequest.agencyCode
                  : userSignUpRequest.agencyName
              }
              onChange={handleChange}
              required
            />
          </div>
        )}

        <div className='input-container-signup'>
          <Label
            className='signup-input-label'
            htmlFor='fullName'
            label='Full Name'
          />
          <div className='fullname-container'>
            <Input
              name='firstName'
              className='firstName'
              data-testid='first-name'
              type='text'
              value={userSignUpRequest.firstName}
              onChange={handleChange}
              required
              placeholder='First Name'
            />
            <Input
              name='lastName'
              className='lastName'
              data-testid='last-name'
              type='text'
              value={userSignUpRequest.lastName}
              onChange={handleChange}
              required
              placeholder='Last Name'
            />
          </div>
        </div>

        <div className='input-container-signup'>
          <Label className='signup-input-label' htmlFor='email' label='Email' />
          <Input
            name='email'
            className='user-input-field'
            data-testid='email-input'
            type='email'
            value={userSignUpRequest.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className='input-container-signup'>
          <Label
            className='signup-input-label'
            htmlFor='password'
            label='Password'
          />
          <Input
            name='password'
            className='user-input-field'
            data-testid='password-input'
            type='password'
            value={userSignUpRequest.password}
            onChange={handleChange}
            required
          />
        </div>

        <button
          className='signup-continue-button'
          id='signup-continue-button'
          data-testid='signup-continue-button'
          type='submit'
        >
          Continue
        </button>
        <div className='have-account-container'>
          <p>Have an account?</p>
          <button className='signin-text' onClick={() => navigate('/login')}>
            {' '}
            <span className='sign-in-text'>Sign In</span>
          </button>
        </div>
      </div>
    </form>
  );
};

export default SignupForm;
