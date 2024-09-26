import React, { useState } from 'react';
import { getUserAnalytics } from '@api/analytics';

export const useAnalytics = () => {
  const [userAnalytics, setUserAnalytics] = useState();
  const [error, setError] = useState<string>('');

  const analyticsHandler = async () => {
    try {
      const analytics = await getUserAnalytics();
      setUserAnalytics(analytics);
    } catch (error) {
      setError(error.message);
    }
  };

  return {
    analyticsHandler,
    error,
    setError,
    userAnalytics
  }
};
