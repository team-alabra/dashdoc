import { setAuth } from '@services/slices/authSlice';

export const setAuthThenNavigate = (dispatch: any, navigate: any) => {
  dispatch(setAuth({ isAuthenticated: false }));
};

export const setHTMLFor = (input: string) => {
  if (input === 'AGENCY_PROVIDER') {
    return 'agencyCode';
  } else {
    return 'agencyName';
  }
};

export const setLabel = (input: string) => {
  if (input === 'agencyCode') {
    return 'Agency Code';
  } else {
    return 'Agency Name';
  }
};
