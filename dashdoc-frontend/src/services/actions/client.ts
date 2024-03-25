import { CaseReducer, PayloadAction } from '@reduxjs/toolkit';
import { RootState } from '../store';
import { Client } from '@typings/client';

export const setClientAction: CaseReducer = (
  state: RootState,
  action: PayloadAction<Client>
) => action.payload;

export const setClientsAction: CaseReducer = (
  state: RootState,
  action: PayloadAction<Client[]>
) => action.payload;

export const addClientAction: CaseReducer = (
  state: RootState,
  action: PayloadAction<Client>
) => [...state.clients, action.payload];