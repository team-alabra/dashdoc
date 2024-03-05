import React from 'react';
import { render } from 'react-dom';
import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { Provider } from 'react-redux';
import store from '@services/store';
import './styles/index.css';
import { ThemeProvider} from '@mui/material/styles';
import { theme } from "./styles/muiThemeStyles";

render(
  // <GoogleOAuthProvider clientId={process.env.REACT_APP_GOOGLE_CLIENT_ID}>
  <ThemeProvider theme={theme}>
    <Provider store={store}>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </Provider>
    </ThemeProvider>,
  // </GoogleOAuthProvider>,
  document.getElementById('app')
);
