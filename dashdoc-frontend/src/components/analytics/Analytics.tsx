import React from 'react';
import * as S from '@styles';
import Metric from '@components/analytics/Metric';
import { formatCurrency } from '@utils/formatter';
import { mockUserAnalytics } from '@utils/mocks/analyticsMocks';

const Analytics = () => {
  return (
    <div>
      <S.StyledHeader fontSize='18' fontColor='black' fontWeight='bold'>
        April Analytics
      </S.StyledHeader>

      <div className='analytics-container'>
        <Metric
          title={'Earnings'}
          data={formatCurrency(mockUserAnalytics.earnings)}
        />

        <Metric
          title={'Completed Notes'}
          data={mockUserAnalytics.num_of_notes.submitted}
        />

        <Metric
          title={'Appointments'}
          data={mockUserAnalytics.num_of_appointments.attended}
        />
      </div>
    </div>
  );
};

export default Analytics;
