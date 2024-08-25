import React, { useEffect } from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '@hooks';

const PrivateRoute: React.FC<any> = ({ children }) => {
  const { isAuthenticated, setIsLoading, authenticateUser } = useAuth();

  useEffect(() => {
    setIsLoading(true);
    (async () => await authenticateUser())();
  }, [isAuthenticated]);

  if (!isAuthenticated) {
    return <Navigate to='/login' replace />;
  }
  return children;
};

export default PrivateRoute;
