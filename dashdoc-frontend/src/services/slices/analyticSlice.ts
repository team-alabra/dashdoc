import { createSlice, Slice } from '@reduxjs/toolkit';
import { setAnalyticsAction } from '@services/actions/analytics';
import { RootState } from '@services/store';

export const analyticSlice: Slice = createSlice({
  name: 'analytics',
  initialState: {
    earnings: 0,
    numOfNotes: { submitted: 0, incomplete: 0 },
    numOfAppointments: { attended: 0, noShow: 0, cancelled: 0 },
    lastUpdated: new Date(),
  },
  reducers: {
    setAnalytics: setAnalyticsAction,
  },
});

export const { setAnalytics } = analyticSlice.actions;
export const getAnalytics = (state: RootState) => state.analytics;

export default analyticSlice.reducer;
