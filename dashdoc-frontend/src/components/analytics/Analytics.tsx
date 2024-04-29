import React from 'react';
import * as S from '@styles';
import Metric from '@components/analytics/Metric';
import { formatCurrency } from '@utils/formatter';
import { mockUserAnalytics } from '@utils/mocks/analyticsMocks';
import { monthNames } from '@constants';
import { useAnalytics } from '@hooks/useAnalytics';

const Analytics = ({ userType }: any) => {
  // this will be used once server is hooked up and will replace mockUserAnalytics
  const { user_analytics } = useAnalytics();
  return (
    <div>
      <S.StyledHeader fontSize='18' fontColor='black' fontWeight='bold'>
        {monthNames[mockUserAnalytics.lastUpdated.getMonth()]} Analytics
      </S.StyledHeader>

      <div className='analytics-container'>
        <Metric
          title={'Earnings'}
          data={formatCurrency(mockUserAnalytics.earnings)}
        />

        <Metric
          title={
            userType === 'AGENCY_ADMINISTRATOR'
              ? 'New Clients'
              : 'Completed Notes'
          }
          data={mockUserAnalytics.num_of_notes.submitted}
        />

        <Metric
          title={
            userType === 'AGENCY_ADMINISTRATOR'
              ? 'Submitted Notes'
              : 'Appointments'
          }
          data={mockUserAnalytics.num_of_appointments.attended}
        />
      </div>
    </div>
  );
};

export default Analytics;
