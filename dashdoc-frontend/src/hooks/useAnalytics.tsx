import React, { useState } from 'react';
import { userAnalytics } from '@api/analytics';

export const useAnalytics = () => {
  const [user_analytics, setUserAnalytics] = useState();
  const [error, setError] = useState<string>('');

  const analyticsHandler = async () => {
    try {
      const analytics = await userAnalytics();
      setUserAnalytics(analytics);
    } catch (error) {
      setError(error.message);
    }
  };

  return {
    analyticsHandler,
    error,
    setError,
    user_analytics
  }
};
