import React from 'react';
import DoneOutlineIcon from '@mui/icons-material/DoneOutline';

const Success = ({}) => {
  return (
    <div className='success-confirm-container'>
      <DoneOutlineIcon />

      <h2>You have successfully confirmed your account. Sign into your account here.</h2>
    </div>
  );
};

export default Success;
