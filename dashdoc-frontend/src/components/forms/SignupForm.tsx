import React, { useState } from 'react';

const SignupForm = () => {
  const [fullName, setFullName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [selectedUserType, setSelectedUserType] = useState('SOLE_PROVIDER');

  const handleChange = (e: any) => {
    setSelectedUserType(e.target.value);
  };

  const onSubmit = () => {
    console.log('in submit handler');
  }

  return (
    <form className='signup-form-container'>
      <div className='signup-userinfo-container'>
        <div className='signup-header-text'>
          <div>Create your DashDoc account today.</div>
        </div>

        <div className='user-type-container'>
          <div>I am a...</div>
          <div className='radio-input-container'>
            <label>
              <input
                className='radio-button-1'
                type='radio'
                name='radio'
                id='soleProvider'
                value='SOLE_PROVIDER'
                checked={selectedUserType === 'SOLE_PROVIDER'}
                onChange={handleChange}
              />
              <span>Sole Provider</span>
            </label>
            <label>
              <input
                className='radio-button-2'
                type='radio'
                name='radio'
                id='agencyProvider'
                value='AGENCY_PROVIDER'
                checked={selectedUserType === 'AGENCY_PROVIDER'}
                onChange={handleChange}
              />
              <span>Agency Provider</span>
            </label>
            <label>
              <input
                className='radio-button-3'
                type='radio'
                name='radio'
                id='agencyAdmin'
                value='AGENCY_ADMIN'
                checked={selectedUserType === 'AGENCY_ADMIN'}
                onChange={handleChange}
              />
              <span>Agency Admin</span>
            </label>
          </div>
        </div>

        <div className='input-container'>
          <div className='fullname-text'>
            {selectedUserType === 'AGENCY_PROVIDER' || selectedUserType === "AGENCY_ADMIN"
              ? 'Organization or Agency Name'
              : 'Full Name'}
          </div>
        </div>
        <input
          className='input-fullname'
          data-testid='full-name-input'
          type='text'
          value={fullName}
          onChange={(e: any) => setFullName(e.target.value)}
          required
        />

        <div className='input-container'>
          <div className='email-text'>Email</div>
        </div>
        <input
          className='input-email-signup'
          data-testid='email-input'
          type='text'
          value={email}
          onChange={(e: any) => setEmail(e.target.value)}
          required
        />

        <div className='input-container'>
          <div className='password-text'>Password</div>
        </div>
        <input
          className='input-password-signup'
          data-testid='password-input'
          type='text'
          value={password}
          onChange={(e: any) => setPassword(e.target.value)}
          required
        />

        <button
          className='signup-continue-button'
          id='signup-continue-button'
          data-testid='signup-continue-button'
          type='submit'
        >
          Continue
        </button>
      </div>
    </form>
  );
};

export default SignupForm;
