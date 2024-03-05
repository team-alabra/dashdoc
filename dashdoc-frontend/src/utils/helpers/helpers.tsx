import { setAuth } from "@services/slices/authSlice";

export const setAuthThenNavigate = (dispatch: any, navigate: any) => {
  dispatch(setAuth({ isAuthenticated: false }));

};


