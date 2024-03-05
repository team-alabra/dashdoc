import { createSlice, Slice } from '@reduxjs/toolkit';
import { setAuthAction } from '@services/actions/auth';

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

export default authSlice.reducer;
