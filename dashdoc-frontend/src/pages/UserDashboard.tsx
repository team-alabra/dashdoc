import React from 'react';
import { useSelector } from 'react-redux';
import * as S from '@styles';
import Analytics from '@components/analytics/Analytics';

const UserDashboard = () => {
  const user = useSelector((state: any) => state.user);
  return (
    <div data-testid='dashboard-container'>
      <S.StyledHeader
        fontSize='25'
        fontColor='#183888'
        margin='auto'
        fontWeight='bold'
      >
        Welcome, Brandy
      </S.StyledHeader>
      <Analytics />
    </div>
  );
};

export default UserDashboard;
