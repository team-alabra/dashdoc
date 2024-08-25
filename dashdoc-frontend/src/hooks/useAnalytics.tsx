import React, { useEffect, useState } from 'react';
import { userAnalytics } from '@api/analytics';
import { useDispatch, useSelector } from './hooks';
import { setAnalytics } from '@services/slices/analyticSlice';
import { getAnalytics } from '@services/slices/analyticSlice';

export const useAnalytics = () => {
  const [error, setError] = useState<string>('');
  const analytics = useSelector(getAnalytics);
  const dispatch = useDispatch();

  const fetchUserAnalytics = async () => {
    try {
      const data = await userAnalytics();
      dispatch(setAnalytics(data));
    } catch (error) {
      setError(error.message);
    }
  };

  useEffect(() => {
    fetchUserAnalytics();
  }, []);

  return {
    error,
    setError,
    analytics,
  };
};
