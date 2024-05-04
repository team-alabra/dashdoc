import React from 'react';
import { useSelector } from 'react-redux';
import * as S from '@styles';
import Analytics from '@components/analytics/Analytics';

const UserDashboard = () => {
  const user = useSelector((state: any) => state.user);
  return (
    <div data-testid='dashboard-container'>
      <S.StyledHeader fontSize='25' fontColor='#183888' fontWeight='bold'>
        Welcome, {user.firstName}
      </S.StyledHeader>
      <Analytics userType={user.userType} />
    </div>
  );
};

export default UserDashboard;
