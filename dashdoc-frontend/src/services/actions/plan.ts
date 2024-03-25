import { CaseReducer, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { PlanTypes } from '@typings/plan';

export const savePlanAction: CaseReducer = (
  state: RootState,
  action: PayloadAction<PlanTypes>
) => {
  let plan = {
    ...state,
    ...action.payload,
  };
  
  return plan;
};
