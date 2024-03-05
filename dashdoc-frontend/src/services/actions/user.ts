import { CaseReducer, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { User } from '@typings/user';

export const saveUserAction: CaseReducer = (
  state: RootState,
  action: PayloadAction<User>
) => {
  let user = {
    ...state,
    ...action.payload,
  };
  return user;
};
