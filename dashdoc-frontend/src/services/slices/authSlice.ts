import { createSlice, Slice } from '@reduxjs/toolkit';
import { setAuthAction } from '@services/actions/auth';
import { RootState } from '@services/store';

export const authSlice: Slice = createSlice({
  name: 'auth',
  initialState: {
    isAuthenticated: false,
  },
  reducers: {
    setAuth: setAuthAction
  },
});

export const { setAuth } = authSlice.actions;
export const getAuth = (state: RootState) => state.auth;

export default authSlice.reducer;
