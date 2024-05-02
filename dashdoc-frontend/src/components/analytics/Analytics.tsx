import React from 'react';
import * as S from '@styles';
import Metric from '@components/analytics/Metric';
import { formatCurrency } from '@utils/formatter';
import { mockUserAnalytics } from '@utils/mocks/analyticsMocks';
import { useAnalytics } from '@hooks/useAnalytics';

const Analytics = ({ userType }: any) => {
  // this will be used once server is hooked up and will replace mockUserAnalytics
  const { analytics } = useAnalytics();
  return (
    <div>
      <S.StyledHeader fontSize='18' fontColor='black' fontWeight='bold'>
        {new Date().toLocaleString('default', { month: 'long' })} Analytics
      </S.StyledHeader>

      <div className='analytics-container'>
        <Metric
          data-testid='earnings-metric'
          title={'Earnings'}
          data={formatCurrency(mockUserAnalytics.earnings)}
        />

        <Metric
          data-testid='completed-notes-metric'
          title={
            userType === 'AGENCY_ADMINISTRATOR'
              ? 'New Clients'
              : 'Completed Notes'
          }
          data={mockUserAnalytics.numOfNotes.submitted}
        />

        <Metric
          data-testid='appointments-metric'
          title={
            userType === 'AGENCY_ADMINISTRATOR'
              ? 'Submitted Provider Notes'
              : 'Appointments'
          }
          data={mockUserAnalytics.numOfAppointments}
        />
      </div>
    </div>
  );
};

export default Analytics;
