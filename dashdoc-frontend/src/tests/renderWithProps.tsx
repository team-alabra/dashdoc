import React, { PropsWithChildren } from "react";
import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import { render } from "@testing-library/react";
import userReducer from "@services/slices/userSlice";
import authReducer from "@services/slices/authSlice";
import planReducer from "@services/slices/planSlice";
import clientReducers from "@services/slices/clientSlice";
import { BrowserRouter } from "react-router-dom";
import { GoogleOAuthProvider } from '@react-oauth/google';

export const reducers = {
  user: userReducer,
  auth: authReducer,
  plan: planReducer,
  client: clientReducers.singleClient,
  clients: clientReducers.clientsList
};

export const rootState = combineReducers(reducers);

export const mockStore = configureStore({
  reducer: reducers,
});

type Options = {
  route: string;
};

export const wrapper: React.FC<PropsWithChildren> = (props) => {
  return (
    <Provider store={mockStore}>
      <GoogleOAuthProvider clientId='123abc'>
        <BrowserRouter>{props.children}</BrowserRouter>
      </GoogleOAuthProvider>
    </Provider>
  );
};
export const renderWithProvider = (
  component: React.ReactElement,
  options: Options = { route: '/' }
) => {
  window.history.pushState({}, 'Test Page', options.route);

  return { ...render(component, { wrapper }) };
};
