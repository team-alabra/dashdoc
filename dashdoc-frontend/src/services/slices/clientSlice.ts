import { createSlice, Slice } from '@reduxjs/toolkit';
import { addClientAction, setClientAction, setClientsAction } from '@services/actions/client';
import { RootState } from '@services/store';
import { Client } from '@typings/client';

export const initialClientState: Client = {
  id: 0,
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

const clientSlice: Slice = createSlice({
  name: 'client',
  initialState: initialClientState,
  reducers: {
    setClient: setClientAction,
  },
});

const clientsSlice: Slice = createSlice({
  name: 'clients',
  initialState: [] as Client[],
  reducers: {
    setClients: setClientsAction,
    addClient: addClientAction
  },
});

const { setClient } = clientSlice.actions;
const { setClients, addClient } = clientsSlice.actions;

export const clientActions = {
  getOne: (state: RootState) => state.client,
  getMany: (state: RootState) => state.clients,
  setOne: setClient,
  setMany: setClients,
  addOne: addClient
}

export default {
  singleClient: clientSlice.reducer,
  clientsList: clientsSlice.reducer
}