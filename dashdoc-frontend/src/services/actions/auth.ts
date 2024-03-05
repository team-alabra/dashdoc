import { CaseReducer, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { User } from '@typings/user';

export const setAuthAction: CaseReducer = (
  state: RootState,
  action: PayloadAction<User>
) => {
  let authStatus = {
    ...state,
    ...action.payload,
  };
  return authStatus;
};
