import { CaseReducer, PayloadAction } from "@reduxjs/toolkit";
import { RootState } from "../store";
import { Client } from "@typings/client";

export const setClientAction: CaseReducer = (
  state,
  action: PayloadAction<Client>
) => action.payload;

export const setClientsAction: CaseReducer = (
  state,
  action: PayloadAction<Client[]>
) => action.payload;

export const addClientAction: CaseReducer = (
  state,
  action: PayloadAction<Client>
) => [...state, action.payload]
