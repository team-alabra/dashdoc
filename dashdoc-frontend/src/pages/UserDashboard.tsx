import React, { useEffect } from 'react';
import { useSelector } from 'react-redux';
import * as S from '@styles';
import Analytics from '@components/analytics/Analytics';
import {
  MOCK_SOLE_PROVIDER_REQUEST,
} from '@utils/mocks/signupMocks';
import axios from 'axios';

const UserDashboard = () => {
  const user = useSelector((state: any) => state.user);

  async function getData(){
    const data = await axios.get("/api/test");

    console.log("Data --->", data);
  }

  useEffect(() => {
    getData()
  }, []);

  return (
    <div data-testid='dashboard-container'>
      <S.StyledHeader fontSize='25' fontColor='#183888' fontWeight='bold'>
        Welcome, {MOCK_SOLE_PROVIDER_REQUEST.firstName}
      </S.StyledHeader>
      <Analytics userType={MOCK_SOLE_PROVIDER_REQUEST.userType} />
    </div>
  );
};

export default UserDashboard;
