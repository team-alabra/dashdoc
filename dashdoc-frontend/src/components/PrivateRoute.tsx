import React, { useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '@hooks';

const PrivateRoute: React.FC<any> = ({ children }) => {
  const { isAuthenticated, setIsLoading, isValidUser } = useAuth();

  useEffect(() => {
    setIsLoading(true);
    (async () => await isAuthenticated())();
  }, [isValidUser]);

  // if (!isValidUser) {
  //   return <Navigate to='/login' replace />;
  // }

  return children;
};

export default PrivateRoute;
