import { User } from '@typings/user';
import { createSlice, Slice } from '@reduxjs/toolkit';
import { saveUserAction } from '@services/actions/user';
import { RootState } from '../store';

export const initialState: User = {
  id: 0,
  email: '',
  npi: '',
  userType: '',
  providerDetail: {
    id: 0,
    firstName: '',
    lastName: '',
    gender: '',
    streetAddress: '',
    city: '',
    zipCode: '',
    phoneNumber: '',
    dob: '',
    discipline: '',
  },
  agencyID: 0,
  subscriptionPlanID: 0,
  stripeCustomerID: 0,
};

export const userSlice: Slice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    saveUser: saveUserAction,
  },
});

export const { saveUser } = userSlice.actions;
export const getUserFromDB = (state: RootState) => state.user;

export default userSlice.reducer;
