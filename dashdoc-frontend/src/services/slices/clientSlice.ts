import { createSlice, Slice } from '@reduxjs/toolkit';
import { saveClientAction, saveClientsAction } from '@services/actions/client';
import { Client } from '@typings/client';

export const initialState: Client = {
  id: 1,
  firstName: '',
  lastName: '',
  ageGroup: null,
  age: 0,
  dob: null,
  phoneNUmber: '',
  email: "",
  gender: null,
  address: '',
  city: '',
  state: '',
  zipCode: '',
  preferredLanguage: ''
};

export const clientSlice: Slice = createSlice({
  name: 'client',
  initialState,
  reducers: {
    setClient: saveClientAction,
    setClients: saveClientsAction
  },
});

export const { setClient, setClients } = clientSlice.actions;

export default clientSlice.reducer;
