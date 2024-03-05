import React, { PropsWithChildren } from "react";
import { configureStore } from "@reduxjs/toolkit";
import { Provider } from "react-redux";
import { render } from "@testing-library/react";
import userSlice from "@services/slices/userSlice";
import authSlice from "@services/slices/authSlice";
import planSlice from "@services/slices/planSlice";
import { BrowserRouter } from "react-router-dom";
import { GoogleOAuthProvider } from '@react-oauth/google';

const store = configureStore({
  reducer: {
    user: userSlice,
    auth: authSlice,
    plan: planSlice,
  },
});

type Options = {
  route: string;
};

export const wrapper: React.FC<PropsWithChildren> = (props) => {
  return (
    <Provider store={store}>
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
