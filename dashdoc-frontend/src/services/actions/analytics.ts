import { CaseReducer, PayloadAction } from '@reduxjs/toolkit';

export const setAnalyticsAction: CaseReducer = (
  state,
  action: PayloadAction<any>
) => {
  let analytics = {
    ...state,
    ...action.payload,
  };
  return analytics;
};
